package controller.publics;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.SongDAO;
import models.Song;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public PublicDetailController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("sid"));
		SongDAO songDAO = new SongDAO();
		Song song = new Song();
		song = songDAO.getSongById(id);
		ArrayList<Song> songlist = songDAO.getAll();
		request.setAttribute("song", song);
		request.setAttribute("songlist", songlist);
		RequestDispatcher rd = request.getRequestDispatcher("views/public/detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
