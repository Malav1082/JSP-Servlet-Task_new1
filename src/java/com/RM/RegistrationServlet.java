/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RM;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationServlet extends HttpServlet {

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
            out.println("<title>Servlet RegistrationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationServlet at " + request.getContextPath() + "</h1>");
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

    // JDBC URL for SQLite database
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve registration form data
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobnum");
        String password1 = request.getParameter("password1");

        Connection c = null;

        // Insert new user into the database
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            c = DriverManager.getConnection(JDBC_URL);
            String checkExistingUserQuery = "SELECT * FROM TblUserMaster WHERE Email = ?";
            try (PreparedStatement checkExistingUserStatement = c.prepareStatement(checkExistingUserQuery)) {
                checkExistingUserStatement.setString(1, email);
                ResultSet rs = checkExistingUserStatement.executeQuery();

                // Check if email already exists
                if (rs.next()) {
                    // Email already exists in the database, display error message
                    request.setAttribute("errorMessage", "Email already exists");
                    // Set the email attribute before forwarding the request
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                    return;
                } else {
                    // Email doesn't exist, proceed with registration
                    String insertQuery = "INSERT INTO TblUserMaster (Email, MobileNumber, Password) VALUES (?, ?, ?)";
                    try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
                        ps.setString(1, email);
                        ps.setString(2, mobileNumber);
                        ps.setString(3, password1);
                        int rowsAffected = ps.executeUpdate();
                        if (rowsAffected > 0) {
                            // Registration successful, set success message in session and redirect to login page
                            request.getSession().setAttribute("Success", "Registration Successful");
                            System.out.println(request.getSession(false));
                            response.sendRedirect("index.jsp");
                        } else {

                            // No rows affected, display error message
                            request.setAttribute("errorMessage", "Registration failed");
                            // Set the email attribute before forwarding the request
                            request.setAttribute("email", email);
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                            return;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Database error, display error message
            request.setAttribute("errorMessage", "Database error");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        } finally {
            // Close the connection in the finally block to ensure it gets closed even if an exception occurs
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Registration Servlet";
    }
}
