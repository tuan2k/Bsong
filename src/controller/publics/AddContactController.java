package controller.publics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.ContactDAO;
import models.Contact;

public class AddContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddContactController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		Contact ct = new Contact(name, email, website,message);
		ContactDAO contactDAO = new ContactDAO();
		String url = "";
	 	StringBuilder sbd = new StringBuilder();
		int countRecordInserted = contactDAO.add(ct);
		if(countRecordInserted > 0) {
	 		// success
	 		sbd = new StringBuilder();
	 		url = sbd.append(request.getContextPath()).append("/views/public/contact.jsp?msg=").append(GlobalConstant.SUCCESS).toString();
	 		response.sendRedirect(url);
	 		return;
	 	} 
	 	// fail
 		url = sbd.append(request.getContextPath()).append("/views/public/contact.jsp?msg=").append(GlobalConstant.ERROR).toString();
	 	response.sendRedirect(url);
		
	}

}
