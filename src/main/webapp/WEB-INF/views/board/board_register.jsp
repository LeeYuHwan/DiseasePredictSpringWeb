<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
	
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tables</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>      
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Register</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form role="form" action="board_register_add" method="post">
        				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name='title'>
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="4" name='content'></textarea>
						</div>
						
						<div class="form-group">
							<label>작성자</label>
							<c:if test="${sessionScope.name == null }">
			                    <textarea class="form-control" rows="1" name='writer'></textarea>
	                        </c:if>
	                        <c:if test="${sessionScope.name != null }">
			                    <input class="form-control" name='writer' value='<c:out value="${sessionScope.name }"/>' readonly="readonly">
	                        </c:if>					
						</div>
						<div class="form-group">
							<label>비밀번호</label>
							<input type="password" id="password" name="passwd" maxlength="12">          	
						</div>
						<button type="submit" class="btn btn-default">Submit Button</button>
						<button type="reset" class="btn btn-default">Reset Button</button>
					</form>
				</div>
				<!--  end panel-body -->
			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->
<%@include file="../includes/footer.jsp" %>