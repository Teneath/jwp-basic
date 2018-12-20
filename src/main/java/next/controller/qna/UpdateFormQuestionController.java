package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;
import next.service.QnaService;

public class UpdateFormQuestionController extends AbstractController {
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
		Question question = questionDao.findById(questionId);
		user = UserSessionUtils.getUserFromSession(req.getSession());
		
		if (!question.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
			return jspView("show.jsp").addObject("question", questionDao.findById(questionId)).addObject("answers", answerDao.findAllByQuestionId(questionId))
					.addObject("CannotUpdate",true).addObject("userId", user.getUserId());
        }
		
		else return jspView("/qna/update.jsp").addObject("question", question);
	}

}

