package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.ContactDAO;
import models.Contact;
import utils.DefineUtil;

public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexContactController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContactDAO contactDAO = new ContactDAO();
		int numContact = contactDAO.numberOfContact();
		int numPage = (int)Math.ceil(numContact*1.0/DefineUtil.NUMBER_PER_PAGE) ;
		boolean check = true;
		int currentPage = 1;
		try {
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
		}catch(NumberFormatException e) {
			check = false;
			response.sendRedirect(request.getContextPath()+"/views/error_page.jsp");
			e.printStackTrace();
		}
		if (currentPage > numPage|| currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage-1)*DefineUtil.NUMBER_PER_PAGE;
		
		List<Contact> contacts = contactDAO.getSongPagination(offset);
		
		request.setAttribute("NumPage",numPage);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("contacts", contacts);
		if (check == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/contact/index.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
