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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlert</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlert at " + request.getContextPath() + "</h1>");
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
        String password = request.getParameter("password");
 
        Integer userID = validateLogin(email, password);

        if (userID == null) {
            // Set error message
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");

            // Forward the request back to the index.jsp page
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            // Login successful, set userID attribute in session
            HttpSession session = request.getSession(true);
            System.out.println(request.getSession(false) + "login Servlet");
            session.setAttribute("userID", userID.toString()); // Set userID instead of email

            // Redirect to home page
            response.sendRedirect("home.jsp");
        }
    }

    private Integer validateLogin(String email, String password) {
        String query = "SELECT UserID FROM TblUserMaster WHERE Email = ? AND Password = ?";
        Connection connection = null;
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("D path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            connection = DriverManager.getConnection(JDBC_URL);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, password);

                try (ResultSet rs = statement.executeQuery()) {
                    // If any rows are returned, return the UserID
                    if (rs.next()) {
                        return rs.getInt("UserID");
                    } else {
                        return null; // Invalid credentials
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
