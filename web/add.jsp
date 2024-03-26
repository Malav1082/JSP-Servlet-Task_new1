<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "add.jsp");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Records</title>                
        <link rel="stylesheet" type="text/css" href="style.css">
    <h1>Add Records</h1>
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
        </script>

    </head>
    <body>

        <!--Display error message if present--> 
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <form action="EmployeeServlet" method="post">
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
        <div class="table-container">
            <table>
                <tr>
                    <td>EmpID</td>
                    <td><input type="text" pattern="[a-zA-Z0-9]+" name="empId" placeholder="Enter EmpID" required></td>
                </tr>
                <tr>
                    <td>EmpName</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="empName" placeholder="Enter EmpName" required></td>
                </tr>
                <tr>
                    <td>Designation</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="designation" placeholder="Enter Designation" required></td>
                </tr>
                <tr>
                    <td>Department</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="department" placeholder="Enter Department" required></td>
                </tr>
                <tr>
                    <td>JoinedDate</td>
                    <td><input type="date" name="joinedDate" placeholder="Enter JoinedDate" max="<%=java.time.LocalDate.now()%>" required></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" pattern="\d+" name="salary" placeholder="Enter Salary" required></td>
                </tr>
                <tr>
                    <td>AddressLine1</td>
                    <td><input type="text" pattern="[a-zA-Z0-9\s]+" name="addressLine1" placeholder="Enter AddressLine1" required></td>
                </tr>
                <tr>
                    <td>AddressLine2</td>
                    <td><input type="text" pattern="[a-zA-Z0-9\s]+" name="addressLine2" placeholder="Enter AddressLine2" required></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="city" placeholder="Enter City" required></td>
                </tr>
                <tr>
                    <td>State</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="state" placeholder="Enter State" required></td>
                </tr>
                <tr>
                    <td>Country</td>
                    <td><input type="text" pattern="[a-zA-Z\s]+" name="country" placeholder="Enter Country" required></td>
                </tr>
                <tr>
                    <td><input type="submit" id="submit" name="Submit" value="Submit"></td>
                    <td><input type="Button" value="Back" onclick="location.href = 'home.jsp';" class="back"></td>
                </tr>
            </table>
        </div>
    </body>
</html>

