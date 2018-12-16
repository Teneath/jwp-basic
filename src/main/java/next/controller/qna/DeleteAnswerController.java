package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.CannotDeleteException;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Result;
import next.model.User;
import next.service.QnaService;

public class DeleteAnswerController extends AbstractController {
    private QuestionDao questionDao = QuestionDao.getInstance();
    private QnaService qnaService = QnaService.getInstance();
    
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		long answerId = Long.parseLong(req.getParameter("answerId"));   
		long questionId = Long.parseLong(req.getParameter("questionId"));
        User user = null;
        
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
        else try {	
			user = UserSessionUtils.getUserFromSession(req.getSession());
			qnaService.deleteAnswer(answerId, user);
			return jsonView().addObject("result", Result.ok()).addObject("countOfComment", questionDao.findById(questionId).getCountOfComment());
		}catch(DataAccessException e) {
			return jspView("/user/show.jsp").addObject("CannotUpdate", true);
		}
		
        
	}
}

