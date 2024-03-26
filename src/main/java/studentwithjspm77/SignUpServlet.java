package studentwithjspm77;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspm77.dao.StudentDao;
import studentwithjspm77.dto.Student;

public class SignUpServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String name=req.getParameter("name");
	String address=req.getParameter("address");
	long phone=Long.parseLong(req.getParameter("phone"));
	double fees=Double.parseDouble(getServletContext().getInitParameter("fees"));
	Student student=new Student();
	student.setAddress(address);
	student.setEmail(email);
	student.setFees(fees);
	student.setName(name);
	student.setPassword(password);
	student.setPhone(phone);
	
	StudentDao dao=new StudentDao();
	List<Student> students=dao.getAllStudents();
	boolean value=true;
	
	for(Student st:students) {
		if(email.equals(student.getEmail())) {
			value=false;
			break;
		}
	}
	if(value) {
//		this student email is not present in the database
		dao.saveStudent(student);
		PrintWriter printWriter=resp.getWriter();
		printWriter.print("Signed up successfully");
		
	}else {
//		email is present in the database
		RequestDispatcher dispatcher=req.getRequestDispatcher("signup.jsp");
		dispatcher.include(req, resp);
	}
	
	
}
}
