package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReimbursementSessionServlet
 */
public class ReimbursementSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		if(session != null) {
			//pw.write("{");
			pw.write("{\"viewList\": "+session.getAttribute("viewList")+"}");
			//pw.write(",");
			//pw.write("{\"allReimbursements\": "+session.getAttribute("allReimbursements")+"}");
			//pw.write("}");
			
		}else {
			pw.write("{\"viewList\": null}");
			//pw.write("{\"allReimbursements\": null}");
			
		}
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
