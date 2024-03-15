package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_jsp extends org.apache.jasper.runtime.HttpJspBase
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
        System.out.println(request.getSession(false) + "add.jsp");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Add Records</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
      out.write("        <script>\n");
      out.write("            // Function to extract URL parameters\n");
      out.write("            function getUrlParameter(name) {\n");
      out.write("                name = name.replace(/[\\[]/, '\\\\[').replace(/[\\]]/, '\\\\]');\n");
      out.write("                var regex = new RegExp('[\\\\?&]' + name + '=([^&#]*)');\n");
      out.write("                var results = regex.exec(location.search);\n");
      out.write("                return results === null ? '' : decodeURIComponent(results[1].replace(/\\+/g, ' '));\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            // Function to pre-fill form fields with URL parameters\n");
      out.write("            window.onload = function () {\n");
      out.write("                preFillForm(\n");
      out.write("                        getUrlParameter('empId'),\n");
      out.write("                        getUrlParameter('empName'),\n");
      out.write("                        getUrlParameter('designation'),\n");
      out.write("                        getUrlParameter('department'),\n");
      out.write("                        getUrlParameter('joinedDate'),\n");
      out.write("                        getUrlParameter('salary'),\n");
      out.write("                        getUrlParameter('addressLine1'),\n");
      out.write("                        getUrlParameter('addressLine2'),\n");
      out.write("                        getUrlParameter('city'),\n");
      out.write("                        getUrlParameter('state'),\n");
      out.write("                        getUrlParameter('country')\n");
      out.write("                        );\n");
      out.write("            };\n");
      out.write("\n");
      out.write("            // Function to pre-fill form fields with previously entered data\n");
      out.write("            function preFillForm(empId, empName, designation, department, joinedDate, salary, addressLine1, addressLine2, city, state, country) {\n");
      out.write("                document.getElementsByName(\"empId\")[0].value = empId;\n");
      out.write("                document.getElementsByName(\"empName\")[0].value = empName;\n");
      out.write("                document.getElementsByName(\"designation\")[0].value = designation;\n");
      out.write("                document.getElementsByName(\"department\")[0].value = department;\n");
      out.write("                document.getElementsByName(\"joinedDate\")[0].value = joinedDate;\n");
      out.write("                document.getElementsByName(\"salary\")[0].value = salary;\n");
      out.write("                document.getElementsByName(\"addressLine1\")[0].value = addressLine1;\n");
      out.write("                document.getElementsByName(\"addressLine2\")[0].value = addressLine2;\n");
      out.write("                document.getElementsByName(\"city\")[0].value = city;\n");
      out.write("                document.getElementsByName(\"state\")[0].value = state;\n");
      out.write("                document.getElementsByName(\"country\")[0].value = country;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Add Records</h1>\n");
      out.write("\n");
      out.write("        <!--Display error message if present--> \n");
      out.write("    <c:if test=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty errorMessage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\n");
      out.write("        <p style=\"color: red;\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${errorMessage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("    </c:if>\n");
      out.write("    <form action=\"EmployeeServlet\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"add\">\n");
      out.write("        <c:set var=\"empId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.empId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"empName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.empName}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"designation\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.designation}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"department\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.department}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"joinedDate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.joinedDate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"salary\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.salary}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"addressLine1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.addressLine1}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"addressLine2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.addressLine2}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"city\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.city}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"state\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.state}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <c:set var=\"country\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${param.country}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <td>EmpID</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z0-9]+\" name=\"empId\" placeholder=\"Enter EmpID\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>EmpName</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"empName\" placeholder=\"Enter EmpName\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Designation</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"designation\" placeholder=\"Enter Designation\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Department</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"department\" placeholder=\"Enter Department\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>JoinedDate</td>\n");
      out.write("                <td><input type=\"date\" name=\"joinedDate\" placeholder=\"Enter JoinedDate\" max=\"");
      out.print(java.time.LocalDate.now());
      out.write("\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Salary</td>\n");
      out.write("                <td><input type=\"number\" pattern=\"\\d+\" name=\"salary\" placeholder=\"Enter Salary\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>AddressLine1</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z0-9\\s]+\" name=\"addressLine1\" placeholder=\"Enter AddressLine1\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>AddressLine2</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z0-9\\s]+\" name=\"addressLine2\" placeholder=\"Enter AddressLine2\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>City</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"city\" placeholder=\"Enter City\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>State</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"state\" placeholder=\"Enter State\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td>Country</td>\n");
      out.write("                <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"country\" placeholder=\"Enter Country\" required></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("                <td colspan=\"2\"><input type=\"submit\" id=\"submit\" name=\"Submit\" value=\"Submit\"></td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("    </form>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
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
