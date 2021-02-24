package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.ContactDAO;
import models.Contact;

public class AdminEditContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContactDAO contactDAO = new ContactDAO();
		Contact contact = contactDAO.getById(id);
		String url = "";
	 	StringBuilder sbd = new StringBuilder();
		if (contact != null) {
			sbd = new StringBuilder();
			request.setAttribute("contact", contact);
	 		//url = sbd.append(request.getContextPath()).append("/admin/cat/edit").toString();
	 		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/edit.jsp");
	 		rd.forward(request, response);
	 		return;
		}else {
			url = sbd.append(request.getContextPath()).append("/admin/contact/index.jsp").toString();
		 	response.sendRedirect(url);
		}
 		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		ContactDAO contactDAO = new ContactDAO();
		Contact contact = new Contact(id, name, email, website, message);
		int countRecordInserted = contactDAO.update(contact);
	 	String url = "";
	 	StringBuilder sbd = new StringBuilder();
	 	if(countRecordInserted > 0) {
	 		// success
	 		sbd = new StringBuilder();
	 		url = sbd.append(request.getContextPath()).append("/admin/contact/index?msg=").append(GlobalConstant.SUCCESS).toString();
	 		response.sendRedirect(url);
	 		return;
	 	} 
	 	// fail
 		url = sbd.append(request.getContextPath()).append("/admin/contact/index?msg=").append(GlobalConstant.ERROR).toString();
	 	response.sendRedirect(url);
	}
	
}