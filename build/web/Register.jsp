<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
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
            .tr-btn input{
                width: 50%;
                margin-left: 25%;
                margin-right: 25%;
            }
        </style>
        <script>
            function togglePasswordVisibility(passwordId, toggleIconId) {
                var passwordInput = document.getElementById(passwordId);
                var toggleIcon = document.getElementById(toggleIconId);
                if (passwordInput.type === "password") {
                    passwordInput.type = "text";
                    toggleIcon.innerText = "❌";
                } else {
                    passwordInput.type = "password";
                    toggleIcon.innerText = "👁️";
                }
            }

            function validateForm() {
                var email = document.forms["registerForm"]["email"].value;
                var mobnum = document.forms["registerForm"]["mobnum"].value;
                var password1 = document.forms["registerForm"]["password1"].value;
                var password2 = document.forms["registerForm"]["password2"].value;

                // Empty field validation
                if (email === "" || mobnum === "" || password1 === "" || password2 === "") {
                    alert("Please fill in all fields.");

                    // Focus on the first empty field
                    if (email === "") {
                        document.getElementById("email").focus();
                    } else if (mobnum === "") {
                        document.getElementById("mobnum").focus();
                    } else if (password1 === "") {
                        document.getElementById("password1").focus();
                    } else {
                        document.getElementById("password2").focus();
                    }
                    return false;
                }

                // Email format validation
                var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(email)) {
                    alert("Please enter a valid email address.");
                    document.getElementById("email").focus();
                    return false;
                }

                // Mobile number format validation
                var mobnumPattern = /^\d{10}$/;
                if (!mobnumPattern.test(mobnum)) {
                    alert("Please enter a valid 10-digit mobile number.");
                    document.getElementById("mobnum").focus();
                    return false;
                }

                // Password validation
                var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
                if (!passwordPattern.test(password1)) {
                    alert("Password should be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.");
                    document.getElementById("password1").focus();
                    return false;
                }

                // Password match validation
                if (password1 !== password2) {
                    alert("Passwords do not match.");
                    document.getElementById("password1").focus();
                    return false;
                }
                return true;
            }
        </script>

    </head>
    <body onload="focusOnField()">
        <div class="container">
            <form name="registerForm" action="RegistrationServlet" method="post" onsubmit="return validateForm()">
                <table style="width : 100%;">
                    <tr>
                    <h1 class="register">Register</h1>
                    </tr>
                    <tr><% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                        <% if (errorMessage != null) {%>
                    <p class="error-message"><%= errorMessage%></p>
                    <% }%>
                    </tr>
                    <tr>
                        <td>Email :</td>
                        <td>
                            <input type="text" name="email" id="email" placeholder="Enter Email" <% if (request.getAttribute("email") != null) {%>value="<%= request.getAttribute("email")%>"<% } %>>
                        </td>
                    </tr>
                    <tr>
                        <td>Mobile Number :</td>
                        <td>
                            <input type="text" name="mobnum" id="mobnum" placeholder="Enter Mobile Number" <% if (request.getParameter("mobnum") != null) {%>value="<%= request.getParameter("mobnum")%>"<% } %>>
                        </td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td class="password-toggle">
                            <span><input type="password" name="password1" id="password1" placeholder="Enter Password" <% if (request.getParameter("password1") != null) {%>value="<%= request.getParameter("password1")%>"<% } %>></span>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password1', 'toggleIcon1')" id="toggleIcon1">👁️</span>
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password2" id="password2" placeholder="Confirm Password" <% if (request.getParameter("password2") != null) {%>value="<%= request.getParameter("password2")%>"<% }%>>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password2', 'toggleIcon2')" id="toggleIcon2">👁️</span>
                        </td>
                    </tr>
                    <tr class="tr-btn" >
                        <td colspan="2">
                            <input type="submit" value="Register" class="register">
                            <input type="Button" value="Login" onclick="location.href = 'index.jsp';" class="login">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <!-- Error message will be displayed here -->
        <% String formErrorMessage = (String) request.getAttribute("formErrorMessage"); %>
        <% if (formErrorMessage != null) {%>
        <p class="error-message"><%= formErrorMessage%></p>
        <% }%>
    </body>
</html>
