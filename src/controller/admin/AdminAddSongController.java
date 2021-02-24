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
public class AdminAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSongController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/song/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SongDAO songDAO = new SongDAO();
		String name = request.getParameter("name");
		String preview = request.getParameter("review");
		String detail = request.getParameter("detail");
		int catid = Integer.parseInt(request.getParameter("catid"));
		Part filePart = request.getPart("image");
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
			//System.out.println("dirUploadPath: "+dirUploadPath);
			// den trang hinhanh.jsp
			// hien thi ra Fullname, age, image
			// phan biet getcontextpath and file path
			//response.sendRedirect("/View/hinhanh.jsp");
		}
		Song song = new Song(name, preview, detail, fileName, catid);
		boolean validate;
		int countRecordInserted=0;
		validate = songDAO.getSongNameByName(song.getName());
	 	if (validate == true) {
	 		countRecordInserted = songDAO.add(song);
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
