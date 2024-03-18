<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "home.jsp");%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home Page</title>
        <%-- Retrieve the JDBC URL attribute --%>
        <%
            String jdbcUrl = (String) request.getAttribute("jdbcUrl");
        %>
        <%
        String db_url = "jdbc:sqlite:" + (new java.io.File(application.getRealPath("/")).getParentFile().getParentFile()).getAbsolutePath() + application.getInitParameter("Employee");
            System.out.println("homepage link: " + db_url);
        %>
        <sql:setDataSource driver="org.sqlite.JDBC" url="<%=db_url%>" var="ds"/>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
                background-color: snow;
            }
            th, td {
                border: 2px solid black;
                text-align: center;
                padding: 5px;
                word-wrap: break-word;
                width: 100px;
                border-color: black;
            }
            th {
                background-color: lightyellow;
                height: 30px;
            }

            .add-button {
                background-color: blue;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
                margin-bottom: 20px;
            }

            .update-button {
                background-color: green;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
            }

            .delete-button{
                background-color: #f44336;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
            }

            .logout {
                background-color: grey;
                border: none;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
                margin-bottom: 20px;
            }

            .button-container {
                display: flex;
                justify-content: space-between;
            }
            .record {
                margin-left: 45%;
                color: darkblue;
            }

            .add-button:hover {
                background-color: darkblue;
            }
            .update-button:hover {
                background-color: darkgreen;
            }
            .delete-button:hover {
                background-color: firebrick;
            }
            .logout:hover {
                background-color: black;
            }
        </style>
    </head>
    <body>
        <!-- Display Records -->
        <h1 class="record">Records:</h1>

        <div class="button-container">
            <!-- Add Record -->
            <form action="add.jsp" method="post">
                <input type="submit" value="Add" class="add-button">
            </form>

            <!--Logout-->
            <form action="logout" method="post">
                <input type="submit" value="Logout" class="logout">
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
                        try {
                            conn = DriverManager.getConnection("jdbc:sqlite:D://Java Training//Task 4//RM//Records.db");
                            Statement stmt = conn.createStatement();
                            String query = "SELECT M.EmpID, M.EmpName, M.Designation, M.Department, M.JoinedDate, M.Salary, D.AddressLine1, D.AddressLine2, D.City, D.State, D.Country FROM TblEmployeeMaster M JOIN TblEmployeeDetail D ON M.MastCode = D.EmpCode";
                            ResultSet rs = stmt.executeQuery(query);

                            // Iterate over the result set and display records in the table
                            while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("EmpID")%></td>
                        <td><%= rs.getString("EmpName")%></td>
                        <td><%= rs.getString("Designation")%></td>
                        <td><%= rs.getString("Department")%></td>
                        <td><%= rs.getString("JoinedDate")%></td>
                        <td><%= rs.getString("Salary")%></td>
                        <td><%= rs.getString("AddressLine1")%></td>
                        <td><%= rs.getString("AddressLine2")%></td>
                        <td><%= rs.getString("City")%></td>
                        <td><%= rs.getString("State")%></td>
                        <td><%= rs.getString("Country")%></td>
                        <td><a class="update-link update-button" href="update.jsp?empId=<%= rs.getString("EmpID")%>">Update</a></td>
                        <td><button class="delete-button" data-empid="<%= rs.getString("EmpID")%>">Delete</button></td>
                    </tr>
                    <%
                            }
                            // Close connections
                            rs.close();
                            stmt.close();
                            conn.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
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
    </table>
</form>

</body>
</html>
