/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Windows 10
 */
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DBConnect() {
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "text";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";

        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url + dbName, userName, password);
            st = (Statement) con.createStatement();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println("Error: " + ex);
        }
    }
    public void setDataTeks(String nama, String kata, int jumlah) {
        try {            
            String q;
            q = "select max(id) as maks from data_teks";
            rs = st.executeQuery(q);
            rs.next();
            int rowCount = rs.getInt("maks");
            rowCount = rowCount+1;
            
            String query = " insert into data_teks (id, nama_file, kata, jumlah_kata)" + " values (?, ?, ?, ?)";

            PreparedStatement preparedStmt = (PreparedStatement)con.prepareStatement(query);
            
            preparedStmt.setInt(1, rowCount);
            preparedStmt.setString(2, nama); 
 
            preparedStmt.setString(3, kata);
            preparedStmt.setInt(4, jumlah);
          
            preparedStmt.execute();

        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
}
