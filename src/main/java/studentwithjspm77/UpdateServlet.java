package studentwithjspm77;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentwithjspm77.dao.StudentDao;
import studentwithjspm77.dto.Student;
@WebServlet("/update")
public class UpdateServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	StudentDao dao=new StudentDao();
	Student student=dao.findStudentById(id);
	
	HttpSession httpSession=req.getSession();
	String name=(String) httpSession.getAttribute("studentwhologgedIn");
	if(name!=null) {
//		he is coming from the login page
		req.setAttribute("student", student);
		RequestDispatcher  dispatcher=req.getRequestDispatcher("edit.jsp");
		dispatcher.forward(req, resp);	
	}else {
//		he is a scammer
		req.setAttribute("message", "HEY SCAMMER I KNOW YOU ARE COPY PASTING THE URL SO PLEASE LOGIN");
		RequestDispatcher  dispatcher=req.getRequestDispatcher("login.jsp");
		dispatcher.include(req, resp);	
		
	}
	
	
	
	
	
	
}
}
