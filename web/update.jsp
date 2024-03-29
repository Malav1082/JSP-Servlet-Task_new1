<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "update.jsp");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Records</title> 
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            String[] val = new String[11];
            String[] v = new String[2];
            String realPath = "";
            Connection conn = null;
            ResultSet rs = null;

            try {
                ServletContext con = getServletContext();
                realPath = con.getRealPath("Records.db");
                System.out.println("Database path: " + realPath);
                String JDBC_URL = "jdbc:sqlite:" + realPath;
                conn = DriverManager.getConnection(JDBC_URL);

                // Fetch data from the database based on the provided empId
                PreparedStatement stmt = conn.prepareStatement("SELECT M.EmpID, M.EmpName, M.Designation, M.Department, M.JoinedDate, M.Salary, D.AddressLine1, D.AddressLine2, D.City, D.State, D.Country FROM TblEmployeeMaster M JOIN TblEmployeeDetail D ON M.MastCode = D.EmpCode where EmpID = ?");
                stmt.setString(1, request.getParameter("empId"));
                rs = stmt.executeQuery();

                // Store the retrieved data into the val array
                if (rs.next()) {
                    for (int i = 1; i <= 11; i++) {
                        val[i - 1] = rs.getString(i);
                    }
                } else {
                    System.out.println("No records found for the provided empId.");
                }

                String email = (String) session.getAttribute("email");
                PreparedStatement emailStmt = conn.prepareStatement("SELECT MobileNumber from TblUserMaster where Email = ?");
                emailStmt.setString(1, email);
                ResultSet Rs = emailStmt.executeQuery();
                if (Rs.next()) {
                    v[0] = email;
                    v[1] = Rs.getString("MobileNumber");
                } else {
                    System.out.println("No records found for the provided email.");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Close the connection and result sets
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
        <h1>Update Records</h1>
        <form action="EmployeeServlet?action=update" method="post">
            <div class="table-container">
                <table>
                    <tr>
                        <td>EmpID</td>
                        <td><input type="text" pattern="[a-zA-Z0-9]+" name="empId" value='<%=val[0]%>' readonly></td>
                    </tr>
                    <tr>
                        <td>EmpName</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="empName" value='<%=val[1]%>' required></td>
                    </tr>
                    <tr>
                        <td>Designation</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="designation" value='<%=val[2]%>' required></td>
                    </tr>
                    <tr>
                        <td>Department</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="department" value='<%=val[3]%>' required></td>
                    </tr>
                    <tr>
                        <td>JoinedDate</td>
                        <td><input type="date" name="joinedDate" value='<%=val[4]%>' placeholder="Enter JoinedDate" max="<%=java.time.LocalDate.now()%>" required></td>
                    </tr>
                    <tr>
                        <td>Salary</td>
                        <td><input type="number" pattern="\d+" name="salary" value='<%=val[5]%>' required></td>
                    </tr>
                    <tr>
                        <td>AddressLine1</td>
                        <td><input type="text" pattern="[a-zA-Z0-9\s]+" name="addressLine1" value='<%=val[6]%>' required></td>
                    </tr>
                    <tr>
                        <td>AddressLine2</td>
                        <td><input type="text" pattern="[a-zA-Z0-9\s]+" name="addressLine2" value='<%=val[7]%>' required></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="city" value='<%=val[8]%>' required></td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="state" value='<%=val[9]%>' required></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" pattern="[a-zA-Z\s]+" name="country" value='<%=val[10]%>' required></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="td-btn">
                            <input type="submit" id="submit" name="Submit" value="Submit">
                            <input type="Button" value="Back" onclick="location.href = 'home.jsp';" class="back">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
    <footer>
        <p class="footer-text">
            Copyrights &copy; reserved for Employee Records. All Rights Reserved.
        </p>
    </footer>
</html>
