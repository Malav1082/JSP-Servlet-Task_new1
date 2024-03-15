/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RM;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Database extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Database</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Database at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String JDBC_URL = "jdbc:sqlite:D:\\Java Training\\Task 4\\RM/Records.db";

    private static final String sql = "SELECT * FROM TblUserMaster ORDER BY UserID";

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            throw new ServletException("SQLite JDBC driver not found", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL);
            if (connection != null) {
                out.println("<html><body>");

                //out.println("<h2>Connected to the database.</h2>");
                // Create TblUserMaster table
                createTblUserMaster(connection, out);

                // Create TblEmployeeMaster table
                createTblEmployeeMaster(connection, out);

                // Create TblEmployeeDetail table
                createTblEmployeeDetail(connection, out);

//                // Display tables
//                displayTables(connection, out);

//                out.println("<h2>Tables created successfully.</h2>");
//                out.println("</body></html>");
                out.println("</body></html>");
            } else {
                out.println("<html><body>");
                out.println("<h2>Failed to connect to the database.</h2>");
                out.println("</body></html>");
            }
        } catch (SQLException e) {
            out.println("<html><body>");
            out.println("<h2>SQL Exception: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
            out.println("</body></html>");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private static void createTblUserMaster(Connection connection, PrintWriter out) throws SQLException {
        String createUserMasterTable = "CREATE TABLE IF NOT EXISTS TblUserMaster ("
                + "UserID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Email TEXT,"
                + "MobileNumber TEXT,"
                + "Password TEXT"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createUserMasterTable);
//            out.println("<p>TblUserMaster table created successfully.</p>");
        }
    }

    private static void createTblEmployeeMaster(Connection connection, PrintWriter out) throws SQLException {
        String createEmployeeMasterTable = "CREATE TABLE IF NOT EXISTS TblEmployeeMaster ("
                + "MastCode INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "UserID INTEGER,"
                + "EmpID TEXT UNIQUE,"
                + "EmpName TEXT,"
                + "Designation TEXT,"
                + "Department TEXT,"
                + "JoinedDate TEXT,"
                + "Salary REAL,"
                + "FOREIGN KEY (UserID) REFERENCES TblUserMaster(UserID)"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createEmployeeMasterTable);
//            out.println("<p>TblEmployeeMaster table created successfully.</p>");
        }
    }

    private static void createTblEmployeeDetail(Connection connection, PrintWriter out) throws SQLException {
        String createEmployeeDetailTable = "CREATE TABLE IF NOT EXISTS TblEmployeeDetail ("
                + "EmpCode INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "AddressLine1 TEXT,"
                + "AddressLine2 TEXT,"
                + "City TEXT,"
                + "State TEXT,"
                + "Country TEXT,"
                + "FOREIGN KEY (EmpCode) REFERENCES TblEmployeeMaster(MastCode)"
                + ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createEmployeeDetailTable);
//            out.println("<p>TblEmployeeDetail table created successfully.</p>");
        }
    }

//    private static void displayTables(Connection connection, PrintWriter out) throws SQLException {
////        displayTable(connection, "TblUserMaster", out);
//        displayTable(connection, "TblEmployeeMaster", out);
//        displayTable(connection, "TblEmployeeDetail", out);
//    }

//    private static void displayTable(Connection connection, String tableName, PrintWriter out) throws SQLException {
//        out.println("<h3>" + tableName + "</h3>");
//        try (Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {
//            out.println("<table border='1'>");
//            out.println("<tr>");
//            ResultSetMetaData metaData = resultSet.getMetaData();
//            int columnCount = metaData.getColumnCount();
//            for (int i = 1; i <= columnCount; i++) {
//                out.println("<th>" + metaData.getColumnName(i) + "</th>");
//            }
//            out.println("</tr>");
//            while (resultSet.next()) {
//                out.println("<tr>");
//                for (int i = 1; i <= columnCount; i++) {
//                    out.println("<td>" + resultSet.getString(i) + "</td>");
//                }
//                out.println("</tr>");
//            }
//            out.println("</table>");
//        }
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
