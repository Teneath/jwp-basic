package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;

public class ShowController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = QuestionDao.getInstance();
        AnswerDao answerDao = AnswerDao.getInstance();
       
        req.setAttribute("question", questionDao.findById(questionId));
        req.setAttribute("answers", answerDao.findAllByQuestionId(questionId));
        return jspView("home.jsp").addObject("questions", questionDao.findAll());
    }
}