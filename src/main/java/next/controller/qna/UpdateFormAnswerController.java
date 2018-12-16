package next.controller.qna;

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
import next.model.Question;
import next.model.User;

public class UpdateFormAnswerController extends AbstractController {	
	AnswerDao answerDao = AnswerDao.getInstance();
	QuestionDao qusetionDao = QuestionDao.getInstance();
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }

        long answerId = Long.parseLong(req.getParameter("answerId"));
        Answer answer = answerDao.findById(answerId);
        User user = UserSessionUtils.getUserFromSession(req.getSession());
        if (!answer.getWriter().equals(user.getUserId())){return jspView("/user/show.jsp").addObject("CannotUpdate", true);}
        log.debug("UpdateFrom Answer : {} ", answer);
        long questionId = answer.getQuestionId();
        Question question = qusetionDao.findById(questionId);
        
        return jspView("/qna/updateAnswer.jsp").addObject("question", question).addObject("answer", answer).addObject("userId", user.getUserId());
	}
}



