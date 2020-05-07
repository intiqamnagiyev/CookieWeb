package servlet;

import model.Operation;
import model.User;
import service.OperationService;
import service.impl.OperationServiceImpl;
import session.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class HistoryServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    private OperationService operationService;


    public HistoryServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        operationService = new OperationServiceImpl();
        User user = Session.getUser();
        List<Operation> operationsByUser = operationService.getOperationsByUser(user.getId());
            data.put("history",operationsByUser);
         templateEngine.render("history.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
