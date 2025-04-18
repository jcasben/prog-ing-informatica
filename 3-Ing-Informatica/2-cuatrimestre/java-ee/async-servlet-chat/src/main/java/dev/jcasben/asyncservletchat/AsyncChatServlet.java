package dev.jcasben.asyncservletchat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AsyncChatServlet", value = "/chat")
public class AsyncChatServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        long version = java.util.Calendar.getInstance().getTimeInMillis();

        String username = (String) request.getSession().getAttribute("username");

        out.println(String.format("""
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>Async Chat</title>
                        <script type='text/javascript' src='js/chat.js?ver="%d%n"'></script>
                        <link rel="stylesheet" href="styles.css">
                    </head>
                    <body onload="startRequest()">
                        <h1>Welcome, %s!</h1>
                        <div id="chat">
                            <div id="message""></div>
                            <input id="input" type="text" oninput="sendInput(this.value)">
                        </div>
                    </body>
                </html>
                """, version, username));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            username = req.getParameter("username");
            if (username == null || username.trim().isEmpty()) {
                resp.sendRedirect("");
                return;
            }
            session.setAttribute("username", username);
        }
        processRequest(req, resp);
    }
}
