package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.UserDao;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class ProfileController extends AbstractController {
	private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);
	UserDao userDao = UserDao.getInstance();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("userId");
        ModelAndView mav = jspView("/user/profile.jsp");
        mav.addObject("user", userDao.findByUserId(userId));
        return mav;
    }
}
