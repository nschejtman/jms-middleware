package org.nschejtman.controller;

import org.nschejtman.util.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String jUsername = req.getParameter("j_username");
        final String jPassword = req.getParameter("j_password");

        if (UserDao.validate(jUsername, jPassword)) {
            final HttpSession session = req.getSession();
            session.setAttribute("username", jUsername);
            resp.sendRedirect("home.jsp");
        } else {
            resp.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }
}
