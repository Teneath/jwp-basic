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
	        
	       	  <c:if test="${not empty errorMessage}">
            		<div class="alert alert-danger" role="alert">${errorMessage}</div>
              </c:if>		
              <c:if test="${CannotUpdate}">
            		<div class="alert alert-danger" role="alert">다른 사람의 게시글은 수정하실 수 없습니다.</div>
              </c:if>
              <c:if test="${CannotDelete}">
            		<div class="alert alert-danger" role="alert">다른 사람의 게시글은 삭제하실 수 없습니다.</div>
              </c:if>		
	        
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
	                            <a href="/users/profile?userId=${question.writer}" class="article-author-name">${question.writer}</a>
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
	                  	  <div id="countAnswer">
	                      	<p class="qna-comment-count">
	                      		<strong>${question.countOfComment}</strong>개의 의견
	                      	</p>
	                      </div>
	                      <div class="qna-comment-slipp-articles">
	
								<c:forEach items="${answers}" var="each">
		                          <article class="article">
		                              <div class="article-header">
		                                  <div class="article-header-thumb">
		                                      <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
		                                  </div>
		                                  <div class="article-header-text">
		                                      ${each.writer}
		                                      <div class="article-header-time">
		                                      	<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${each.createdDate}"/>
		                                      </div>
		                                  </div>
		                              </div>
		                              <div class="article-doc comment-doc">
		                              	<p>${each.contents}</p>
		                              </div>
		                              <div class="article-util">
		                                  <ul class="article-util-list">
		                                      <li>
		                                          <a class="link-modify-article" href="/qna/updateFormAnswer?answerId=${each.answerId}">수정</a>
		                                      </li>
		                                      <li>
		                                          <form class="form-delete" method="POST" action="/api/qna/deleteAnswer">
		                                              <input type="hidden" name="answerId" value="${each.answerId}"/>
		                                              <input type="hidden" name="questionId" value="${each.questionId}"/>
		                                              <button type="submit" class="link-delete-article">삭제</button>
		                                          </form>
		                                      </li>
		                                  </ul>
		                              </div>
		                          </article>
		                        </c:forEach>
		                        <div class="answerWrite">
		                        	<form name="answer" method="POST">
		                        		<input type="hidden" name="questionId" value="${question.questionId}"/>
		                        		<div class="form-group col-lg-4" style="padding-top:10px;">
		                        			<c:choose>
			                        			<c:when test="${empty userId}">
				                        			<input class="form-control" name="writer" id="writer" 
				                        				   placeholder="작성자"/>
				                        		</c:when>
				                        		<c:otherwise>
				                        			<input class="form-control" name="writer" id="writer" 
				                        				   disabled placeholder="작성자" value="${userId}"/>
				                        		</c:otherwise>
			                        		</c:choose>
		                        		</div>
		                        		<div class="form-group col-lg-12">
		                        			<textarea name="contents" id="contents" class="form-control" placeholder=""></textarea>
		                        		</div>
		                        		<input class="btn btn-sucess pull-right" type="submit" value="답변하기" />
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
	
	<script type="text/template" id="answerTemplate">
		<article class="article">
		<div class="article-header">
			<div class="article-header-thumb">
				<img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
			</div>
			<div class="article-header-text">
				{0}
				<div class="article-header-time">{1}</div>
			</div>
		</div>
		<div class="article-doc comment-doc">
			{2}
		</div>
		<div class="article-util">
		<ul class="article-util-list">
			<li>
				 <a class="link-modify-article" href="/qna/updateFormAnswer?answerId={3}">수정</a>
			</li>
			<li>
				<form class="form-delete" method="POST" action="/api/qna/deleteAnswer">
					<input type="hidden" name="answerId" value="{4}" />
					<input type="hidden" name="questionId" value="{5}" />
					<button type="submit" class="link-delete-article">삭제</button>
				</form>
			</li>
		</ul>
		</div>
	</article>
	</script>
	
	<script type="text/template" id="countTemplate">
		<p class="qna-comment-count"><strong>{0}</strong>개의 의견</p>
	</script>
	
	<%@ include file="/include/footer.jspf" %>

	</body>
</html>
