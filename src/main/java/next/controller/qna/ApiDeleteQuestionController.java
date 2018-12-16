package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.CannotDeleteException;
import next.controller.UserSessionUtils;
import next.model.Result;
import next.service.QnaService;

public class ApiDeleteQuestionController extends AbstractController {
	private QnaService qnaService = QnaService.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			return jsonView().addObject("result", Result.fail("로그인이 필요합니다."));
		}
		
		long questionId = Long.parseLong(req.getParameter("questionId"));
		try {
			qnaService.deleteQuestion(questionId, UserSessionUtils.getUserFromSession(req.getSession()));
			return jsonView().addObject("result", Result.ok());
		}catch(CannotDeleteException e) {
			return jsonView().addObject("result", Result.fail(e.getMessage()));
		}
	}

}
