import filters.CalculatorFilter;
import filters.CookieFilter;
import filters.HistoryFilter;
import filters.LoginFilter;
import model.Calculator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApp {
    private static final EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        // in that syntax you can't pass parameters to the servlet

        TemplateEngine engine = TemplateEngine.folder("./content");

        Calculator c = new Calculator();
        handler.addServlet(new ServletHolder(new CalcServlet(c, engine)), "/calc/do/*");
        handler.addServlet(new ServletHolder(new LogoutServlet()),"/calc/logout/*");
        handler.addServlet(new ServletHolder(new HistoryServlet(engine)),"/calc/history/*");
        handler.addServlet(LoginServlet.class, "/calc/login");

   /* handler.addServlet(UploadServlet.class, "/upload/*")
            .getRegistration().setMultipartConfig(new MultipartConfigElement("./from_user"));*/

        handler.addFilter(new FilterHolder(new LoginFilter()), "/calc/login", ft);
        handler.addFilter(new FilterHolder(new CalculatorFilter(c)), "/calc/do", ft);
        handler.addFilter(new FilterHolder(new HistoryFilter()),"/calc/history/*", ft);
        handler.addServlet((new ServletHolder(new StaticContentServlet("content"))), "/static/*");
        // in that syntax you can't pass parameters to the servlet filter
        handler.addFilter(CookieFilter.class, "/b", ft);

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
