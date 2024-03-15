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
    private static final String JDBC_URL = "jdbc:sqlite:D://Java Training//Task 4//RM//Records.db";

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
        String password2 = request.getParameter("password2");

        // Check if any field is empty
        if (email.isEmpty() || mobileNumber.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email format");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate phone number format
        if (!isValidPhoneNumber(mobileNumber)) {
            request.setAttribute("errorMessage", "Phone number should be 10 digits");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Validate password length and match
        if (!isValidPassword(password1)) {
            request.setAttribute("errorMessage", "Password should be at least 8 characters and contain at least 1 uppercase letter, 1 lowercase letter, and 1 special character");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        // Check if passwords match
        if (!password1.equals(password2)) {
            request.setAttribute("errorMessage", "Passwords do not match");
            // Set the email attribute before forwarding the request
            request.setAttribute("email", email);
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }

        Connection c = null;

        // Insert new user into the database
        try {
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

    // Method to validate email format
    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Method to validate phone number format
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for phone number validation (simple)
        String phoneRegex = "\\d{10}";
        return phoneNumber.matches(phoneRegex);
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
        return "Registration Servlet";
    }
}