package filters;

import model.Calculator;
import session.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CalculatorFilter implements Filter {

    private Calculator calc;

    public CalculatorFilter(Calculator c) {
        this.calc = c;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // I want to filter only POST requests

     if (Session.getUser()!=null){
         if (req.getMethod().equalsIgnoreCase("POST")) {
             Map<String, String[]> allParams = req.getParameterMap();
             boolean all =
                     allParams.containsKey("x") &&
                             allParams.containsKey("y") &&
                             allParams.containsKey("op");
             if (all && allValid(req)) {

                 chain.doFilter(request, response);
             } else {

                 resp.sendRedirect("/calc/do");
             }
         } else
             // I don't want to filter any other requests
             chain.doFilter(request, response);
     }else resp.sendRedirect("/calc/login");
    }

    private boolean allValid(HttpServletRequest req) {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String op = req.getParameter("op");
        try {
            Integer.parseInt(x);
            Integer.parseInt(y);
            return calc.allowedOps().contains(op);
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Override
    public void destroy() {

    }
}
