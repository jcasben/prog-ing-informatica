package dev.jcasben.asyncchat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "SendMessageServlet", value = "/send")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) return;

        String username = (String) session.getAttribute("username");
        if (username == null) return;

        String input = req.getParameter("text");
        if (input == null) input = "";

        AsyncMessageServlet.updateInput(username, input);
    }
}
