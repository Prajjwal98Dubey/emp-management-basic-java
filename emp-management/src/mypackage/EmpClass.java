package mypackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class EmpClass {
    String DB_URL = "jdbc:mysql://localhost:3306/";
    String USER = "root";
    String PASS = "Prajjwal1998@";
    String MY_DB = "EMPLOYEE";

    public void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "CREATE DATABASE EMPLOYEE";
            Statement smt = conn.createStatement();
            smt.executeUpdate(query);
            System.out.println("Database Created Successfully");
            conn.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }
    public void createTable() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL + MY_DB, USER, PASS);
            Statement smt = conn.createStatement();
            String query = "CREATE TABLE EMP_INFO (id int(5),name varchar(200),email varchar(200),salary int(10))";
            smt.execute(query);
            System.out.println("Table Created Successfuly!");
            conn.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public ArrayList<String> createData(int empId, String empName, String empEmail, int empSalary) {
        ArrayList<String> empData = new ArrayList<String>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL + MY_DB, USER, PASS);
            String query = "INSERT INTO EMP_INFO VALUES (?,?,?,?)";
            PreparedStatement pmt = conn.prepareStatement(query);
            pmt.setInt(1, empId);
            pmt.setString(2, empName);
            pmt.setString(3, empEmail);
            pmt.setInt(4, empSalary);
            boolean isResultSetPresent = pmt.execute();
            if (!isResultSetPresent) {
                empData.add(Integer.toString(empId));
                empData.add(empName);
                empData.add(empEmail);
                empData.add(Integer.toString(empSalary));
            }
            System.out.println("Data Created Successfully!!!");
            conn.close();
        } catch (SQLException e) {

            e.getStackTrace();
        }
        return empData;
    }

    public ArrayList<ArrayList<String>> readData() {
        ArrayList<ArrayList<String>> empData = new ArrayList<ArrayList<String>>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL + MY_DB, USER, PASS);
            Statement smt = conn.createStatement();
            String query = "SELECT * FROM EMP_INFO";
            ResultSet rs = smt.executeQuery(query);
            while (rs.next()) {
                ArrayList<String> e = new ArrayList<String>();
                e.add(Integer.toString(rs.getInt(1)));
                e.add(rs.getString(2));
                e.add(rs.getString(3));
                e.add(Integer.toString(rs.getInt(4)));
                empData.add(e);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return empData;
    }

    public ArrayList<String> updateData(int empId, String updatedEmpName) {
        ArrayList<String> empData = new ArrayList<String>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL + MY_DB, USER, PASS);
            String query = "UPDATE EMP_INFO SET NAME = ? WHERE ID = ?";
            PreparedStatement pmt = conn.prepareStatement(query);
            pmt.setInt(2, empId);
            pmt.setString(1, updatedEmpName);
            pmt.executeUpdate();
            String newQuery = "SELECT * FROM EMP_INFO";
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(newQuery);
            while (rs.next()) {
                if (rs.getInt(1) == empId) {
                    empData.add(Integer.toString(rs.getInt(1)));
                    empData.add(rs.getString(2));
                    empData.add(rs.getString(3));
                    empData.add(Integer.toString(rs.getInt(4)));
                    break;
                }
            }
            System.out.println("Data Updated.");
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return empData;
    }
    public void deleteData(int empId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL + MY_DB, USER, PASS);
            String query = "DELETE FROM EMP_INFO WHERE ID = ? ";
            PreparedStatement pmt = conn.prepareStatement(query);
            pmt.setInt(1, empId);
            pmt.executeUpdate();
            System.out.println("Data Deleted.");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
}
