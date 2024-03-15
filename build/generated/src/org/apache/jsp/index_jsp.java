package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
        System.out.println(request.getSession(false) + "index.jsp");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html> \n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">\n");
      out.write("<!--        <style>\n");
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
      out.write("\n");
      out.write("        </style>-->\n");
      out.write("        <script>\n");
      out.write("            function togglePasswordVisibility() {\n");
      out.write("                var passwordField = document.getElementById(\"password\");\n");
      out.write("                var toggleIcon = document.getElementById(\"toggleIcon\");\n");
      out.write("\n");
      out.write("                if (passwordField.type === \"password\") {\n");
      out.write("                    passwordField.type = \"text\";\n");
      out.write("                    toggleIcon.innerText = \"❌\";\n");
      out.write("                } else {\n");
      out.write("                    passwordField.type = \"password\";\n");
      out.write("                    toggleIcon.innerText = \"👁️\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function validateForm() {\n");
      out.write("                var email = document.forms[\"loginForm\"][\"email\"].value;\n");
      out.write("                var password = document.forms[\"loginForm\"][\"password\"].value;\n");
      out.write("                if (email === \"\" || password === \"\") {\n");
      out.write("                    alert(\"Please fill in all fields.\");\n");
      out.write("                    return false;\n");
      out.write("                }\n");
      out.write("                return true;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <form name=\"loginForm\" action=\"LoginServlet\" method=\"post\" onsubmit=\"return validateForm()\">\n");
      out.write("            ");
 String successMessage = request.getParameter("success");
                if (successMessage != null && !successMessage.isEmpty()) {
      out.write("\n");
      out.write("            <div class=\"alert alert-success\">\n");
      out.write("                ");
      out.print( successMessage);
      out.write("\n");
      out.write("            </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("            <!-- Display error message if exists -->\n");
      out.write("            ");
 String errorMessage = (String) request.getAttribute("errorMessage"); 
      out.write("\n");
      out.write("            ");
 if (errorMessage != null && !errorMessage.isEmpty()) {
      out.write("\n");
      out.write("            <p style=\"color: red;\">");
      out.print( errorMessage);
      out.write("</p>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <h3>Login</h3>\n");
      out.write("                <table style=\"width: 100%;\">\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Email :</td>\n");
      out.write("                        <td><input type=\"email\" name=\"email\" placeholder=\"Enter Email\" required></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Password :</td>\n");
      out.write("                        <td class=\"password-toggle\">\n");
      out.write("                            <input type=\"password\" name=\"password\" id=\"password\" placeholder=\"Enter Password\" ");
 if (request.getParameter("password") != null) {
      out.write("value=\"");
      out.print( request.getParameter("password"));
      out.write('"');
 }
      out.write(" required>\n");
      out.write("                            <span class=\"toggle-password\" onclick=\"togglePasswordVisibility()\" id=\"toggleIcon\">👁️</span>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"tr-btn\">\n");
      out.write("                        <td><input type=\"submit\" name=\"submit\" value=\"Login\" class=\"login\"></td>\n");
      out.write("                        <td><input type=\"Button\" value=\"Register\" onclick=\"location.href = 'Register.jsp';\" class=\"register\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr class=\"tr-btn\">\n");
      out.write("                        <td><a href=\"ForgotPassword.jsp\" class=\"forgot\">Forgot Password?</a></td>\n");
      out.write("                        <td><a href=\"ResetPassword.jsp\" class=\"reset\">Reset Password</a></td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
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
