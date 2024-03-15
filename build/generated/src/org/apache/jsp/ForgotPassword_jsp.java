package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ForgotPassword_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <!--         Compiled and minified CSS \n");
      out.write("                <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css\">\n");
      out.write("        \n");
      out.write("                 Compiled and minified JavaScript \n");
      out.write("                <script src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js\"></script>-->\n");
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Forgot Password</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"index.css\">\n");
      out.write("        <style>\n");
      out.write("            .password-toggle {\n");
      out.write("                position: relative;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .password-toggle input[type=\"password\"],\n");
      out.write("            .password-toggle input[type=\"text\"] {\n");
      out.write("                padding-right: 35px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .password-toggle .toggle-password {\n");
      out.write("                position: absolute;\n");
      out.write("                top: 50%;\n");
      out.write("                transform: translateY(-50%);\n");
      out.write("                cursor: pointer;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("        <script>\n");
      out.write("            function togglePasswordVisibility(passwordId, toggleIconId) {\n");
      out.write("                var passwordField = document.getElementById(passwordId);\n");
      out.write("                var toggleIcon = document.getElementById(toggleIconId);\n");
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
      out.write("            function focusOnField() {\n");
      out.write("                var errorMessage = '");
      out.print( request.getAttribute("errorMessage"));
      out.write("';\n");
      out.write("                if (errorMessage != null) {\n");
      out.write("                    switch (errorMessage) {\n");
      out.write("                        case \"Error: Enter Email\":\n");
      out.write("                            document.getElementById(\"email\").focus();\n");
      out.write("                            break;\n");
      out.write("                        case \"Error: Enter New Password\":\n");
      out.write("                            document.getElementById(\"password1\").focus();\n");
      out.write("                            break;\n");
      out.write("                        case \"Error: Confirm Password\":\n");
      out.write("                            document.getElementById(\"password2\").focus();\n");
      out.write("                            break;\n");
      out.write("                        default:\n");
      out.write("                            break;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"focusOnField()\">\n");
      out.write("        ");
String errorMessage = (String) request.getAttribute("errorMessage");
      out.write("\n");
      out.write("        ");
 if (errorMessage != null) {
      out.write("\n");
      out.write("        <p class=\"error-message\">");
      out.print( errorMessage);
      out.write("</p>\n");
      out.write("        ");
 }
      out.write("\n");
      out.write("        <form action=\"ForgotPasswordServlet\" method=\"post\">\n");
      out.write("            <table style=\"border: solid black; margin-left: 20px; align-content: 20px; margin: auto; width : 35%;\">\n");
      out.write("                <tr>\n");
      out.write("                    <td><h3>Forgot Password</h3></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Email :</td>\n");
      out.write("                    <td><input type=\"email\" name=\"email\" id=\"email\" placeholder=\"Enter Email\" ");
 if (request.getParameter("email") != null) {
      out.write("value=\"");
      out.print( request.getParameter("email"));
      out.write('"');
 }
      out.write("></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>New Password :</td>\n");
      out.write("                    <td class=\"password-toggle\">\n");
      out.write("                        <input type=\"password\" name=\"password1\" id=\"password1\" placeholder=\"New Password\" ");
 if (request.getParameter("password1") != null) {
      out.write("value=\"");
      out.print( request.getParameter("password1"));
      out.write('"');
 }
      out.write(">\n");
      out.write("                        <span class=\"toggle-password\" onclick=\"togglePasswordVisibility('password1', 'toggleIcon1')\" id=\"toggleIcon1\">👁️</span>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Confirm Password :</td>\n");
      out.write("                    <td class=\"password-toggle\">\n");
      out.write("                        <input type=\"password\" name=\"password2\" id=\"password2\" placeholder=\"Confirm Password\" ");
 if (request.getParameter("password2") != null) {
      out.write("value=\"");
      out.print( request.getParameter("password2"));
      out.write('"');
 }
      out.write(">\n");
      out.write("                        <span class=\"toggle-password\" onclick=\"togglePasswordVisibility('password2', 'toggleIcon2')\" id=\"toggleIcon2\">👁️</span>\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>\n");
      out.write("                        <input type=\"submit\" value=\"Forgot Password\">\n");
      out.write("                        <input type=\"Button\" value=\"Login\" onclick=\"location.href = 'index.jsp';\">\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
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
