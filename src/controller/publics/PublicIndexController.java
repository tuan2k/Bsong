package controller.publics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;
import utils.DefineUtil;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicIndexController() {
		super();	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SongDAO songDAO = new SongDAO();
		// search 
		boolean check = true;
		if (!"".equals(request.getParameter("search")) && request.getParameter("search")!=null ) {
			//search
			String nsearch = request.getParameter("search");
			//String page = request.getParameter("page");
			ArrayList<Song> listseach = songDAO.getSongSearch(nsearch);
			int numSong  = listseach.size();
			int numPage = (int)Math.ceil(numSong*1.0/DefineUtil.NUMBER_PER_PAGE) ;
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
			List<Song> songs = songDAO.getSongPaginationBySearch(nsearch, offset);
			request.setAttribute("search", nsearch);
			request.setAttribute("NumPage",numPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listSong",songs);
			if (check == true) {
				RequestDispatcher rd = request.getRequestDispatcher("views/public/index.jsp");
				rd.forward(request, response);
			}
			
		}else {
			int numSong = songDAO.numberOfSong();
			int numPage = (int)Math.ceil(numSong*1.0/DefineUtil.NUMBER_PER_PAGE) ;
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
			List<Song> songs = songDAO.getSongPagination(offset);
			request.setAttribute("NumPage",numPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("listSong",songs);
			if (check == true) {
				RequestDispatcher rd = request.getRequestDispatcher("views/public/index.jsp");
				rd.forward(request, response);
			}
			
		}
			
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
