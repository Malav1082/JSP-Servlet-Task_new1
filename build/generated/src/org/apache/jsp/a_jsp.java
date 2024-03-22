package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class a_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Sign Up</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">\n");
      out.write("        <style>\n");
      out.write("            .password-toggle {\n");
      out.write("                position: relative;\n");
      out.write("            }\n");
      out.write("            .password-toggle input[type=\"password\"],\n");
      out.write("            .password-toggle input[type=\"text\"] {\n");
      out.write("                padding-right: 35px;\n");
      out.write("            }\n");
      out.write("            .password-toggle .toggle-password {\n");
      out.write("                position: absolute;\n");
      out.write("                right: 5px;\n");
      out.write("                top: 50%;\n");
      out.write("                transform: translateY(-50%);\n");
      out.write("                cursor: pointer;\n");
      out.write("            }\n");
      out.write("            .tr-btn input{\n");
      out.write("                width: 50%;\n");
      out.write("                margin-left: 25%;\n");
      out.write("                margin-right: 25%;\n");
      out.write("            }\n");
      out.write("            .error-message {\n");
      out.write("                color: #f44336; /* Red color */\n");
      out.write("                font-size: 14px;\n");
      out.write("                margin-top: 5px;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <script>\n");
      out.write("            function togglePasswordField(id) {\n");
      out.write("            console.log(id)\n");
      out.write("            var passwordField = document.getElementById(id);\n");
      out.write("            var eyeIcon = document.getElementById(\"eyeIcon-\" + id);\n");
      out.write("\n");
      out.write("            // Toggle password field type between 'password' and 'text'\n");
      out.write("            if (passwordField.type === \"password\") {\n");
      out.write("                passwordField.type = \"text\";\n");
      out.write("                eyeIcon.className = \"fa fa-eye-slash\"; // Change icon to slashed eye for hiding password\n");
      out.write("            } else {\n");
      out.write("                passwordField.type = \"password\";\n");
      out.write("                eyeIcon.className = \"fa fa-eye\"; // Change icon back to eye for showing password\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<span class=\"togglePassword\" onclick=\"togglePasswordField('password')\">\n");
      out.write("                                                <i id=\"eyeIcon-password\" class=\"fa fa-eye\" aria-hidden=\"true\"></i>\n");
      out.write("                                            </span>\n");
      out.write("\n");
      out.write("            function validateForm() {\n");
      out.write("                var email = document.forms[\"registerForm\"][\"email\"].value;\n");
      out.write("                var mobnum = document.forms[\"registerForm\"][\"mobnum\"].value;\n");
      out.write("                var password1 = document.forms[\"registerForm\"][\"password1\"].value;\n");
      out.write("                var password2 = document.forms[\"registerForm\"][\"password2\"].value;\n");
      out.write("                var errorMessageContainer = document.getElementById(\"errorMessage\");\n");
      out.write("\n");
      out.write("                // Empty field validation\n");
      out.write("                if (email === \"\") {\n");
      out.write("                    errorMessageContainer.innerText = \"Please fill in all fields.\";\n");
      out.write("                    document.getElementById(\"email\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                if (mobnum === \"\") {\n");
      out.write("                    errorMessageContainer.innerText = \"Please fill in all fields.\";\n");
      out.write("                    document.getElementById(\"mobnum\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                if (password1 === \"\") {\n");
      out.write("                    errorMessageContainer.innerText = \"Please fill in all fields.\";\n");
      out.write("                    document.getElementById(\"password1\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                if (password2 === \"\") {\n");
      out.write("                    errorMessageContainer.innerText = \"Please fill in all fields.\";\n");
      out.write("                    document.getElementById(\"password2\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // Email format validation\n");
      out.write("                var emailPattern = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;\n");
      out.write("                if (!emailPattern.test(email)) {\n");
      out.write("                    errorMessageContainer.innerText = \"Please enter a valid email address.\";\n");
      out.write("                    document.getElementById(\"email\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // Mobile number format validation\n");
      out.write("                var mobnumPattern = /^\\d{10}$/;\n");
      out.write("                if (!mobnumPattern.test(mobnum)) {\n");
      out.write("                    errorMessageContainer.innerText = \"Please enter a valid 10-digit mobile number.\";\n");
      out.write("                    document.getElementById(\"mobnum\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // Password validation\n");
      out.write("                var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$/;\n");
      out.write("                if (!passwordPattern.test(password1)) {\n");
      out.write("                    errorMessageContainer.innerText = \"Password should be at least 8 characters long, contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character.\";\n");
      out.write("                    document.getElementById(\"password1\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // Password match validation\n");
      out.write("                if (password1 !== password2) {\n");
      out.write("                    errorMessageContainer.innerText = \"Passwords do not match.\";\n");
      out.write("                    document.getElementById(\"password2\").focus();\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                // If all validations pass, clear any previous error message\n");
      out.write("                errorMessageContainer.innerText = \"\";\n");
      out.write("                return true;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"focusOnField()\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <form name=\"registerForm\" action=\"RegistrationServlet\" method=\"post\" onsubmit=\"return validateForm()\">\n");
      out.write("                <table style=\"width : 100%;\">\n");
      out.write("                    <tr>\n");
      out.write("                    <h1 class=\"register\">Register</h1>\n");
      out.write("                    </tr>\n");
      out.write("                    <!-- Error message will be displayed here -->\n");
      out.write("                    ");

                        String errorMessage = (String) request.getAttribute("errorMessage");
                        if (errorMessage != null && !errorMessage.isEmpty()) {
                    
      out.write("\n");
      out.write("                    <p id=\"errorMessage\" class=\"error-message\">\n");
      out.write("                        ");
      out.print( errorMessage);
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td colspan=\"2\">\n");
      out.write("                            <!-- Error message will be displayed here -->\n");
      out.write("                            <p id=\"errorMessage\" class=\"error-message\"></p>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Email :</td>\n");
      out.write("                        <td>\n");
      out.write("                            <input type=\"text\" name=\"email\" id=\"email\" placeholder=\"Enter Email\" ");
 if (request.getAttribute("email") != null) {
      out.write("value=\"");
      out.print( request.getAttribute("email"));
      out.write('"');
 } 
      out.write(">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Mobile Number :</td>\n");
      out.write("                        <td>\n");
      out.write("                            <input type=\"text\" name=\"mobnum\" id=\"mobnum\" placeholder=\"Enter Mobile Number\" ");
 if (request.getParameter("mobnum") != null) {
      out.write("value=\"");
      out.print( request.getParameter("mobnum"));
      out.write('"');
 } 
      out.write(">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Password :</td>\n");
      out.write("                        <td class=\"password-toggle\">\n");
      out.write("                            <span><input type=\"password\" name=\"password1\" id=\"password1\" placeholder=\"Enter Password\" ");
 if (request.getParameter("password1") != null) {
      out.write("value=\"");
      out.print( request.getParameter("password1"));
      out.write('"');
 } 
      out.write("></span>\n");
      out.write("                            <span class=\"toggle-password\" onclick=\"togglePasswordVisibility('password1', 'toggleIcon1')\" id=\"toggleIcon1\">👁️</span>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Confirm Password :</td>\n");
      out.write("                        <td class=\"password-toggle\">\n");
      out.write("                            <input type=\"password\" name=\"password2\" id=\"password2\" placeholder=\"Confirm Password\" ");
 if (request.getParameter("password2") != null) {
      out.write("value=\"");
      out.print( request.getParameter("password2"));
      out.write('"');
 }
      out.write(">\n");
      out.write("                            <span class=\"toggle-password\" onclick=\"togglePasswordVisibility('password2', 'toggleIcon2')\" id=\"toggleIcon2\">👁️</span>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"tr-btn\" >\n");
      out.write("                        <td colspan=\"2\">\n");
      out.write("                            <input type=\"submit\" value=\"Register\" class=\"register\">\n");
      out.write("                            <input type=\"Button\" value=\"Login\" onclick=\"location.href = 'index.jsp';\" class=\"login\">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
