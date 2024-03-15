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

            function focusOnField() {
                var errorMessage = '<%= request.getAttribute("errorMessage")%>';
                if (errorMessage != null) {
                    switch (errorMessage) {
                        case "Error: Enter Email":
                            document.getElementById("email").focus();
                            break;
                        case "Error: Enter Mobile Number":
                            document.getElementById("mobnum").focus();
                            break;
                        case "Error: Enter Password":
                            document.getElementById("password1").focus();
                            break;
                        default:
                            break;
                    }
                }
            }
        </script>
    </head>
    <body onload="focusOnField()">
        <div class="container">
            <form action="RegistrationServlet" method="post">
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
                            <input type="email" name="email" id="email" placeholder="Enter Email" <% if (request.getAttribute("email") != null) {%>value="<%= request.getAttribute("email")%>"<% } %> required>
                        </td>
                    </tr>
                    <tr>
                        <td>Mobile Number :</td>
                        <td>
                            <input type="number" name="mobnum" id="mobnum" placeholder="Enter Mobile Number" <% if (request.getParameter("mobnum") != null) {%>value="<%= request.getParameter("mobnum")%>"<% } %> required>
                        </td>
                    </tr>
                    <tr>
                        <td>Password :</td>
                        <td class="password-toggle">
                            <span><input type="password" name="password1" id="password1" placeholder="Enter Password" <% if (request.getParameter("password1") != null) {%>value="<%= request.getParameter("password1")%>"<% } %> required></span>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password1', 'toggleIcon1')" id="toggleIcon1">👁️</span>
                        </td>
                    </tr>
                    <tr>
                        <td>Confirm Password :</td>
                        <td class="password-toggle">
                            <input type="password" name="password2" id="password2" placeholder="Confirm Password" <% if (request.getParameter("password2") != null) {%>value="<%= request.getParameter("password2")%>"<% }%> required>
                            <span class="toggle-password" onclick="togglePasswordVisibility('password2', 'toggleIcon2')" id="toggleIcon2">👁️</span>
                        </td>
                    </tr>
                    <tr class="tr-btn" >
                        <td colspan="2">
                            <input type="submit" value="Register" class="register">
                            <input type="Button" value="Login" onclick="location.href = 'index.jsp';">
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
