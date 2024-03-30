package studentwithjspm77;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	String studentwhologgedin=null;
	
	for(Student student:list) {
		if(email.equals(student.getEmail())) {
			value=true;
			studentwhologgedin=student.getName();
			dbPassword=student.getPassword();
			break;
		}
	}
	
	if(value) {
//		email is present on the database
		if(password.equals(dbPassword)) {
//			login success
			
//			Create a Cookie
			Cookie cookie=new Cookie("studentnamewhologgein", studentwhologgedin);
			resp.addCookie(cookie);
//			Create a HTTPSESSION
			HttpSession httpSession=req.getSession();
			httpSession.setAttribute("studentwhologgedIn", studentwhologgedin);
			
			
			
			req.setAttribute("students", list);
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
