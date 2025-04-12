<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>
</head>
<body>
    <h2>Student Portal</h2>
    <form action="SaveAttendanceServlet" method="post">
        Student ID: <input type="text" name="studentId" required><br><br>
        Course: <input type="text" name="course" required><br><br>
        Date: <input type="date" name="date" required><br><br>
        Attendance: 
        <select name="attendance">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
