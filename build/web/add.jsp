<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "add.jsp");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Records</title>
        <link rel="stylesheet" type="text/css" href="style.css">
        <script>

            // Function to extract URL parameters
            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }

            // Function to pre-fill form fields with URL parameters
            window.onload = function () {
                preFillForm(
                        getUrlParameter('empId'),
                        getUrlParameter('empName'),
                        getUrlParameter('designation'),
                        getUrlParameter('department'),
                        getUrlParameter('joinedDate'),
                        getUrlParameter('salary'),
                        getUrlParameter('addressLine1'),
                        getUrlParameter('addressLine2'),
                        getUrlParameter('city'),
                        getUrlParameter('state'),
                        getUrlParameter('country')
                        );
            };

            // Function to pre-fill form fields with previously entered data
            function preFillForm(empId, empName, designation, department, joinedDate, salary, addressLine1, addressLine2, city, state, country) {
                document.getElementsByName("empId")[0].value = empId;
                document.getElementsByName("empName")[0].value = empName;
                document.getElementsByName("designation")[0].value = designation;
                document.getElementsByName("department")[0].value = department;
                document.getElementsByName("joinedDate")[0].value = joinedDate;
                document.getElementsByName("salary")[0].value = salary;
                document.getElementsByName("addressLine1")[0].value = addressLine1;
                document.getElementsByName("addressLine2")[0].value = addressLine2;
                document.getElementsByName("city")[0].value = city;
                document.getElementsByName("state")[0].value = state;
                document.getElementsByName("country")[0].value = country;
            }

            function showDropdown() {
                document.getElementById("dropdownContent").style.display = "block";
            }

            function hideDropdown() {
                document.getElementById("dropdownContent").style.display = "none";
            }
            
            function validateForm() {
                var empId = document.forms["addRecordsForm"]["empId"].value;
                var empName = document.forms["addRecordsForm"]["empName"].value;
                var designation = document.forms["addRecordsForm"]["designation"].value;
                var department = document.forms["addRecordsForm"]["department"].value;
                var joinedDate = document.forms["addRecordsForm"]["joinedDate"].value;
                var salary = document.forms["addRecordsForm"]["salary"].value;
                var addressLine1 = document.forms["addRecordsForm"]["addressLine1"].value;
                var addressLine2 = document.forms["addRecordsForm"]["addressLine2"].value;
                var city = document.forms["addRecordsForm"]["city"].value;
                var state = document.forms["addRecordsForm"]["state"].value;
                var country = document.forms["addRecordsForm"]["country"].value;
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
                    focusOnField();
                    isValid = false;
                }

                // Reset error message container
                errorMessageContainer.innerText = "";
                return isValid;
            }

        </script>

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
        <h1>Add Records</h1>

        <!--        Display error message if present 
            <c:if test="${not empty errorMessage}">
                <p style="color: red;">${errorMessage}</p>
            </c:if>-->
        <div class="table-container">
            <form name="addRecordsForm" action="EmployeeServlet" method="post" onsubmit="return validateForm();">
                <input type="hidden" name="action" value="add">
                <c:set var="empId" value="${param.empId}" />
                <c:set var="empName" value="${param.empName}" />
                <c:set var="designation" value="${param.designation}" />
                <c:set var="department" value="${param.department}" />
                <c:set var="joinedDate" value="${param.joinedDate}" />
                <c:set var="salary" value="${param.salary}" />
                <c:set var="addressLine1" value="${param.addressLine1}" />
                <c:set var="addressLine2" value="${param.addressLine2}" />
                <c:set var="city" value="${param.city}" />
                <c:set var="state" value="${param.state}" />
                <c:set var="country" value="${param.country}" />
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
                        <td><input type="text" name="empId" placeholder="Enter EmpID"></td>
                    </tr>
                    <tr>
                        <td>EmpName</td>
                        <td><input type="text" name="empName" placeholder="Enter EmpName"></td>
                    </tr>
                    <tr>
                        <td>Designation</td>
                        <td><input type="text" name="designation" placeholder="Enter Designation"></td>
                    </tr>
                    <tr>
                        <td>Department</td>
                        <td><input type="text" name="department" placeholder="Enter Department"></td>
                    </tr>
                    <tr>
                        <td>JoinedDate</td>
                        <td><input type="date" name="joinedDate" placeholder="Enter JoinedDate" max="<%=java.time.LocalDate.now()%>" ></td>
                    </tr>
                    <tr>
                        <td>Salary</td>
                        <td><input type="number" name="salary" placeholder="Enter Salary"></td>
                    </tr>
                    <tr>
                        <td>AddressLine1</td>
                        <td><input type="text" name="addressLine1" placeholder="Enter AddressLine1"></td>
                    </tr>
                    <tr>
                        <td>AddressLine2</td>
                        <td><input type="text" name="addressLine2" placeholder="Enter AddressLine2"></td>
                    </tr>
                    <tr>
                        <td>City</td>
                        <td><input type="text" name="city" placeholder="Enter City"></td>
                    </tr>
                    <tr>
                        <td>State</td>
                        <td><input type="text" name="state" placeholder="Enter State"></td>
                    </tr>
                    <tr>
                        <td>Country</td>
                        <td><input type="text" name="country" placeholder="Enter Country"></td>
                    </tr>
                    <tr>
                        <td colspan="2" class="td-btn">
                            <input type="submit" id="submit" value="Submit">
                            <input type="Button" value="Back" onclick="location.href = 'home.jsp';" class="back">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
    <footer>
        <p class="footer-text">
            Copyrights &copy; reserved for Employee Records. All Rights Reserved.
        </p>
    </footer>
</html>