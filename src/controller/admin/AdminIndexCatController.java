package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.CategoryDAO;
import models.Category;
import utils.DefineUtil;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		
		int numCat = categoryDAO.numberOfCat();
		int numPage = (int)Math.ceil(numCat*1.0/DefineUtil.NUMBER_PER_PAGE) ;
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
		
		List<Category> caterogies = categoryDAO.getCatPagination(offset);
		request.setAttribute("NumPage",numPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("catlist", caterogies);
		if (check == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/cat/index.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
