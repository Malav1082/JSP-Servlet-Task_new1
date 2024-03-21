<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <% String successMessage = request.getParameter("success");
        if (successMessage != null && !successMessage.isEmpty()) {%>
    <div class="alert alert-success">
        <%= successMessage%>
    </div>
    <% } %>

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
            position: absolute;
            right: 5px;
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

            // Empty field validation
            if (email === "" || password === "") {
                alert("Please fill in all fields.");

                // Focus on the first empty field
                if (email === "") {
                    document.forms["loginForm"]["email"].focus();
                } else {
                    document.forms["loginForm"]["password"].focus();
                }

                return false;
            }
            // Email format validation
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                alert("Please enter a valid email address.");
                document.forms["loginForm"]["email"].focus();
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="container">
        <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateForm()">
            <table style="width: 100%;">
                <tr>
                <h1 class="login">Login</h1>
                <!-- Display error message if exists -->
                <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null && !errorMessage.isEmpty()) {%>
                <p style="color: red;"><%= errorMessage%></p>
                <% }%>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><input type="text" name="email" placeholder="Enter Email"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td class="password-toggle">
                        <input type="password" name="password" id="password" placeholder="Enter Password" <% if (request.getParameter("password") != null) {%>value="<%= request.getParameter("password")%>"<% }%>>
                        <span class="toggle-password" onclick="togglePasswordVisibility()" id="toggleIcon">👁️</span>
                    </td>
                </tr>
                <tr class="tr-btn">
                    <td colspan="2"><input type="submit" name="submit" value="Login" class="login"></td>
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
