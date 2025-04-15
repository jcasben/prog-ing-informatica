package dev.jcasben.chatservlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "chatServlet", value = "/chat")
public class ChatServlet extends HttpServlet {
    private static final long INACTIVITY_LIMIT = 5 * 60 * 1000;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Init complete");
        ServletContext ctx = getServletContext();
        ctx.setAttribute("messages", new ArrayList<String>());
        ctx.setAttribute("users", new ConcurrentHashMap<String, Long>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        Map<String, Long> users = (Map<String, Long>) getServletContext().getAttribute("users");
        users.put(username, System.currentTimeMillis());

        String action = req.getParameter("action");
        String message = req.getParameter("message");

        if (action != null) {
            System.out.println(action);
            switch (action) {
                case "Send" -> {
                    if (message != null && !message.trim().isEmpty()) {
                        List<String> messages = (List<String>) getServletContext().getAttribute("messages");
                        messages.add(String.format("%s: %s", username, message));
                    }
                }
                case "Clear" -> {
                    List<String> messages = (List<String>) getServletContext().getAttribute("messages");
                    messages.clear();
                }
                case "Logout" -> {
                    users.remove(username);
                    session.invalidate();
                    resp.sendRedirect("");
                    return;
                }
            }
        }

        removeInactiveUsers();
        showChatPage(resp, session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("");
            return;
        }

        String username = (String) session.getAttribute("username");
        Map<String, Long> users = (Map<String, Long>) getServletContext().getAttribute("users");
        users.put(username, System.currentTimeMillis());

        removeInactiveUsers();
        showChatPage(resp, session);
    }

    private void removeInactiveUsers() {
        Map<String, Long> users = (Map<String, Long>) getServletContext().getAttribute("users");
        long now = System.currentTimeMillis();
        users.entrySet().removeIf(entry -> now - entry.getValue() >= INACTIVITY_LIMIT);
    }

    private void showChatPage(HttpServletResponse resp, HttpSession session) throws IOException {
        String username = (String) session.getAttribute("username");
        Map<String, Long> users = (Map<String, Long>) getServletContext().getAttribute("users");
        List<String> messages = (List<String>) getServletContext().getAttribute("messages");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println(String.format("""
                <html>
                    <head>
                        <title>Multiuser Chat</title>
                        <link rel="stylesheet" href="styles.css">
                    </head>
                    <body>
                        <div class="chat-container">
                            <h1>Welcome, %s!</h1>
                            <div class="chat-users">
                                <h3>Active users:</h3>
                                <ul>
                """, username));

        users.keySet().forEach(user -> out.println(String.format("<li>%s</li>", user)));

        out.println("""
                                </ul>
                            </div>
                            <div class="chat-messages">
                                <h3>Messages</h3>
                                <div>
                """);

        System.out.println(messages.toString());
        messages.forEach(message -> out.println(String.format("<p>%s</p>", message)));

        out.println("""
                                </div>
                            </div>
                            <form class="chat-form" method="post">
                                <input type="text" name="message" placeholder="Write a message..."/>
                                <input type="submit" name="action" value="Send" />
                                <input type="submit" name="action" value="Clear" />
                                <input type="submit" name="action" value="Refresh" />
                                <input type="submit" name="action" value="Logout" />
                            </form>
                        </div>
                    </body>
                </html>
                """);
    }
}
