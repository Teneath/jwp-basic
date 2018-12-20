package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.CannotDeleteException;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;
import next.model.User;
import next.service.QnaService;

public class DeleteAnswerController extends AbstractController {
	AnswerDao answerDao = AnswerDao.getInstance();
	QuestionDao questionDao = QuestionDao.getInstance();
	private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);
    private QnaService qnaService = QnaService.getInstance();
    
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			jspView("redirect:/users/loginForm");
		}
		
        long answerId = Long.parseLong(req.getParameter("answerId"));
        Answer answer = answerDao.findById(answerId);
        User user = UserSessionUtils.getUserFromSession(req.getSession());
        long questionId = answer.getQuestionId();
        
        try {	
			qnaService.deleteAnswer(answerId, user);
			return jsonView().addObject("result", Result.ok()).addObject("countOfComment", questionDao.findById(questionId).getCountOfComment());
		}catch(CannotDeleteException e) {
			return jspView("show.jsp").addObject("question", questionDao.findById(questionId)).addObject("answers", answerDao.findAllByQuestionId(questionId))
					.addObject("CannotDelete", true).addObject("userId", user.getUserId());
		}
		
        
	}
}

