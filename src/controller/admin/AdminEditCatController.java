package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.CategoryDAO;
import models.Category;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		CategoryDAO categoryDAO = new CategoryDAO();
		Category cat = categoryDAO.getById(id);
		String url = "";
	 	StringBuilder sbd = new StringBuilder();
		if (cat != null) {
			sbd = new StringBuilder();
			request.setAttribute("cat", cat);
	 		//url = sbd.append(request.getContextPath()).append("/admin/cat/edit").toString();
	 		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/edit.jsp");
	 		rd.forward(request, response);
	 		return;
		}else {
			url = sbd.append(request.getContextPath()).append("/admin/cat/index.jsp").toString();
		 	response.sendRedirect(url);
		}
 		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		CategoryDAO catDAO = new CategoryDAO();
		Category cat = new Category(id, name);
		boolean validate;
		int countRecordInserted=0;
		validate = catDAO.getByName(cat.getName());
		if (validate == true) {
			countRecordInserted = catDAO.update(cat);
		}
	 	String url = "";
	 	StringBuilder sbd = new StringBuilder();
	 	if(countRecordInserted > 0) {
	 		// success
	 		sbd = new StringBuilder();
	 		url = sbd.append(request.getContextPath()).append("/admin/cat/index?msg=").append(GlobalConstant.SUCCESS).toString();
	 		response.sendRedirect(url);
	 		return;
	 	} 
	 	// fail
 		url = sbd.append(request.getContextPath()).append("/admin/cat/index?msg=").append(GlobalConstant.ERROR).toString();
	 	response.sendRedirect(url);
	}
	
}