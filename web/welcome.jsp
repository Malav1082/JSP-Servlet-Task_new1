<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
        <link rel="stylesheet" type="text/css" href="welcome.css">    
    </head>
    <body>
        <h1>Welcome</h1>
        <%!
            String[] val = new String[2];
        %>
        <%
            String realPath = "";
            Connection conn = null;
            ResultSet rs = null;

            try {
                ServletContext con = getServletContext();
                realPath = con.getRealPath("Records.db");
                System.out.println("Database path: " + realPath);
                String JDBC_URL = "jdbc:sqlite:" + realPath;
                conn = DriverManager.getConnection(JDBC_URL);
                PreparedStatement stmt = conn.prepareStatement("SELECT Email, MobileNumber from TblUserMaster where Email = ?");
                stmt.setString(1, request.getParameter("email"));
                rs = stmt.executeQuery();
                if (rs.next()) {
                    val[0] = rs.getString("email");
                    val[1] = rs.getString("mobnum");
                } else {
                    System.out.println("No records found for the provided email.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
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
        %>
        <header>
            <div class="header-container">
                <img src="1.png" class="logo">
                <div class="user-info">
                    <form>
                        <table>
                            <tr>
                                <td>Email:<input type="text" name="email" value='<%=val[0]%>' readonly></td>
                            </tr>
                            <tr>
                                <td>Mobile Number:<input type="text" name="mobnum" value='<%=val[1]%>' readonly></td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="header-container">
                    <form action="home.jsp" method="post">
                        <input type="submit" value="Records" class="home">
                    </form>
                    <form action="logout" method="post">
                        <input type="submit" value="Logout" class="logout">
                    </form>
                </div>
            </div>
        </header>
        <div>
            <div>
                <div class="image-container">
                    <img src="emp.jpg" class="custom-img">
                </div>
            </div>
        </div>
        <footer>
            <p class="footer-text">
                Copyrights &copy; reserved for Employee Records. All Rights Reserved.
            </p>
        </footer>
    </body>
</html>
