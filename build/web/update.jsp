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

            function validateForm() {
                var empId = document.forms["updateRecordsForm"]["empId"].value;
                var empName = document.forms["updateRecordsForm"]["empName"].value;
                var designation = document.forms["updateRecordsForm"]["designation"].value;
                var department = document.forms["updateRecordsForm"]["department"].value;
                var joinedDate = document.forms["updateRecordsForm"]["joinedDate"].value;
                var salary = document.forms["updateRecordsForm"]["salary"].value;
                var addressLine1 = document.forms["updateRecordsForm"]["addressLine1"].value;
                var addressLine2 = document.forms["updateRecordsForm"]["addressLine2"].value;
                var city = document.forms["updateRecordsForm"]["city"].value;
                var state = document.forms["updateRecordsForm"]["state"].value;
                var country = document.forms["updateRecordsForm"]["country"].value;
                var errorMessageContainer = document.getElementById("errorMessage");
                var isValid = true;

                if (empId === "") {
                    errorMessageContainer.innerText = "Please fill in EmpID";
                    document.getElementsByName("empId")[0].focus();
                    return false;
                }

                var empIdPattern = /^[a-zA-Z0-9]+$/;
                if (!empIdPattern.test(empId)) {
                    errorMessageContainer.innerText = "EmpID should contain only alphanumeric characters.";
                    document.getElementsByName("empId")[0].focus();
                    return false;
                }

                if (empName === "") {
                    errorMessageContainer.innerText = "Please fill in EmpName";
                    document.getElementsByName("empName")[0].focus();
                    return false;
                }

                var empNamePattern = /^[a-zA-Z\s]+$/;
                if (!empNamePattern.test(empName)) {
                    errorMessageContainer.innerText = "Employee Name should contain only alphabets and spaces.";
                    document.getElementsByName("empName")[0].focus();
                    return false;
                }

                if (designation === "") {
                    errorMessageContainer.innerText = "Please fill in Designation";
                    document.getElementsByName("designation")[0].focus();
                    return false;
                }

                var designationPattern = /^[a-zA-Z\s]+$/;
                if (!designationPattern.test(designation)) {
                    errorMessageContainer.innerText = "Designation should contain only alphabets and spaces.";
                    document.getElementsByName("designation")[0].focus();
                    return false;
                }

                if (department === "") {
                    errorMessageContainer.innerText = "Please fill in Designation";
                    document.getElementsByName("department")[0].focus();
                    return false;
                }

                var departmentPattern = /^[a-zA-Z\s+#.]+$/;
                if (!departmentPattern.test(department)) {
                    errorMessageContainer.innerText = "Department should contain only alphabets and spaces.";
                    document.getElementsByName("department")[0].focus();
                    return false;
                }

                if (joinedDate === "") {
                    errorMessageContainer.innerText = "Please fill in JoinedDate";
                    document.getElementsByName("joinedDate")[0].focus();
                    return false;
                }

                var joinedDatePattern = /^\d{4}-\d{2}-\d{2}$/;
                if (!joinedDatePattern.test(joinedDate)) {
                    errorMessageContainer.innerText = "joinedDate should contain only alphabets and spaces.";
                    document.getElementsByName("joinedDate")[0].focus();
                    return false;
                }

                if (salary === "") {
                    errorMessageContainer.innerText = "Please fill in Salary";
                    document.getElementsByName("salary")[0].focus();
                    return false;
                }

                if (addressLine1 === "") {
                    errorMessageContainer.innerText = "Please fill in AddressLine1";
                    document.getElementsByName("addressLine1")[0].focus();
                    return false;
                }

                var addressLine1Pattern = /^[a-zA-Z0-9]+$/;
                if (!addressLine1Pattern.test(addressLine1)) {
                    errorMessageContainer.innerText = "AddressLine1 should contain only alphanumeric characters.";
                    document.getElementsByName("addressLine1")[0].focus();
                    return false;
                }

                if (addressLine2 === "") {
                    errorMessageContainer.innerText = "Please fill in AddressLine1";
                    document.getElementsByName("addressLine2")[0].focus();
                    return false;
                }

                var addressLine2Pattern = /^[a-zA-Z0-9]+$/;
                if (!addressLine2Pattern.test(addressLine2)) {
                    errorMessageContainer.innerText = "AddressLine2 should contain only alphanumeric characters.";
                    document.getElementsByName("addressLine2")[0].focus();
                    return false;
                }

                if (city === "") {
                    errorMessageContainer.innerText = "Please fill in City";
                    document.getElementsByName("city")[0].focus();
                    return false;
                }

                var cityPattern = /^[a-zA-Z\s]+$/;
                if (!cityPattern.test(city)) {
                    errorMessageContainer.innerText = "City should contain only alphabets and spaces.";
                    document.getElementsByName("city")[0].focus();
                    return false;
                }

                if (state === "") {
                    errorMessageContainer.innerText = "Please fill in State";
                    document.getElementsByName("state")[0].focus();
                    return false;
                }

                var statePattern = /^[a-zA-Z\s]+$/;
                if (!statePattern.test(state)) {
                    errorMessageContainer.innerText = "State should contain only alphabets and spaces.";
                    document.getElementsByName("state")[0].focus();
                    return false;
                }

                if (country === "") {
                    errorMessageContainer.innerText = "Please fill in Country";
                    document.getElementsByName("country")[0].focus();
                    return false;
                }

                var countryPattern = /^[a-zA-Z\s]+$/;
                if (!countryPattern.test(country)) {
                    errorMessageContainer.innerText = "Country should contain only alphabets and spaces.";
                    document.getElementsByName("country")[0].focus();
                    return false;
                }

                if (errorMessageContainer.innerText !== "") {
                    document.querySelector('.error-field').focus();
                    isValid = false;
                }

                // Reset error message container
                errorMessageContainer.innerText = "";
                return isValid;
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
        <form name="updateRecordsForm" action="EmployeeServlet?action=update" method="post" onsubmit="return validateForm();">
            <div class="table-container">
                <table>
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null && !errorMessage.isEmpty()) {
                    %>
                    <p class="error-message">
                        <%= errorMessage%>
                    </p>
                    <% }%>

                    <tr>
                        <td colspan="2">
                            <!-- Error message will be displayed here -->
                            <p id="errorMessage" class="error-message"></p>
                        </td>
                    </tr>
                    <tr>
                        <td>EmpID</td>
                        <td><input type="text" name="empId" value='<%=val[0]%>' readonly></td>
                    </tr>
                    <tr>
                        <td>EmpName</td>
                        <td><input type="text" name="empName" value='<%=val[1]%>'></td>
                    </tr>
                    <tr>
                        <td>Designation</td>
                        <td><input type="text" name="designation" value='<%=val[2]%>'></td>
                    </tr>
                    <tr>
                        <td>Department</td>
                        <td><input type="text" name="department" value='<%=val[3]%>'></td>
                    </tr>
                    <tr>
                        <td>JoinedDate</td>
                        <td><input type="date" name="joinedDate" value='<%=val[4]%>' placeholder="Enter JoinedDate" max="<%=java.time.LocalDate.now()%>"></td>
                    </tr>
                    <tr>
                        <td>Salary</td>
                        <td><input type="number" name="salary" value='<%=val[5]%>'></td>
                    </tr>
                    <tr>
                        <td>AddressLine1</td>
                        <td><input type="text" name="addressLine1" value='<%=val[6]%>'></td>
                    </tr>
                    <tr>
                        <td>AddressLine2</td>
                        <td><input type="text" name="addressLine2" value='<%=val[7]%>'></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city" value='<%=val[8]%>'></td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" name="state" value='<%=val[9]%>'></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" value='<%=val[10]%>'></td>
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
