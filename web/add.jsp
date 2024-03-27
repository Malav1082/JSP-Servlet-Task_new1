<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "add.jsp");
    String errorMessage = (String) request.getAttribute("errorMessage");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Records</title>                
        <link rel="stylesheet" type="text/css" href="style.css">
    <h1>Add Records</h1>
    <% if (errorMessage != null && !errorMessage.isEmpty()) {%>
    <p id="errorMessage" class="error-message">
        <%= errorMessage%>
    </p>
    <% }%>
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

            if (empId === "" || department === "" || joinedDate === "" || salary === "" || addressLine1 === "" || addressLine2 === "" || city === "" || state === "" || country === "") {
                errorMessageContainer.innerText = "Please fill in EmpID";
                return false;
            }

            var empIdPattern = /^[a-zA-Z0-9]+$/;
            if (!empIdPattern.test(empId)) {
                errorMessageContainer.innerText = "EmpID should contain only alphanumeric characters.";
                document.getElementById("empId").focus();
                return false;
            }

            if (empName === "") {
                errorMessageContainer.innerText = "Please fill in EmpName";
                return false;
            }

            var empNamePattern = /^[a-zA-Z\s]+$/;
            if (!empNamePattern.test(empName)) {
                errorMessageContainer.innerText = "EmpName should contain only alphabets and spaces.";
                document.getElementById("empName").focus();
                return false;
            }

            if (designation === "") {
                errorMessageContainer.innerText = "Please fill in Designation";
                return false;
            }

            var designationPattern = /^[a-zA-Z\s]+$/;
            if (!designationPattern.test(designation)) {
                errorMessageContainer.innerText = "EmpName should contain only alphabets and spaces.";
                document.getElementById("designation").focus();
                return false;
            }

            errorMessageContainer.innerText = "";
            return true;
        }

    </script>

</head>
<body>
    <p id="errorMessage" class="error-message"></p>
    <!--Display error message if present--> 
<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>
<form name="addRecordsForm" action="EmployeeServlet" method="post" onsubmit="return validateForm()">
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
                <td><input type="date" name="joinedDate" placeholder="Enter JoinedDate" max="<%=java.time.LocalDate.now()%>"></td>
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
                <td><input type="text" pattern="[a-zA-Z\s]+" name="city" placeholder="Enter City"></td>
            </tr>
            <tr>
                <td>State</td>
                <td><input type="text" name="state" placeholder="Enter State"></td>
            </tr>
            <tr>
                <td>Country</td>
                <td><input type="text" name="country" placeholder="Enter Country"></td>
            </tr>
        </table>
        <input type="submit" id="submit" name="Submit" value="Submit">
        <input type="Button" value="Back" onclick="location.href = 'home.jsp';" class="back">        
    </div>
            </form>
</body>
</html>

