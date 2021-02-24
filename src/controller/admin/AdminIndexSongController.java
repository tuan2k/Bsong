package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;
import utils.DefineUtil;

public class AdminIndexSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminIndexSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		int numSong = songDAO.numberOfSong();
		int numPage = (int)Math.ceil(numSong*1.0/DefineUtil.NUMBER_PER_PAGE) ;
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
		
		List<Song> songs = songDAO.getSongPagination(offset);
		
		
		request.setAttribute("NumPage",numPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("songs", songs);
		if (check == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/index.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
