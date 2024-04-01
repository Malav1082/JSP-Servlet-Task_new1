<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
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
            .tr-btn input, .tr-btn a {
                width: 50%;
                margin-left: 25%;
                margin-right: 25%;
                display: block;
                text-align: center;
                margin-bottom: 10px;
            }
            .error-message {
                color: #f44336;
                font-size: 14px;
                margin-top: 5px;
            }
        </style>
        <script>

            function togglePasswordVisibility() {
                var passwordField = document.getElementById("password");
                var toggleIcon = document.getElementById("toggleIcon");

                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    toggleIcon.innerText = "❌";
                } else {
                    passwordField.type = "password";
                    toggleIcon.innerText = "👁️";
                }
            }

            function validateForm() {
                var email = document.forms["loginForm"]["email"].value;
                var password = document.forms["loginForm"]["password"].value;
                var errorMessageContainer = document.getElementById("errorMessage");

                if (email === "") {
                    errorMessageContainer.innerText = "Email cannot be empty.";
                    document.getElementById("email").focus();
                    return false; // Prevent form submission
                }

                var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(email)) {
                    errorMessageContainer.innerText = "Please enter a valid email address.";
                    document.getElementById("email").focus();
                    return false; // Prevent form submission
                }

                if (password === "") {
                    errorMessageContainer.innerText = "Password cannot be empty.";
                    document.getElementById("password").focus();
                    return false; // Prevent form submission
                }

                var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                if (!passwordPattern.test(password)) {
                    errorMessageContainer.innerText = "Password should be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.";
                    document.getElementById("password").focus();
                    return false; // Prevent form submission
                }

                errorMessageContainer.innerText = "";
                return true; // Allow form submission
            }
        </script>
    </head>
    <body onload="focusOnField()">
        <div class="container">
            <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateForm()">
                <table style="width: 100%;">
                    <tr>
                    <h1 class="login">Login</h1>
                    </tr>
                    <!-- Display error message if exists -->
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
                        <td>
                            <input type="text" name="email" id="email" placeholder="Enter Email" <% if (request.getAttribute("email") != null) {%>value="<%= request.getAttribute("email")%>"<% } %>>
                        </td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td class="password-toggle">
                            <input type="password" name="password" id="password" placeholder="Enter Password" <% if (request.getParameter("password") != null) {%>value="<%= request.getParameter("password")%>"<% }%>>
                            <span class="toggle-password" onclick="togglePasswordVisibility()" id="toggleIcon">👁️</span>
                        </td>
                    </tr>
                    <tr class="tr-btn">
                        <td colspan="2">
                            <input type="submit" value="Login" class="login">
                        </td>
                    </tr>
                    <tr class="tr-btn">
                        <td colspan="2"><input type="Button" value="Register" onclick="location.href = 'Register.jsp';" class="register"></td>
                    </tr>
                    <tr class="tr-btn">
                        <td colspan="2"><a href="ForgotPassword.jsp" class="h-forgot">Forgot Password?</a></td>
                    </tr>
                    <tr class="tr-btn">
                        <td colspan="2"><a href="ResetPassword.jsp" class="h-reset">Reset Password</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
