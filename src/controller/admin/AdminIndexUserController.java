package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDAO;
import models.User;
import utils.DefineUtil;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get user list
		UserDAO userDAO = new UserDAO();
		
		int numUser = userDAO.numberOfUser();
		int numPage = (int)Math.ceil(numUser*1.0/DefineUtil.NUMBER_PER_PAGE) ;
		boolean check = true;
		int currentPage = 1;
		try {
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath()+"/views/error_page.jsp");
			check = false;
			e.printStackTrace();
		}
		if (currentPage > numPage|| currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage-1)*DefineUtil.NUMBER_PER_PAGE;
		
		List<User> users = userDAO.getSongPagination(offset);
		
		request.setAttribute("NumPage",numPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listuser", users);
		if (check == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/user/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
