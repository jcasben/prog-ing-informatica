package dev.jcasben.noticeboardclient2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/noticeBoard")
public class NoticeBoardServlet extends HttpServlet {
    private final NoticeBoardServiceImpl service = new NoticeBoard().getNoticeBoardServiceImplPort();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            String message = req.getParameter("message");
            int minutes = Integer.parseInt(req.getParameter("minutes"));
            String code = service.createNotice(message, minutes);
            req.setAttribute("noticeCode", code);
        } else if ("delete".equals(action)) {
            String code = req.getParameter("deleteCode");
            boolean result = service.deleteNotice(code);
            req.setAttribute("deleteSuccess", result);
        }

        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Notice> notices = service.getAllNotices();
        req.setAttribute("notices", notices);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
