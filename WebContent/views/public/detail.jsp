<%@page import="java.util.ArrayList"%>
<%@page import="models.Song"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="daos.SongDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <%
      Song song = (Song) request.getAttribute("song");
      ArrayList<Song> listall = (ArrayList<Song>) request.getAttribute("songlist");
      CategoryDAO c1 = new CategoryDAO();
      if (song != null){
    	  int cid = song.getId();
      %>
      <h1><%=c1.getCatnameByid(cid) %></h1>
      <h2><%= song.getName()%></h2>
      <div class="clr"></div>
      <p>Ngày đăng: <%=song.getDate_create() %>. Lượt xem: <%= song.getCounter() %></p>
      <div class="vnecontent">
          <%= song.getDetail() %>
      </div>
    </div>
    <div class="article">
      <h2>Bài viết liên quan</h2>
      <div class="clr"></div>
      <%
      	    for (Song ss: listall){
      	    	if (ss.getCat_id() == song.getCat_id() && ss.getId() != song.getId()){
      	    		String Urlslug = request.getContextPath()+"/detail/" + StringUtil.makeSlug(ss.getName())+"-"+ss.getId()+".html" ;
      %>
      <div class="comment"> <a href=""><img src="<%=request.getContextPath() %>/resources/public/images/only-love.jpg" width="40" height="40" alt="" class="userpic" /></a>
        <h2><a href="<%= Urlslug%>"><%= ss.getName() %></a></h2>
        <p><%=ss.getPreview() %></p>
      </div>
      <% } } } %>
    </div>
    
  </div>
  <div class="sidebar">
      <%@ include file="/templates/public/inc/leftbar.jsp" %>
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
