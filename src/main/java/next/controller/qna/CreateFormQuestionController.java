package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;

public class CreateFormQuestionController extends AbstractController {
	UserDao userDao = UserDao.getInstance();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(!UserSessionUtils.isLogined(req.getSession())) {
			return jspView("redirect:/users/loginForm");
		}
		
		User user = userDao.findByUserId(req.getParameter("userId"));
		
		ModelAndView mav = jspView("/qna/form.jsp");
        mav.addObject("user", user);
		return mav;
	}

}


