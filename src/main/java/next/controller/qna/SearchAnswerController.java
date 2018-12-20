package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class SearchAnswerController extends AbstractController {
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {
		String link = req.getParameter("srchTerm");
    	return jspView("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+link);
	}
}