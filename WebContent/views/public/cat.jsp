<%@page import="models.Song"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="daos.SongDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/public/inc/header.jsp" %>
<div class="content_resize">
  <div class="mainbar">
    <%
        //ArrayList<Song> lists = (ArrayList<Song>)request.getAttribute("listsongcat");
    	ArrayList<Song> lists = (ArrayList<Song>) request.getAttribute("listSong");
    	CategoryDAO c2 = new CategoryDAO();
    	int heo =0;
        if (lists != null){
        	if (lists.size() > 0){
        		for (Song s: lists){
      	    		String Urlslug = request.getContextPath()+"/detail/" + StringUtil.makeSlug(s.getName())+"-"+s.getId()+".html" ;
     %>
     <% if (heo == 0) {%>
     	<h1><%= c2.getCatnameByid(s.getCat_id()) %></h1>
     <% heo++ ;} else heo++; %>
    <div class="article">
      <h2><a href="<%=Urlslug %>" title="Đổi thay"><%=s.getName() %></a></h2>
      <p class="infopost">Ngày đăng: <%=s.getDate_create() %>. Lượt xem: <%=s.getCounter() %> <a href="#" class="com"><span>1</span></a></p>
      <div class="clr"></div>
      <div class="img"><img src="<%=request.getContextPath() %>/resources/public/images/doi-thay.jpg" width="177" height="213" alt="Đổi thay" class="fl" /></div>
      <div class="post_content">
        <p><%=s.getPreview() %></p>
        <p class="spec"><a href="<%=Urlslug%>" class="rm">Chi tiết &raquo;</a></p>
      </div>
      <div class="clr"></div>
    </div>
     <%
        		}
        	}
      %>
    <%
		int NumPage = (Integer) request.getAttribute("NumPage");
		int currentPage = (Integer) request.getAttribute("currentPage");
		CategoryDAO catdao = new CategoryDAO();
		if (lists.size() > 0 && lists != null) {
			Song s = lists.get(0);
			String catname = catdao.getCatnameByid(s.getCat_id());
	%>
    <p class="pages">
    <%
    for (int i=1;i<=NumPage;i++){
    	String Urlslugc = request.getContextPath()+"/category/" + StringUtil.makeSlug(catname)+"-"+s.getCat_id()+"-"+i+".html" ;
    	if (i== currentPage){
    		%>
    		<span><%=i %></span>
    		<% 
    	}else{
    		%>
    		<a href="<%=Urlslugc %>"><%=i %></a>
    		<% 
    	}
    %>
    <% } } }%>
    </p>
    
    
    </p>
  </div>
  <div class="sidebar">
  		<%@ include file="/templates/public/inc/leftbar.jsp" %>
  	</div>
  <div class="clr"></div>
</div>
<%@ include file="/templates/public/inc/footer.jsp" %>