package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ERSDaoImpl;
import com.revature.pojos.ReimbursementInfo;

/**
 * Servlet implementation class PersonalEmployeeServlet
 */
public class PersonalEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the session linktoempr name, and then use the value there to find the list containing the reimbursement info, and print
		//them out in object mapper
		HttpSession session = request.getSession();
		ERSDaoImpl edi = new ERSDaoImpl();
		String id = (String) session.getAttribute("linktoempr");
		int ids = Integer.parseInt(id);
		List<ReimbursementInfo> rilist = edi.viewAllRR(ids);
		ObjectMapper om = new ObjectMapper();
		String rilistString = om.writeValueAsString(rilist);
		rilistString = "{\"rilist\":"+rilistString+"}";
		PrintWriter pw = response.getWriter();
		pw.write(rilistString);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
