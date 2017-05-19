package org.nschejtman.controller;

import org.nschejtman.model.User;
import org.nschejtman.util.UserDao;
import org.nschejtman.service.JMSHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    public RegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String jUsername = req.getParameter("j_username");
        final String jPassword = req.getParameter("j_password");
        UserDao.register(jUsername, jPassword);
        final User user = new User(jUsername, jPassword);
        final JMSHandler jmsHandler = new JMSHandler();
        jmsHandler.registerUser(user);
        final HttpSession session = req.getSession();
        session.setAttribute("username", jUsername);
        final RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
