package servlet;

import model.Calculator;
import model.Operation;
import service.OperationService;
import service.impl.OperationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CalcServlet extends HttpServlet {

    private Calculator calc;
    private TemplateEngine engine;
    private OperationService operationService;

    public CalcServlet(Calculator calc, TemplateEngine engine) {
        this.calc = calc;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = new BufferedReader(new FileReader(new File("content/calc.ftl"))).lines()
                .collect(Collectors.joining("\n"));

        try (PrintWriter writer = resp.getWriter()) {
            writer.write(content);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String op = req.getParameter("op");

        HashMap<String, Object> result = new HashMap<>();
        Operation operation = calc.doOperation(x, y, op);
        result.put("result", operation);
            operationService=new OperationServiceImpl();
        operationService.save(operation);
        engine.render("calc.ftl", result, resp);

    }
}
