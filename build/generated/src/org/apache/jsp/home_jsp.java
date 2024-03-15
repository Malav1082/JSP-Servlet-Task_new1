package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
        System.out.println(request.getSession(false) + "home.jsp");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Home Page</title>\n");
      out.write("    <style>\n");
      out.write("        table {\n");
      out.write("            border-collapse: collapse;\n");
      out.write("            width: 100%;\n");
      out.write("        }\n");
      out.write("        th, td {\n");
      out.write("            border: 1px solid black;\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 8px;\n");
      out.write("            width: 100px;\n");
      out.write("        }\n");
      out.write("        th {\n");
      out.write("            background-color: yellow;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .add-button {\n");
      out.write("            background-color: blue;\n");
      out.write("            border: none;\n");
      out.write("            color: white;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            text-decoration: none;\n");
      out.write("            display: inline-block;\n");
      out.write("            font-size: 16px;\n");
      out.write("            margin: 4px 2px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .update-button {\n");
      out.write("            background-color: green;\n");
      out.write("            border: none;\n");
      out.write("            color: white;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            text-decoration: none;\n");
      out.write("            display: inline-block;\n");
      out.write("            font-size: 16px;\n");
      out.write("            margin: 4px 2px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .delete-button{\n");
      out.write("            background-color: red;\n");
      out.write("            border: none;\n");
      out.write("            color: white;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            text-decoration: none;\n");
      out.write("            display: inline-block;\n");
      out.write("            font-size: 16px;\n");
      out.write("            margin: 4px 2px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .logout {\n");
      out.write("            background-color: black;\n");
      out.write("            border: none;\n");
      out.write("            color: white;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            text-decoration: none;\n");
      out.write("            display: inline-block;\n");
      out.write("            font-size: 16px;\n");
      out.write("            margin: 4px 2px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <!-- Display Records -->\n");
      out.write("    <h2>Records:</h2>\n");
      out.write("\n");
      out.write("    <!-- Add Record -->\n");
      out.write("    <form action=\"add.jsp\" method=\"post\">\n");
      out.write("        <input type=\"submit\" value=\"Add\" class=\"add-button\">\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <!--Logout-->\n");
      out.write("    <form action=\"logout\" method=\"post\">\n");
      out.write("        <input type=\"submit\" value=\"Logout\" class=\"logout\">\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <form>\n");
      out.write("        <table>\n");
      out.write("            <thead>\n");
      out.write("                <tr>\n");
      out.write("                    <th>EmpID</th>\n");
      out.write("                    <th>EmpName</th>\n");
      out.write("                    <th>Designation</th>\n");
      out.write("                    <th>Department</th>\n");
      out.write("                    <th>JoinedDate</th>\n");
      out.write("                    <th>Salary</th>\n");
      out.write("                    <th>AddressLine1</th>\n");
      out.write("                    <th>AddressLine2</th>\n");
      out.write("                    <th>City</th>\n");
      out.write("                    <th>State</th>\n");
      out.write("                    <th>Country</th>\n");
      out.write("                    <th>Update</th>\n");
      out.write("                    <th>Delete</th>\n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("            <tbody>\n");
      out.write("                ");

                    Connection conn = null;
                    try {
                        conn = DriverManager.getConnection("jdbc:sqlite:D://Java Training//Task 4//RM//Records.db");
                        Statement stmt = conn.createStatement();
                        String query = "SELECT M.EmpID, M.EmpName, M.Designation, M.Department, M.JoinedDate, M.Salary, D.AddressLine1, D.AddressLine2, D.City, D.State, D.Country FROM TblEmployeeMaster M JOIN TblEmployeeDetail D ON M.MastCode = D.EmpCode";
                        ResultSet rs = stmt.executeQuery(query);

                        // Iterate over the result set and display records in the table
                        while (rs.next()) {
                
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print( rs.getString("EmpID"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("EmpName"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("Designation"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("Department"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("JoinedDate"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("Salary"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("AddressLine1"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("AddressLine2"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("City"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("State"));
      out.write("</td>\n");
      out.write("                    <td>");
      out.print( rs.getString("Country"));
      out.write("</td>\n");
      out.write("                    <td><a class=\"update-link update-button\" href=\"update.jsp?empId=");
      out.print( rs.getString("EmpID"));
      out.write("\">Update</a></td>\n");
      out.write("                    <td><button class=\"delete-button\" data-empid=\"");
      out.print( rs.getString("EmpID"));
      out.write("\">Delete</button></td>\n");
      out.write("                </tr>\n");
      out.write("                ");

                        }
                        // Close connections
                        rs.close();
                        stmt.close();
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        // Close the connection
                        if (conn != null) {
                            try {
                                conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                
      out.write("\n");
      out.write("            </tbody>\n");
      out.write("        </table>\n");
      out.write("        <!-- Script to handle Update and Delete actions -->\n");
      out.write("        <script>\n");
      out.write("            document.querySelectorAll('.delete-button').forEach(button => {\n");
      out.write("                button.addEventListener('click', function () {\n");
      out.write("                    const empId = this.getAttribute('data-empid');\n");
      out.write("\n");
      out.write("                    // Display confirmation dialog\n");
      out.write("                    const confirmed = confirm('Are you sure you want to delete the record?');\n");
      out.write("\n");
      out.write("                    // If user confirms, send AJAX request to servlet to delete record\n");
      out.write("                    if (confirmed) {\n");
      out.write("                        fetch('EmployeeServlet?action=delete&empId=' + empId, {\n");
      out.write("                            method: 'POST'\n");
      out.write("                        }).then(response => {\n");
      out.write("                            if (response.ok) {\n");
      out.write("                                // Reload page after successful deletion\n");
      out.write("                                window.location.reload();\n");
      out.write("                            } else {\n");
      out.write("                                console.error('Error deleting record');\n");
      out.write("                            }\n");
      out.write("                        }).catch(error => {\n");
      out.write("                            console.error('Error deleting record:', error);\n");
      out.write("                        });\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            document.querySelectorAll('tr[data-empid]').forEach(row => {\n");
      out.write("                row.addEventListener('click', function () {\n");
      out.write("                    const empId = this.getAttribute('data-empid');\n");
      out.write("                    window.location.href = 'update.jsp?empId=' + empId;\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("        </script>\n");
      out.write("    </tbody>\n");
      out.write("</table>\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("</body>\n");
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
