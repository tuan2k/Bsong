<%@page import="models.Song"%>
<%@page import="daos.SongDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <div class="article">
      <%
      	
      	if (request.getAttribute("listSong")!= null){
      		ArrayList<Song> list = (ArrayList<Song>)request.getAttribute("listSong");
      	if (list != null){
      		if (list.size() > 0){
      			for (Song s: list){
      	    		String Urlslug = request.getContextPath()+"/detail/" + StringUtil.makeSlug(s.getName())+"-"+s.getId()+".html" ;
      %>	
      <h2><a href="<%= Urlslug%>" title="Đổi thay"><%=s.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=s.getDate_create() %>. Lượt xem:<%=s.getCounter() %> <a href="#" class="com"><span>1</span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/resources/public/images/doi-thay.jpg" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%= s.getPreview() %></p>
        <p class="spec"><a href="<%=Urlslug %>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
      <%
      			}
      		}
      	}
      	
      %>
      
    </div>
    <%
		int NumPage = (Integer) request.getAttribute("NumPage");
		int currentPage = (Integer) request.getAttribute("currentPage");
		if (list.size() > 0 && list != null) {
	%>
    <p class="pages">
    <%
    for (int i=1;i<=NumPage;i++){
    	if (i == currentPage){
    		%>
    		<span><%=i %></span>
    		<% 
    	}else{
    		%>
    		<!-- search -->
    		<%
    			if (request.getAttribute("search") != null){
    				String search = request.getParameter("search") ;
      	    		String Urlslug = request.getContextPath()+"/home/" + "search"+ "-" + search +"-"+i+".html" ;
    		%>
    		<a href="<%=Urlslug%>"><%=i %></a>
    		<%}else{ %>
    		<a href="<%=request.getContextPath()%>/home?page=<%=i%>"><%=i %></a>
    		<% } %>
    		
    		<!-- end search -->
    		<% 
    	} }
    %>
    <% } } %>
    </p>
  	</div>
  <div class="sidebar">
  	<%@ include file="/templates/public/inc/leftbar.jsp" %>  
  </div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>
