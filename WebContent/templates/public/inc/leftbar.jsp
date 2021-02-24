<%@page import="models.Song"%>
<%@page import="daos.SongDAO"%>
<%@page import="utils.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="models.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<div class="searchform">
  <form id="formsearch" name="formsearch" method="get" action="<%= request.getContextPath()%>/home">
    <span>
    <input class="editbox_search" id="editbox_search" maxlength="80" value="" type="text" name="search" />
    <input class="editbox_search"  type = "hidden" maxlength="80" value="1" type="text" name="page" />
    </span>
    <button>Seach</button>
  </form>
</div>
<div class="clr"></div>
<div class="gadget">
  <h2 class="star">Danh mục bài hát</h2>
  <div class="clr"></div>
  <ul class="sb_menu">
  
  	<% 
  		CategoryDAO categoryDAO = new CategoryDAO();
  		List<Category> listc = categoryDAO.getCategories();
  		if (listc != null){
  			if (listc.size() > 0){
  				for (Category c : listc){
  					String Urlslug = request.getContextPath()+"/category/" + StringUtil.makeSlug(c.getName())+"-"+c.getId()+"-"+"1"+".html" ;
  	%>
    <li><a href="<%=Urlslug%>"><%=c.getName() %></a></li>
    <%
  				}
  			}
  		}
    %>
    
  </ul>
</div>

<div class="gadget">
  <h2 class="star"><span>Bài hát mới</span></h2>
  <div class="clr"></div>
  <ul class="ex_menu">
    <% 
    	SongDAO dao = new SongDAO();
    	List<Song> songs =  dao.getAll();
    	int heo2 =1;
    	for (Song s: songs){
    		if (heo2 <=3){
    			String Urlslug = request.getContextPath()+"/detail/" + StringUtil.makeSlug(s.getName())+"-"+s.getId()+".html" ;
    %>
    <li><a href="<%= Urlslug%>"><%= s.getName()%></a><br />
      <%=s.getPreview().substring(0, 100	) %></li>
     <% ++heo2; }} %>
  </ul>
</div>