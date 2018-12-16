package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.User;

public class ShowQuestionController extends AbstractController {
	QuestionDao questionDao = QuestionDao.getInstance();
	AnswerDao answerDao = AnswerDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		String userId = null;
		
		ModelAndView mav = jspView("/qna/show.jsp");
		User user = UserSessionUtils.getUserFromSession(req.getSession());
		if(user != null)
			userId = user.getUserId();
		mav.addObject("question", questionDao.findById(questionId));
		mav.addObject("answers", answerDao.findAllByQuestionId(questionId));
		mav.addObject("userId", userId);
		
		return mav;
	}

}
