<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" type="text/css" href="index.css">
        <style>
            .password-toggle {
                position: relative;
            }
            .password-toggle input[type="password"],
            .password-toggle input[type="text"] {
                padding-right: 35px;
            }
            .password-toggle .toggle-password {
                position: relative;
                left: -30px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
            }

            .tr-btn input{
                width: 50%;
                margin-left: 25%;
                margin-right: 25%;
            }
            .error-message {
                color: #f44336;
                font-size: 14px;
                margin-top: 5px;
            }
        </style>
        <script>
            function togglePasswordVisibility(passwordId, toggleIconId) {
                var passwordField = document.getElementById(passwordId);
                var toggleIcon = document.getElementById(toggleIconId);

                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    toggleIcon.innerHTML = "❌";
                } else {
                    passwordField.type = "password";
                    toggleIcon.innerHTML = "👁️";
                }
            }
            function validateForm() {
                var email = document.forms["resetForm"]["email"].value;
                var password0 = document.forms["resetForm"]["password0"].value;
                var password1 = document.forms["resetForm"]["password1"].value;
                var password2 = document.forms["resetForm"]["password2"].value;
                var errorMessageContainer = document.getElementById("errorMessage");

                if (email === "") {
                    errorMessageContainer.innerText = "Email cannot be Empty.";
                    document.getElementById("email").focus();
                    return false;
                }

                var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(email)) {
                    errorMessageContainer.innerText = "Please enter a valid email address.";
                    document.getElementById("email").focus();
                    return false;
                }

                if (password0 === "") {
                    errorMessageContainer.innerText = "Old Password cannot be Empty.";
                    document.getElementById("password0").focus();
                    return false;
                }

                var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                if (!passwordPattern.test(password0)) {
                    errorMessageContainer.innerText = "Password should be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.";
                    document.getElementById("password0").focus();
                    return false;
                }

                if (password1 === "") {
                    errorMessageContainer.innerText = "New Password cannot be Empty.";
                    document.getElementById("password1").focus();
                    return false;
                }

                var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                if (!passwordPattern.test(password1)) {
                    errorMessageContainer.innerText = "Password should be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.";
                    document.getElementById("password1").focus();
                    return false;
                }

                if (password0 === password1) {
                    errorMessageContainer.innerText = "New password should be different from the old password.";
                    document.getElementById("password1").focus();
                    return false;
                }

                if (password2 === "") {
                    errorMessageContainer.innerText = "Confirm Password cannot be Empty.";
                    document.getElementById("password2").focus();
                    return false;
                }

                if (password1 !== password2) {
                    errorMessageContainer.innerText = "Passwords do not match";
                    document.getElementById("password2").focus();
                    return false;
                }
                errorMessageContainer.innerText = "";
                return true;
            }
        </script>
    </head>
    <body onload="focusOnField()">
        <div class="container">
            <form name="resetForm" action="ResetPasswordServlet" method="post" onsubmit="return validateForm()">
                <table style="width : 100%;">
                    <tr>
                    <h1 class="reset">Reset Password</h1>
                    </tr>
                    <!-- Error message will be displayed here -->
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null && !errorMessage.isEmpty()) {
                    %>
                    <p id="errorMessage" class="error-message">
                        <%= errorMessage%>
                    </p>
                    <% } %>
                    <tr>
                        <td colspan="2">
                            <!-- Error message will be displayed here -->
                            <p id="errorMessage" class="error-message"></p>
                        </td>
                    </tr>
                    <tr>
                        <td>Email :</td>
                        <td><input type="text" name="email" id="email" placeholder="Enter Email" <% if (request.getParameter("email") != null) {%>value="<%= request.getParameter("email")%>"<% } %>></td>
                    </tr>
                    <tr>
                        <td>Old Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password0" id="password0" placeholder="Old Password" <% if (request.getParameter("password0") != null) {%>value="<%= request.getParameter("password0")%>"<% } %>>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password0', 'toggleIcon1')" id="toggleIcon1">👁️</span>
                        </td>
                    </tr>
                    <tr>
                        <td>New Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password1" id="password1" placeholder="New Password" <% if (request.getParameter("password1") != null) {%>value="<%= request.getParameter("password1")%>"<% } %>>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password1', 'toggleIcon2')" id="toggleIcon2">👁️</span>
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password2" id="password2" placeholder="Confirm Password" <% if (request.getParameter("password2") != null) {%>value="<%= request.getParameter("password2")%>"<% }%>>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password2', 'toggleIcon3')" id="toggleIcon3">👁️</span>
                        </td>
                    </tr>
                    <tr class="tr-btn">
                        <td colspan="2">
                            <input type="submit" value="Reset Password" class="btn-reset">
                            <input type="Button" value="Login" onclick="location.href = 'index.jsp';" class="login">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
