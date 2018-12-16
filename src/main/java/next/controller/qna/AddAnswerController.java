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
import next.model.Result;
import next.model.User;

public class AddAnswerController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
	private QuestionDao questionDao = QuestionDao.getInstance();
	private AnswerDao answerDao = AnswerDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			return jsonView().addObject("result", Result.fail("로그인이 필요합니다"));
		}
		
		User user = UserSessionUtils.getUserFromSession(req.getSession());
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		Answer answer = new Answer(user.getUserId(), req.getParameter("contents"), questionId);
        log.debug("answer : {}", answer);
        Answer savedAnswer = answerDao.insert(answer);
        log.debug("saved answer : {}", savedAnswer);
        questionDao.updateCountOfAnswer(savedAnswer.getQuestionId(), 1);
        return jsonView().addObject("answer", savedAnswer).addObject("countOfComment", questionDao.findById(questionId).getCountOfComment()).addObject("result", Result.ok());
	}
}




