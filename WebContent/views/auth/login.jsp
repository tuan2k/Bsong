<%@page import="constants.GlobalConstant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>Login</h2>
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
                            	<% if (request.getParameter("msg")!= null){
                            	String msg = request.getParameter("msg");
                            	if (GlobalConstant.ERROR.equals(msg)){
                            		%>
                            		<h4 class="alert alert-success" role="alert">ERROR</h4>
                            		<%
                            	}
                            }
                            %>
                                <form role="form" method="post" id="form" action="<%=request.getContextPath()%>/auth/login">
                                    <div class="form-group">
                                         <label for="name">Tên đăng nhập</label>
                                         <input type="text" id="name" value="" name="username" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                         <label for="name">Password</label>
                                         <input type="password" id="name" value="" name="password" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Login</button>
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