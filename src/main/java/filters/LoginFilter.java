package filters;


import model.User;
import service.UserService;
import service.impl.UserServiceImpl;
import session.Session;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginFilter implements Filter {
    private UserService userService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals("POST")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            userService = new UserServiceImpl();
            Optional<User> userByEmail = userService.getUserByEmail(email);
            if (userByEmail.isPresent() && userByEmail.get().getPassword().equalsIgnoreCase(password)){
                Session.setUser(userByEmail.get());
                Cookie cookie = new Cookie("user", "principalUser");
                cookie.setPath("/calc");
                response.addCookie(cookie);
                chain.doFilter(request, response);
            }else {
                response.sendRedirect("/calc/login");
            }
        }else chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
