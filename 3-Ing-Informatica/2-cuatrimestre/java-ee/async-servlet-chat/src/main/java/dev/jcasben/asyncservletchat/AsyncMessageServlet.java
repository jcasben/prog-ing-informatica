package dev.jcasben.asyncservletchat;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AsyncMessageServlet", value = "/asyncchat", asyncSupported = true)
public class AsyncMessageServlet extends HttpServlet {
    private static String user1 = null, user2 = null;
    private static String text1 = "", text2 = "";
    private static AsyncContext ctx1 = null, ctx2 = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        if (session == null) return;

        String username = (String) session.getAttribute("username");
        if (username == null) return;

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(0);

        synchronized (AsyncMessageServlet.class) {
            if (username.equals(user1)) {
                ctx1 = asyncContext;
            } else if (username.equals(user2)) {
                ctx2 = asyncContext;
            } else if (user1 == null) {
                user1 = username;
                ctx1 = asyncContext;
            } else if (user2 == null) {
                user2 = username;
                ctx2 = asyncContext;
            }
        }
    }

    public static void updateInput(String user, String text) {
        synchronized (AsyncMessageServlet.class) {
            if (user.equals(user1)) {
                text1 = text;
            } else if (user.equals(user2)) {
                text2 = text;
            }

            sendToOther(user);
        }
    }

    private static void sendToOther(String fromUser) {
        try {
            if (fromUser.equals(user1) && ctx2 != null) {
                send(ctx2,fromUser, text1);
                ctx2.complete();
                ctx2 = null;
            } else if (fromUser.equals(user2) && ctx1 != null) {
                send(ctx1, fromUser, text2);
                ctx1.complete();
                ctx1 = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void send(AsyncContext ctx, String fromUser, String message) throws IOException {
        HttpServletResponse response = (HttpServletResponse) ctx.getResponse();
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println(String.format("""
                <?xml version="1.0"?>
                <message>
                    <line>%s: %s</line>
                </message>
                """, fromUser, message));
    }
}
