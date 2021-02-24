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

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PublicCatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// try catch ne
		int cid = Integer.parseInt(request.getParameter("cid"));
		SongDAO songDAO = new SongDAO();
		ArrayList<Song> lists = songDAO.getAll();
		ArrayList<Song> listscat =  new ArrayList<Song>();
		for (Song s:lists) {
		if (s.getCat_id() == cid) {
			listscat.add(s);
			}
		}
		int numSong = listscat.size();
		int numPage = (int)Math.ceil(numSong*1.0/DefineUtil.NUMBER_PER_PAGE) ;
		int currentPage = 1;
		try {
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		if (currentPage > numPage|| currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage-1)*DefineUtil.NUMBER_PER_PAGE;
		List<Song> songs = songDAO.getSongPaginationByCatID(cid,offset);
		request.setAttribute("NumPage",numPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listSong", songs);
		RequestDispatcher rd = request.getRequestDispatcher("views/public/cat.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
