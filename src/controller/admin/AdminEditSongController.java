package controller.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import constants.GlobalConstant;
import daos.SongDAO;
import models.Song;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		SongDAO songDAO = new SongDAO();
		Song song = songDAO.getSongById(id);
		String url = "";
	 	StringBuilder sbd = new StringBuilder();
		if (song != null) {
			sbd = new StringBuilder();
			request.setAttribute("song", song);
	 		//url = sbd.append(request.getContextPath()).append("/admin/cat/edit").toString();
	 		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/edit.jsp");
	 		rd.forward(request, response);
	 		return;
		}else {
			url = sbd.append(request.getContextPath()).append("/admin/song/index.jsp").toString();
		 	response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("sid"));
		String name = request.getParameter("name");
		String preview = request.getParameter("review");
		String detail = request.getParameter("detail");
		int catid = Integer.parseInt(request.getParameter("catid"));
		Part filePart = request.getPart("upimage");
		String fileName = filePart.getSubmittedFileName();
		if (!"".equals(fileName)) {
			String rootPath = request.getServletContext().getRealPath("");
			String dirUploadPath = rootPath + "files";
			File createDir = new File(dirUploadPath);
			if (!createDir.exists()) {
				createDir.mkdir();
			}
			// use String buider append to add string avoid sql 
			StringBuffer sb = new StringBuffer();
			String filePath =  sb.append(dirUploadPath).append(File.separator).append(fileName).toString();
			filePart.write(filePath);   // truyền vào đường dẫn upload file
		}
		SongDAO songDAO = new SongDAO();
		Song song = new Song(id,name, preview, detail, fileName, catid);
		boolean validate;
		int countRecordInserted=0;
		validate = songDAO.getSongNameByName(song.getName());
	 	if (validate == true) {
	 		countRecordInserted = songDAO.update(song);
	 	}
	 	String url = "";
	 	StringBuilder sbd = new StringBuilder();
	 	if(countRecordInserted > 0) {
	 		// success
	 		sbd = new StringBuilder();
	 		url = sbd.append(request.getContextPath()).append("/admin/song/index?msg=").append(GlobalConstant.SUCCESS).toString();
	 		response.sendRedirect(url);
	 		return;
	 	} 
	 	// fail
 		url = sbd.append(request.getContextPath()).append("/admin/song/index?msg=").append(GlobalConstant.ERROR).toString();
	 	response.sendRedirect(url);
	}
	
}