<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%        
    System.out.println(request.getSession(false) + "home.jsp");

    // Initialize email and mobile number variables
    String email = "";
    String mobileNumber = "";

    // Retrieve email and mobile number from the session
    HttpSession s = request.getSession(false);
    if (s != null) {
        email = (String) s.getAttribute("email");
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
                mobileNumber = rs.getString("MobileNumber");
            } else {
                System.out.println("No records found for the provided email.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close the connection
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
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" href="home.css">
        <!-- Display Records -->
    </head>
    <body>
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
                        <p><%= email%></p>
                        <p><%= mobileNumber%></p>
                    </div>
                </div>
                <div class="header1">
                    <form action="welcome.jsp" method="post">
                        <input type="submit" value="Back" class="back">
                    </form>
                    <form action="logout" method="post">
                        <input type="submit" value="Logout" class="logout">
                    </form>
                </div>
            </div>
        </header>
        <h1>Employee Records</h1>    
        <div class="button-container">
            <!-- Add Record -->
            <form action="add.jsp" method="post">
                <input type="submit" value="Add" class="add-button">
            </form>
        </div>
        <form>
            <table>
                <thead>
                    <tr>
                        <th>EmpID</th>
                        <th>EmpName</th>
                        <th>Designation</th>
                        <th>Department</th>
                        <th>JoinedDate</th>
                        <th>Salary</th>
                        <th>AddressLine1</th>
                        <th>AddressLine2</th>
                        <th>City</th>
                        <th>State</th>
                        <th>Country</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Connection conn = null;
                        ResultSet rs2 = null;

                        try {
                            ServletContext con = getServletContext();
                            String realPath = con.getRealPath("Records.db");
                            System.out.println("Database path: " + realPath);
                            String JDBC_URL = "jdbc:sqlite:" + realPath;
                            conn = DriverManager.getConnection(JDBC_URL);

                            Statement stmt2 = conn.createStatement();
                            String query = "SELECT M.EmpID, M.EmpName, M.Designation, M.Department, M.JoinedDate, M.Salary, D.AddressLine1, D.AddressLine2, D.City, D.State, D.Country FROM TblEmployeeMaster M JOIN TblEmployeeDetail D ON M.MastCode = D.EmpCode";
                            rs2 = stmt2.executeQuery(query);

                            while (rs2.next()) {
                    %>
                    <tr>
                        <td><%= rs2.getString("EmpID")%></td>
                        <td><%= rs2.getString("EmpName")%></td>
                        <td><%= rs2.getString("Designation")%></td>
                        <td><%= rs2.getString("Department")%></td>
                        <td><%= rs2.getString("JoinedDate")%></td>
                        <td><%= rs2.getString("Salary")%></td>
                        <td><%= rs2.getString("AddressLine1")%></td>
                        <td><%= rs2.getString("AddressLine2")%></td>
                        <td><%= rs2.getString("City")%></td>
                        <td><%= rs2.getString("State")%></td>
                        <td><%= rs2.getString("Country")%></td>
                        <td><a class="update" href="update.jsp?empId=<%= rs2.getString("EmpID")%>">Update</a></td>
                        <td><button class="delete-button" data-empid="<%= rs2.getString("EmpID")%>">Delete</button></td>
                    </tr>
                    <%
                            }
                            // Close connections
                            rs2.close();
                            stmt2.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } finally {
                            // Close the connection
                            if (rs2 != null) {
                                try {
                                    rs2.close();
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
                </tbody>
            </table>
            <!-- Script to handle Update and Delete actions -->
            <script>
                document.querySelectorAll('.delete-button').forEach(button => {
                    button.addEventListener('click', function () {
                        const empId = this.getAttribute('data-empid');

                        // Display confirmation dialog
                        const confirmed = confirm('Are you sure you want to delete the record?');

                        // If user confirms, send AJAX request to servlet to delete record
                        if (confirmed) {
                            fetch('EmployeeServlet?action=delete&empId=' + empId, {
                                method: 'POST'
                            }).then(response => {
                                if (response.ok) {
                                    // Reload page after successful deletion
                                    window.location.reload();
                                } else {
                                    console.error('Error deleting record');
                                }
                            }).catch(error => {
                                console.error('Error deleting record:', error);
                            });
                        }
                    });
                });

                document.querySelectorAll('tr[data-empid]').forEach(row => {
                    row.addEventListener('click', function () {
                        const empId = this.getAttribute('data-empid');
                        window.location.href = 'update.jsp?empId=' + empId;
                    });
                });
            </script>
        </tbody>
    </form>
</body>
<footer>
    <p class="footer-text">
        Copyrights &copy; reserved for Employee Records. All Rights Reserved.
    </p>
</footer>
</html>
