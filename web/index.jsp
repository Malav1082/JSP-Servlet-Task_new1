<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%        System.out.println(request.getSession(false) + "index.jsp");%>
<!DOCTYPE html>
<html> 
    <head>
                <% String successMessage = request.getParameter("success");
                if (successMessage != null && !successMessage.isEmpty()) {%>
                <div class="alert alert-success">
                    <%= successMessage%>
                </div>
                <% } %>
                <!-- Display error message if exists -->
                <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null && !errorMessage.isEmpty()) {%>
                <p style="color: red;"><%= errorMessage%></p>
                <% }%>

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
                if (email === "" || password === "") {
                    alert("Please fill in all fields.");
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
                    </tr>
                    <tr>
                        <td>Email :</td>
                        <td><input type="email" name="email" placeholder="Enter Email" required></td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password" id="password" placeholder="Enter Password" <% if (request.getParameter("password") != null) {%>value="<%= request.getParameter("password")%>"<% }%> required>
                            <span class="toggle-password" onclick="togglePasswordVisibility()" id="toggleIcon">👁️</span>
                        </td>
                    </tr>
                    <tr class="tr-btn">
                        <td><input type="submit" name="submit" value="Login" class="login"></td>
                        <td><input type="Button" value="Register" onclick="location.href = 'Register.jsp';" class="register"></td>
                    </tr>
                    <tr class="tr-btn">
                        <td><a href="ForgotPassword.jsp" class="h-forgot">Forgot Password?</a></td>
                        <td><a href="ResetPassword.jsp" class="h-reset">Reset Password</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
