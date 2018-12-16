package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.CannotDeleteException;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.User;
import next.service.QnaService;

public class DeleteQuestionController extends AbstractController {
	private AnswerDao answerDao = AnswerDao.getInstance();
	private QuestionDao questionDao = QuestionDao.getInstance();
	private QnaService qnaService = QnaService.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			return jspView("redirect:/users/loginForm");
		}
		
		User user = null;
		long questionId = Long.parseLong(req.getParameter("questionId"));
		try {
			user = UserSessionUtils.getUserFromSession(req.getSession());
			qnaService.deleteQuestion(questionId, user);
			return jspView("redirect:/");
		}catch(CannotDeleteException e) {
			return jspView("show.jsp").addObject("question", questionDao.findById(questionId)).addObject("answers", answerDao.findAllByQuestionId(questionId))
					.addObject("errorMessage", e.getMessage()).addObject("userId", user.getUserId());
		}
	}
}




