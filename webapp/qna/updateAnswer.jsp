<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="/include/header.jspf" %>
</head>
<body>
	<%@ include file="/include/navigation.jspf" %>
	
	<div class="container" id="main">
	    <div class="col-md-12 col-sm-12 col-lg-12">
	        <div class="panel panel-default">
	          <header class="qna-header">
	              <h2 class="qna-title">${question.title}</h2>
	          </header>
	          <div class="content-main">
	          
	              <article class="article">
	                  <div class="article-header">
	                      <div class="article-header-thumb">
	                          <img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
	                      </div>
	                      <div class="article-header-text">
	                          <a href="#" class="article-author-name">${question.writer}</a>
	                          <a href="#" class="article-header-time">
	                              <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}"/>
	                              <i class="icon-link"></i>
	                          </a>
	                      </div>
	                  </div>
	                  <div class="article-doc">
	                      ${question.contents}
	                  </div>
	                  <div class="article-util">
	                      <ul class="article-util-list">
	                          <li>
	                              <a class="link-modify-article" 
	                              href="/qna/updateForm?questionId=${question.questionId}">수정</a>
	                          </li>
	                          <li>
	                              <form class="form-delete" action="/qna/delete" method="POST">
	                                  <input type="hidden" name="questionId" value="${question.questionId}">
	                                  <button class="link-delete-article" type="submit">삭제</button>
	                              </form>
	                          </li>
	                          <li>
	                              <a class="link-modify-article" href="/">목록</a>
	                          </li>
	                      </ul>
	                  </div> 
	              </article>
	
	              <div class="qna-comment">
	                  <div class="qna-comment-slipp">
	                      <div class="qna-comment-slipp-articles">
		                     <div class="answerUpdate">
		                        <form name="answer" method="POST" action="/qna/updateAnswer">
		                        	<input type="hidden" name="questionId" value="${question.questionId}"/>
		                        	<input type="hidden" name="answerId" value="${answer.answerId}"/>
		                        		<div class="form-group col-lg-4" style="padding-top:10px;">
		                        			<c:choose>
			                        			<c:when test="${empty userId}">
				                        			<input class="form-control" name="writer" id="writer" 
				                        				   placeholder="작성자"/>
				                        		</c:when>
				                        		<c:otherwise>
				                        			<input class="form-control" name="writer" id="writer" 
				                        				   disabled placeholder="작성자" value="${answer.writer}"/>
				                        		</c:otherwise>
			                        		</c:choose>
		                        		</div>
		                        		<div class="form-group col-lg-12">
		                        			<textarea name="contents" id="contents" class="form-control">${answer.contents}</textarea>
		                        		</div>
		                        		<input class="btn btn-sucess pull-right" type="submit" value="수정하기" />
		                        		<div class="clearfix"></div>
		                        	</form>
		                        </div>
	                      </div>
	                  </div>
	              </div>
	              
	          </div>
	        </div>
	    </div>
	</div>
	
	
	<%@ include file="/include/footer.jspf" %>

	</body>
</html>
