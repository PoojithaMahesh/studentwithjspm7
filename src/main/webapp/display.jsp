<%@page import="studentwithjspm77.dto.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%List<Student> students=(List)request.getAttribute("students"); %>
<table border="2px">
<tr>
<th>ID</th>
<th>Name</th>
<th>Address</th>
<th>Phone</th>
<th>Email</th>
<th>Password</th>
<th>Fees</th>
<th>Delete</th>
<th>Update</th>
</tr>
<%for(Student student:students){ %>
<tr>
<td><%=student.getId() %></td>
<td><%=student.getName() %></td>
<td><%=student.getAddress() %></td>
<td><%=student.getPhone() %></td>
<td><%=student.getEmail() %></td>
<td><%=student.getPassword() %></td>
<td><%=student.getFees() %></td>
<td><a href="delete?id=<%=student.getId() %>">Delete</a></td>
<td><a href="update?id=<%=student.getId() %>">Update</a></td>
</tr>
<%} %>


</table>
</body>
</html>