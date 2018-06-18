package com.revature.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.dao.ERSDaoImpl;
import com.revature.pojo.Employee;
import com.revature.pojo.Reimbursement;
import com.revature.servlet.SessionServlet.Info;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = -7797812828514308149L;
	ERSDaoImpl dao = new ERSDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("id") != null)
			req.getRequestDispatcher("profile.html").forward(req, res);
		else
			res.sendRedirect("login");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		SessionServlet.clearMessagesAndErrors();
		
		if(req.getParameter("amount") != null) {
			try{
				System.out.println(req.getParameter("amount"));
				double amount = Double.parseDouble(req.getParameter("amount"));
				String description = req.getParameter("description");
				String picURL = req.getParameter("picURL");
				
				Random r = new Random();
				int reimb_id = r.nextInt(90000000) + 10000000;
				while(dao.getReimbursementByID(reimb_id) != null)
					reimb_id = r.nextInt(90000000) + 10000000;
				int request_id = SessionServlet.empl.getID();
				Date requestDate = Date.valueOf(LocalDate.now());
				String status = "Pending";
				
				dao.createReimbursement(new Reimbursement(reimb_id, request_id,
						picURL, amount, description, requestDate, status, 0, null));
			} catch(NumberFormatException e) {
				e.printStackTrace();
				SessionServlet.errors.add(new Info("Invalid amount provided", true));
			}
			res.sendRedirect("profile");
		}
		else if(req.getParameter("emplBday") != null) {
			String first = req.getParameter("emplFirst");
			String email = req.getParameter("emplEmail");
			String last = req.getParameter("emplLast");
			Date bday = null;
			if(!req.getParameter("emplBday").equals(""))
				bday = Date.valueOf(req.getParameter("emplBday"));
			
			if(first.equals(""))
				first = SessionServlet.empl.getFirst();
			if(last.equals(""))
				last = SessionServlet.empl.getLast();
			if(email.equals(""))
				email = SessionServlet.empl.getEmail();
			
			SessionServlet.empl.setFirst(first);
			SessionServlet.empl.setLast(last);
			SessionServlet.empl.setEmail(email);
			SessionServlet.empl.setBday(bday);
			dao.updateEmployee(SessionServlet.empl);
			
			res.sendRedirect("profile");
		}
		else if(req.getParameter("removeReimb") != null) {
			dao.deleteReimbByID(Integer.parseInt(req.getParameter("removeReimb")));
			res.sendRedirect("profile");
		}
		else if(req.getParameter("logout") != null) {
			SessionServlet.empl = new Employee();
			req.getSession(false).removeAttribute("id");
			res.sendRedirect("login");
		}
//			BufferedReader br = req.getReader();
//			String post = br.readLine();
//			if(post.matches("^RemoveReimb [0-9]{8}$")) {
//				int reimb_id = Integer.parseInt(post.substring(12));
//				dao.deleteReimbByID(reimb_id);
//				res.sendRedirect("profile");
//			}
//			else if(post.equals("Logout")) {
//				SessionServlet.empl = new Employee();
//				req.getSession(false).removeAttribute("id");
//				res.sendRedirect("login");
//			}
			// more possible custom post requests
	}
}