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
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ForgotPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1"); // Corrected parameter name
        String password2 = request.getParameter("password2");

        Connection connection = null; // Initialize connection outside try block

        try {
            // Check for individual empty fields
            if (email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
                request.setAttribute("errorMessage", "Error: Please enter all information");
                request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
                return;
            }

            // Check if passwords match
            if (!password1.equals(password2)) {
                request.setAttribute("errorMessage", "Error: Passwords do not match");
                request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
                return;
            }

            // Validate password length and match
            if (!isValidPassword(password1)) {
                request.setAttribute("errorMessage", "1: Password should be at least 8 characters."
                        + "   2: Password should have at least 1 capital and 1 small letter."
                        + "   3: Password should have at least 1 special character");
                request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
                return;
            }
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            
            // Insert new password into the database
            connection = DriverManager.getConnection(JDBC_URL);
            String updateQuery = "UPDATE TblUserMaster SET password = ? WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, password1);
                preparedStatement.setString(2, email);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Password updated successfully, redirect to login page
                    response.sendRedirect("index.jsp");
                } else {
                    // No rows affected, display error message
                    request.setAttribute("errorMessage", "Error: Email not found");
                    request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Database error, display error message
            request.setAttribute("errorMessage", "Error: Database error");
            request.getRequestDispatcher("ForgotPassword.jsp").forward(request, response);
        } finally {
            // Close the database connection in the finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    // Method to validate password length and complexity
    private boolean isValidPassword(String password1) {
        // Check if password is at least 8 characters long
        if (password1.length() < 8) {
            return false;
        }

        // Check for at least one uppercase letter, one lowercase letter, and one special character
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecialChar = false;

        for (char c : password1.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }

            // If all criteria are met, return true
            if (hasUppercase && hasLowercase && hasSpecialChar) {
                return true;
            }
        }

        // If any criteria are not met, return false
        return false;
    }

    @Override
    public String getServletInfo() {
        return "Forgot Password Servlet";
    }
}
