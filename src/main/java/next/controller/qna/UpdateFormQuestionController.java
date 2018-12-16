package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.model.Question;

public class UpdateFormQuestionController extends AbstractController {
	QuestionDao questionDao = QuestionDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) 
			throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			return jspView("redirect:/users/loginForm");
		}
		
		long questionId = Long.parseLong(req.getParameter("questionId"));
		Question question = questionDao.findById(questionId);
		
		if (!question.isSameUser(UserSessionUtils.getUserFromSession(req.getSession()))) {return jspView("/user/show.jsp").addObject("CannotUpdate", true);
        }
		
		return jspView("/qna/update.jsp").addObject("question", question);
	}

}

