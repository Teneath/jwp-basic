package next.controller.qna;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.User;

public class UpdateAnswerController extends AbstractController {
	
	QuestionDao questionDao = QuestionDao.getInstance();
	AnswerDao answerDao = AnswerDao.getInstance();
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
	
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

		Long questionId = Long.parseLong(req.getParameter("questionId"));
		Long answerId = Long.parseLong(req.getParameter("answerId"));		
		
		Answer answer = answerDao.findById(answerId);
		
		Answer savedAnswer = new Answer(answer.getAnswerId(), answer.getWriter(), req.getParameter("contents"), new Date(), answer.getQuestionId());
		answerDao.update(savedAnswer);
		
		log.debug("update Answer : {}", savedAnswer);
		
		ModelAndView mav = jspView("/qna/show.jsp");
		
		String userId = null;
		User user = UserSessionUtils.getUserFromSession(req.getSession());	
		if (!answer.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {
			return jspView("show.jsp").addObject("question", questionDao.findById(questionId)).addObject("answers", answerDao.findAllByQuestionId(questionId))
					.addObject("CannotUpdate", true).addObject("userId", user.getUserId());
        }
		if(user != null)
		userId = user.getUserId();
		mav.addObject("question", questionDao.findById(questionId));
		mav.addObject("answers", answerDao.findAllByQuestionId(questionId));
		mav.addObject("userId", userId);
		mav.addObject("errorMessage", null);
		
		return mav;
	}
}




