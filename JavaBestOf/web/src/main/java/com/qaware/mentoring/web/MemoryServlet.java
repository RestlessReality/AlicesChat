package com.qaware.mentoring.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple HTTP Servlet which returns the number of MB used by the JVM.
 */
public class MemoryServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryServlet.class);

    private static final int BYTES_PER_MB = 1024 * 1024;
    private static final String CONTENT_TYPE = "text/html";
    private static final String BG_COLOR_PROPERTY = "bgColor";

    private String bgColor;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        LOGGER.info("MemoryServlet init() called");
        bgColor = getServletConfig().getInitParameter(BG_COLOR_PROPERTY);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("MemoryServlet doGet() called");
        resp.setContentType(CONTENT_TYPE);

        Runtime runtime = Runtime.getRuntime();
        long used = (runtime.totalMemory() - runtime.freeMemory()) / BYTES_PER_MB;
        long freeAndUnallocated = (runtime.maxMemory() - used) / BYTES_PER_MB;

        try {
            PrintWriter out = resp.getWriter();
            out.println("<h1 style=\"color:" + bgColor + "\">Used memory: " + used + " MB</h1>");
            out.println("<h1 style=\"color:" + bgColor + "\">Free memory: " + freeAndUnallocated + " MB</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
