import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SaveAttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String course = request.getParameter("course");
        String date = request.getParameter("date");
        String attendance = request.getParameter("attendance");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        // Database credentials
        String jdbcURL = "jdbc:mysql://localhost:3306/yourDatabase";
        String dbUser = "root";
        String dbPassword = "yourPassword";
        
        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
            
            // Query to insert attendance record
            String query = "INSERT INTO attendance (student_id, course, date, attendance_status) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, studentId);
            stmt.setString(2, course);
            stmt.setString(3, date);
            stmt.setString(4, attendance);
            stmt.executeUpdate();
            
            // Confirmation message
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h3>Attendance for Student ID " + studentId + " in " + course + " has been recorded.</h3>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database error.");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
