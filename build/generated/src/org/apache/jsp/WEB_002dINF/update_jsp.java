package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class update_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Update Records</title>\n");
      out.write("        <style>\n");
      out.write("            input[type=\"text\"],\n");
      out.write("            input[type=\"date\"] {\n");
      out.write("                width: 100%;\n");
      out.write("                box-sizing: border-box; /* Include padding and border in the element's total width and height */\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Update Records</h1>\n");
      out.write("        <form action=\"EmployeeServlet\" method=\"post\">\n");
      out.write("            <!--            <input type=\"hidden\" name=\"action\" value=\"add\">-->\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>EmpID</td>\n");
      out.write("                    <td><input type=\"text\" name=\"empId\" placeholder=\"Enter EmpID\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>EmpName</td>\n");
      out.write("                    <td><input type=\"text\" name=\"empName\" placeholder=\"Enter EmpName\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Designation</td>\n");
      out.write("                    <td><input type=\"text\" name=\"designation\" placeholder=\"Enter Designation\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Department</td>\n");
      out.write("                    <td><input type=\"text\" name=\"department\" placeholder=\"Enter Department\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>JoinedDate</td>\n");
      out.write("                    <!--<td><input type=\"date\" name=\"joinedDate\" placeholder=\"Enter JoinedDate\" max=\"");
      out.print(java.time.LocalDate.now());
      out.write("\"></td>-->\n");
      out.write("                    <td><input type=\"text\" name=\"joinedDate\" placeholder=\"Enter Date\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Salary</td>\n");
      out.write("                    <td><input type=\"text\" name=\"salary\" placeholder=\"Enter Salary\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>AddressLine1</td>\n");
      out.write("                    <td><input type=\"text\" name=\"addressLine1\" placeholder=\"Enter AddressLine1\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>AddressLine2</td>\n");
      out.write("                    <td><input type=\"text\" name=\"addressLine2\" placeholder=\"Enter AddressLine2\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>City</td>\n");
      out.write("                    <td><input type=\"text\" name=\"city\" placeholder=\"Enter City\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>State</td>\n");
      out.write("                    <td><input type=\"text\" name=\"state\" placeholder=\"Enter State\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Country</td>\n");
      out.write("                    <td><input type=\"text\" name=\"country\" placeholder=\"Enter Country\"></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td colspan=\"2\"><input type=\"submit\" id=\"update\" name=\"Update\" value=\"Submit\"></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
