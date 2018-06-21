package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDAOImpl;
import dao.RequestDAOImpl;
import model.Employee;


public class EmployeeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmployeeDAOImpl emp = new EmployeeDAOImpl();
		
		HttpSession session = request.getSession();
		String empemail = session.getAttribute("email").toString();
		Employee employee = emp.getEmployeeByEmail(empemail);
		
		System.out.println(employee);
		
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		String employeeString = om.writeValueAsString(employee);
		PrintWriter pw = response.getWriter();
		pw.print(employeeString);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
