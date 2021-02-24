<%@page import="models.Category"%>
<%@page import="java.util.List"%>
<%@page import="daos.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Thêm bài hát</h2>
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
                                <form role="form" method="post" id="form" action="<%=request.getContextPath()%>/admin/song/add" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="name">Tên bài hát</label>
                                        <input type="text" id="name" value="" name="name" class="form-control" required/>
                                        <label for="name">Review</label>
                                        <input type="text" id="review" value="" name="review" class="form-control" required/>
                                        <label for="name">Chi tiết</label>
                                        <textarea id="detail" name="detail" class="form-control" required></textarea>
                                        <label for="name">Upload ảnh</label>
                                        <input type="file" id="image" value="" name="image" class="form-control" required/>
                                        <label for="name">Tiêu đề</label>
                                        <select name="catid">
                                        <%
                                        	CategoryDAO categoryDAO = new CategoryDAO();
                                        	List<Category> listcat = categoryDAO.getCategories();
                                        	for (Category c :listcat){
                                        %>
                                        <option value="<%=c.getId()%>"><%=c.getName() %></option>
                                        <% } %>
                                        </select>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
                                </form>
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