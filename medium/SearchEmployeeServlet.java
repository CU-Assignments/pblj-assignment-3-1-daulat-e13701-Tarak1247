import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SearchEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empId = request.getParameter("empId");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/yourDatabase";
        String dbUser = "root";
        String dbPassword = "yourPassword";
        
        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // Query to fetch employee by ID
            String query = "SELECT * FROM employees WHERE emp_id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, empId);
            rs = stmt.executeQuery();
            
            // Set response type and prepare output
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            if (rs.next()) {
                out.println("<h3>Employee Found:</h3>");
                out.println("<p>Employee ID: " + rs.getString("emp_id") + "</p>");
                out.println("<p>Employee Name: " + rs.getString("emp_name") + "</p>");
                out.println("<p>Employee Department: " + rs.getString("emp_dept") + "</p>");
            } else {
                out.println("<h3>No employee found with ID: " + empId + "</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database connection error.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
