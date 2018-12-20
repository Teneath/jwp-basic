package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.User;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class UpdateFormUserController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);
	UserDao userDao = UserDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return jspView("redirect:/users/loginForm");
        }
        
        User user = userDao.findByUserId(req.getParameter("userId"));

        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            ModelAndView mav = jspView("/user/list.jsp");
            mav.addObject("users", userDao.findAll()).addObject("CannotChange", true);
        	return mav;
        }
        ModelAndView mav = jspView("/user/updateForm.jsp");
        mav.addObject("user", user);
        return mav;
    }
}
