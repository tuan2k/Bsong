package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.GlobalConstant;
import daos.UserDAO;
import models.User;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getById(id);
		String url = "";
	 	StringBuilder sbd = new StringBuilder();
		if (user != null) {
			sbd = new StringBuilder();
			request.setAttribute("user", user);
	 		//url = sbd.append(request.getContextPath()).append("/admin/user/edit").toString();
	 		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/edit.jsp");
	 		rd.forward(request, response);
	 		return;
		}else {
			url = sbd.append(request.getContextPath()).append("/admin/user/index.jsp").toString();
		 	response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("uid"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		UserDAO userDAO = new UserDAO();
		User user  = new User(id, username, password, fullname);
		boolean check;
		int countRecordInserted = 0;
	 	String url = "";
	 	StringBuilder sbd = new StringBuilder();
		check = userDAO.Validate(user);
		if (check == true) {
			countRecordInserted = userDAO.add(user);
		}
	 	if(countRecordInserted > 0) {
	 		// success
	 		sbd = new StringBuilder();
	 		url = sbd.append(request.getContextPath()).append("/admin/user/index?msg=").append(GlobalConstant.SUCCESS).toString();
	 		response.sendRedirect(url);
	 		return;
	 	} 
	 	// fail
 		url = sbd.append(request.getContextPath()).append("/admin/user/index?msg=").append(GlobalConstant.ERROR).toString();
	 	response.sendRedirect(url);
	}
	
}