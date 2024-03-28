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
        <%!
            String[] v = new String[2];
        %>
        <%
            String email = (String) session.getAttribute("email");
            String realPath = "";
            Connection conn = null;
            ResultSet rs = null;

            try {
                ServletContext con = getServletContext();
                realPath = con.getRealPath("Records.db");
                System.out.println("Database path: " + realPath);
                String JDBC_URL = "jdbc:sqlite:" + realPath;
                conn = DriverManager.getConnection(JDBC_URL);
                PreparedStatement stmt = conn.prepareStatement("SELECT MobileNumber from TblUserMaster where Email = ?");
                stmt.setString(1, email);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    v[0] = email;
                    v[1] = rs.getString("MobileNumber");

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
        <script>
            function showDropdown() {
                document.getElementById("dropdownContent").style.display = "block";
            }

            function hideDropdown() {
                document.getElementById("dropdownContent").style.display = "none";
            }
        </script>
        <header>
            <div class="header-container">
                <div class="profile-container" onmouseover="showDropdown()" onmouseout="hideDropdown()">
                    <img src="1.png" class="logo">
                    <div class="dropdown-content" id="dropdownContent">
                        <p><%=v[0]%></p>
                        <p><%=v[1]%></p>
                    </div>
                </div>
                <div class="header1">
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
                    <div class="welcome-text">
                        Welcome to Employee Records Management System.
                    </div>
                    <img src="emp1.jpg" class="custom-img">
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
