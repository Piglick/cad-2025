package ru.bsuedu.cad.lab.app;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.bsuedu.cad.lab.service.DataLoaderService;

@WebListener
public class DataInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());

        DataLoaderService dataLoaderService = context.getBean(DataLoaderService.class);
        dataLoaderService.loadData();

        System.out.println("Initial data loaded successfully");
    }
}