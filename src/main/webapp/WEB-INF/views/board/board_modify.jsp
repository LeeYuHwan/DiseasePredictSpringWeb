<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../includes/header.jsp" %>
	
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Read</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>      
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Read Page</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form role="form" id="passwd_check" action="board_passwd_check" method="post">
						<input type='hidden' name="${_csrf.parameterName}" value="${_csrf.token}" />
						<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
						<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
						<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
						<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
						<div class="form-group">
							<label>번호</label>
							<input class="form-control" name='bno' value='<c:out value="${board.bno }"/>' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" name='title' value='<c:out value="${board.title }"/>' >
						</div>
						
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="4" name='content' ><c:out value="${board.content }"/></textarea>
						</div>
						
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" name='writer' value='<c:out value="${board.writer }"/>' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>작성일</label>
							<input class="form-control" name='regDate' value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly">
						</div>
						
						<div class="form-group">
							<label>수정일</label>
							<input class="form-control" name='updateDate'value='<fmt:formatDate pattern="yyyy/MM/dd" value="${board.updateDate }"/>' readonly="readonly">
						</div>
						<div class="form-group">
							<label>비밀번호</label>		
								<input type="password" id="password" name="passwd" maxlength="12">								
								<input type="hidden" class="work" name='work' value='' readonly="readonly">
    						</div>
					</form>
						
						
						<!--<sec:authentication property="principal" var="pinfo" />
						<sec:authorize access="isAuthenticated()">
							<c:if test="${pinfo.username eq board.writer}">					
								<button type="submit" data-oper='modify' class="btn btn-info updateBtn">수정완료</button>
								<button type="submit" data-oper='remove' class="btn btn-info delBtn">삭제</button>							
							</c:if>						
						</sec:authorize>-->
						
						<button type="submit" data-oper='list' class="btn btn-info">목록</button>
						<button type="submit" data-oper='modify' class="btn btn-info updateBtn">수정완료</button>
						<button type="submit" data-oper='remove' class="btn btn-info delBtn">삭제</button>
					
					
				</div>
				<!--  end panel-body -->
			</div>
			<!--  end panel-body -->
		</div>
		<!-- end panel -->
	</div>
	<!-- /.row -->
	
	<script type="text/javascript">
		$(document).ready(function() {
			var formObj = $("form");
			$('button').on("click", function(e) {
				e.preventDefault();
				var operation = $(this).data("oper");
				console.log(operation);
				if(operation === 'remove') {
					console.log("remove");
					document.querySelector(".work").value="delete";
					document.querySelector("#passwd_check").submit();
				} else if (operation === 'list') {
					// self.location = "/board/list";
					// return;
					formObj.attr("action", "board_list").attr("method", "get");
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag = $("input[name='amount']").clone();
					var keywordTag = $("input[name='keyword']").clone();
					var typeTag = $("input[name='type']").clone();
					formObj.empty();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(keywordTag);
					formObj.append(typeTag);
				}
				formObj.submit();
			});
		});	
		
		let updateBtn = document.querySelector(".btn.btn-info.updateBtn");
		updateBtn.addEventListener("click", (evt) => {
			document.querySelector(".work").value="update";
			document.querySelector("#passwd_check").submit();	
		});
	</script>
	
<%@include file="../includes/footer.jsp" %>