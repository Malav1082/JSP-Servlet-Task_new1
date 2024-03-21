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

public class ResetPasswordServlet extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("password0");
        String newPassword = request.getParameter("password1");
        String confirmPassword = request.getParameter("password2");

        Connection connection = null;
        try {
            ServletContext con = getServletContext();
            String realPath = con.getRealPath("Records.db");
            System.out.println("Database path: " + realPath);
            String JDBC_URL = "jdbc:sqlite:" + realPath;
            connection = DriverManager.getConnection(JDBC_URL);
            String updateQuery = "UPDATE TblUserMaster SET password = ? WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, oldPassword);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("index.jsp"); // Password updated successfully, redirect to login page
                } else {
                    request.setAttribute("errorMessage", "Error: Incorrect email or old password");
                    request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Error: Database error");
            request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
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
    
    @Override
    public String getServletInfo() {
        return "Reset Password Servlet";
    }
}
