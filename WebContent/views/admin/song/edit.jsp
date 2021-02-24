<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@page import="models.Song"%>
<%@page import="models.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Sửa bài hát</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                            	<% 
                            	if (request.getAttribute("song") != null){
                            		Song song = (Song)request.getAttribute("song"); 
                                	int id = song.getId();
                                	String name = song.getName();
                            	%>
                                <form role="form" method="post" id="form" action="<%=request.getContextPath()%>/admin/song/edit" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="name">Tên Bài hát</label>
                                        <input type="hidden" id="id" value="<%=id %>" name="sid" class="form-control" />
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" required/>
                                        <label for="name">Review</label>
                                        <input type="text" id="review" value="<%=song.getPreview() %>" name="review" class="form-control" required/>
                                        <label for="name">Chi tiết</label>
                                        <input type="text" id="detail" value="<%=song.getDetail() %>" name="detail" class="form-control" required />
                                        <label for="name">Upload ảnh</label>
                                        <input type="file" id="image" value="<%=song.getPic() %>" name="upimage" class="form-control" required/>
                                        <label for="name">Tiêu đề</label>
                                        <select name="catid">
                                        <%
                                        	CategoryDAO categoryDAO = new CategoryDAO();
                                        	List<Category> listcat = categoryDAO.getCategories();
                                        	for (Category c :listcat){
                                        %>
                                        
                                        <% if (c.getId() == song.getCat_id()) {%>
                                        <option value="<%=c.getId()%>" selected="selected"><%=c.getName() %></option>
                                        <% }else{ %>
                                        <option value="<%=c.getId()%>"><%=c.getName() %></option>
                                        <% }} %>
                                        </select>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("song").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>