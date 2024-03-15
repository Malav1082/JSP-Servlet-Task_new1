package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;

public final class update_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


            String[] val = new String[11];
        
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
        System.out.println(request.getSession(false) + "update.jsp");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Update Records</title>        \n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Update Records</h1>\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");

            Connection conn = null;
            ResultSet rs = null;

            try {
                conn = DriverManager.getConnection("jdbc:sqlite:D://Java Training//Task 4//RM//Records.db");
                PreparedStatement stmt = conn.prepareStatement("SELECT M.EmpID, M.EmpName, M.Designation, M.Department, M.JoinedDate, M.Salary, D.AddressLine1, D.AddressLine2, D.City, D.State, D.Country FROM TblEmployeeMaster M JOIN TblEmployeeDetail D ON M.MastCode = D.EmpCode where EmpID = ?");
                stmt.setString(1, request.getParameter("empId"));
                rs = stmt.executeQuery();
                for (int i = 1; i <= 11; i++) {
                    val[i - 1] = rs.getString(i);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Close the connection
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        
      out.write("\n");
      out.write("        <form action=\"EmployeeServlet?action=update\" method=\"post\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>EmpID</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z0-9]+\" name=\"empId\" value='");
      out.print(val[0]);
      out.write("' readonly></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>EmpName</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"empName\" value='");
      out.print(val[1]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Designation</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"designation\" value='");
      out.print(val[2]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Department</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"department\" value='");
      out.print(val[3]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>JoinedDate</td>\n");
      out.write("                    <td><input type=\"date\" name=\"joinedDate\" value='");
      out.print(val[4]);
      out.write("' placeholder=\"Enter JoinedDate\" max=\"");
      out.print(java.time.LocalDate.now());
      out.write("\" required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Salary</td>\n");
      out.write("                    <td><input type=\"number\" pattern=\"\\d+\" name=\"salary\" value='");
      out.print(val[5]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>AddressLine1</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z0-9\\s]+\" name=\"addressLine1\" value='");
      out.print(val[6]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>AddressLine2</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z0-9\\s]+\" name=\"addressLine2\" value='");
      out.print(val[7]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>City</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"city\" value='");
      out.print(val[8]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>State</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"state\" value='");
      out.print(val[9]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Country</td>\n");
      out.write("                    <td><input type=\"text\" pattern=\"[a-zA-Z\\s]+\" name=\"country\" value='");
      out.print(val[10]);
      out.write("' required></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td colspan=\"2\"><input type=\"submit\" id=\"submit\" name=\"Submit\" value=\"Submit\"></td>\n");
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
