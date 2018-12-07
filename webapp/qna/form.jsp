<!DOCTYPE html>
<html lang="kr">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" pageEncoding = "UTF-8"%>
<head>
	<%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>
<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/qna/create">
              <div class="form-group">
                  <label for="writer">writer</label>
                  <input class="form-control" id="writer" name="writer" placeholder="writer"/>
              </div>
              <div class="form-group">
                  <label for="title"> title </label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="title"/>
              </div>
              <div class="form-group">
                  <label for="contents">contents</label>
                  <textarea name="contents" id="contents" rows="5" class="form-control"></textarea>
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right"> enregister </button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

	<%@ include file="/include/footer.jspf" %>
	</body>
</html>