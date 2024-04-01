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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Malav.Shah
 */
public class EmployeeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmployeeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    private static final String JDBC_URL = "jdbc:sqlite:D:\\Java Training\\Task 4\\RM//Records.db";
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userID") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // Get the action parameter and check if it's null
        String action = request.getParameter("action");
        if (action == null) {
            // Handle the case where action parameter is not set
            response.sendRedirect("home.jsp"); // Redirect to home page or display an error message
            return;
        }

        // Process the request based on the action
        switch (action) {
            case "add":
                addEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                response.sendRedirect("home.jsp");
        }
    }

    private boolean isDuplicateEmpId(String empId) {
        boolean isDuplicate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            conn = DriverManager.getConnection(JDBC_URL);
            String sql = "SELECT COUNT(*) FROM TblEmployeeMaster WHERE EmpID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, empId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                isDuplicate = count > 0; // If count > 0, then empId already exists
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        } finally {
            // Close resources
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return isDuplicate;
    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = (String) request.getSession().getAttribute("userID");

        System.out.println("a b" + request.getSession(false));

        String empId = request.getParameter("empId");
        String empName = request.getParameter("empName");
        String designation = request.getParameter("designation");
        String department = request.getParameter("department");
        String joinedDate = request.getParameter("joinedDate");
        String salary = request.getParameter("salary");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        // Check if empId already exists
        if (isDuplicateEmpId(empId)) {
            // Use JavaScript to show an alert on the client side
            String alertScript = "<script>alert('Employee ID already exists.');";
            alertScript += "window.location.href = 'add.jsp?empId=" + empId + "&empName=" + empName + "&designation=" + designation + "&department=" + department + "&joinedDate=" + joinedDate + "&salary=" + salary + "&addressLine1=" + addressLine1 + "&addressLine2=" + addressLine2 + "&city=" + city + "&state=" + state + "&country=" + country + "';</script>";
            response.getWriter().println(alertScript);
            return; // Return to stop further processing
        }

        Connection conn = null;
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            conn = DriverManager.getConnection(JDBC_URL);
            // Begin transaction
            conn.setAutoCommit(false);

            // Insert data into TblEmployeeMaster table
            String sqlMaster = "INSERT INTO TblEmployeeMaster (UserID, EmpID, EmpName, Designation, Department, JoinedDate, Salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlMaster, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, userID); // Set UserID
                ps.setString(2, empId); // Set EmpID
                ps.setString(3, empName);
                ps.setString(4, designation);
                ps.setString(5, department);
                ps.setString(6, joinedDate);
                ps.setString(7, salary);
                ps.executeUpdate();

                // Retrieve the auto-generated MastCode
                ResultSet generatedKeys = ps.getGeneratedKeys();
                int mastCode = 0;
                if (generatedKeys.next()) {
                    mastCode = generatedKeys.getInt(1);
                }

                // Insert data into TblEmployeeDetail table using the retrieved MastCode
                String sqlDetails = "INSERT INTO TblEmployeeDetail (EmpCode, AddressLine1, AddressLine2, City, State, Country) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement psDetails = conn.prepareStatement(sqlDetails)) {
                    psDetails.setInt(1, mastCode);
                    psDetails.setString(2, addressLine1);
                    psDetails.setString(3, addressLine2);
                    psDetails.setString(4, city);
                    psDetails.setString(5, state);
                    psDetails.setString(6, country);
                    psDetails.executeUpdate();
                }
            }
            // Commit transaction
            conn.commit();
            // Redirect back to home.jsp after updating record
            String successMessage = "Record added successfully.";

            // Display success message on the page
            HttpSession session = request.getSession();
            session.setAttribute("successMessage", successMessage);
            response.setHeader("Refresh", "2; URL=home.jsp");
            // response.sendRedirect("home.jsp");
            request.getRequestDispatcher("home.jsp").forward(request, response);
            System.out.println("a a" + request.getSession(false));

        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + ex.getMessage());
        } finally {
            // Close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("u b" + request.getSession(false));

        // Retrieve the parameters from the request
        String empId = request.getParameter("empId");
        String empName = request.getParameter("empName");
        String designation = request.getParameter("designation");
        String department = request.getParameter("department");
        String joinedDate = request.getParameter("joinedDate");
        String salary = request.getParameter("salary");
        String addressLine1 = request.getParameter("addressLine1");
        String addressLine2 = request.getParameter("addressLine2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");

        Connection conn = null;
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            conn = DriverManager.getConnection(JDBC_URL);

            // Update data in TblEmployeeMaster table
            String sqlMaster = "UPDATE TblEmployeeMaster SET EmpName=?, Designation=?, Department=?, JoinedDate=?, Salary=? WHERE EmpID=?";
            try (PreparedStatement ps = conn.prepareStatement(sqlMaster)) {
                ps.setString(1, empName);
                ps.setString(2, designation);
                ps.setString(3, department);
                ps.setString(4, joinedDate);
                ps.setString(5, salary);
                ps.setString(6, empId);
                ps.executeUpdate();
            }

            // Update data in TblEmployeeDetail table
            String sqlDetails = "UPDATE TblEmployeeDetail SET AddressLine1=?, AddressLine2=?, City=?, State=?, Country=? WHERE EmpCode=(SELECT MastCode FROM TblEmployeeMaster WHERE EmpID=?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlDetails)) {
                ps.setString(1, addressLine1);
                ps.setString(2, addressLine2);
                ps.setString(3, city);
                ps.setString(4, state);
                ps.setString(5, country);
                ps.setString(6, empId);
                ps.executeUpdate();
            }

            // Redirect back to home.jsp after updating record
            String successMessage = "Record updated successfully.";

            // Display success message on the page
            HttpSession session = request.getSession();
            // Set action attribute to indicate that the action was a update operation
            session.setAttribute("successMessage", successMessage);
            response.setHeader("Refresh", "2; URL=home.jsp");
            // response.sendRedirect("home.jsp");
            request.getRequestDispatcher("home.jsp").forward(request, response);
            System.out.println("u a" + request.getSession(false));

        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + ex.getMessage());
        } finally {
            // Close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the ID of the employee to be deleted from request parameters
        String empId = request.getParameter("empId");

        System.out.println("d b" + request.getSession(false));

        // Log the received empId for debugging
        System.out.println("Received empId for deletion: " + empId);

        Connection conn = null;
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            conn = DriverManager.getConnection(JDBC_URL);

            // Delete record from TblEmployeeDetail table using subquery
            String sqlDetails = "DELETE FROM TblEmployeeDetail WHERE EmpCode = (SELECT MastCode FROM TblEmployeeMaster WHERE EmpID = ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlDetails)) {
                ps.setString(1, empId);
                int rowsAffectedDetails = ps.executeUpdate();

                // Log the number of rows affected for debugging
                System.out.println("Rows affected in TblEmployeeDetail: " + rowsAffectedDetails);
            }

            // Delete record from TblEmployeeMaster table
            String sqlMaster = "DELETE FROM TblEmployeeMaster WHERE EmpID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlMaster)) {
                ps.setString(1, empId);
                int rowsAffectedMaster = ps.executeUpdate();

                // Log the number of rows affected for debugging
                System.out.println("Rows affected in TblEmployeeMaster: " + rowsAffectedMaster);
            }

             // Redirect back to home.jsp after updating record
            String successMessage = "Record deleted successfully.";

            // Display success message on the page
            HttpSession session = request.getSession();
            // Set action attribute to indicate that the action was a update operation
            session.setAttribute("successMessage", successMessage);
            response.setHeader("Refresh", "2; URL=home.jsp");
            // response.sendRedirect("home.jsp");
            request.getRequestDispatcher("home.jsp").forward(request, response);
            
            System.out.println("d a" + request.getSession(false));
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + ex.getMessage());
        } finally {
            // Close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
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
