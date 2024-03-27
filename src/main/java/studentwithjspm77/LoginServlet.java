package studentwithjspm77;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import studentwithjspm77.dao.StudentDao;
import studentwithjspm77.dto.Student;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	StudentDao dao=new StudentDao();
	List<Student> list=dao.getAllStudents();
	boolean value=false;
	String dbPassword=null;
	
	for(Student student:list) {
		if(email.equals(student.getEmail())) {
			value=true;
			dbPassword=student.getPassword();
			break;
		}
	}
	
	if(value) {
//		email is present on the database
		if(password.equals(dbPassword)) {
//			login success
			RequestDispatcher dispatcher=req.getRequestDispatcher("display.jsp");
			dispatcher.forward(req, resp);
		}else {
//			invalid Password
			req.setAttribute("message", "Sorry invalid password");
			RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
			dispatcher.include(req, resp);
		}
	}else {
		req.setAttribute("message", "Sorry invalid email");
		RequestDispatcher dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);
	}
			
}
}
