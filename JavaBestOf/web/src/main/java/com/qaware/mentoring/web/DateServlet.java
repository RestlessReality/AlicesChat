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
import java.time.LocalDateTime;

/**
 * Simple servlet which returns the date on GET HTTP requests.
 *
 * Note: usually you would not implement a HttpServlet yourself,
 * but use a library which uses it.
 */
public class DateServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateServlet.class);

    private static final String CONTENT_TYPE = "text/html";
    private static final String BG_COLOR_PROPERTY = "bgColor";
    private String bgColor;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        LOGGER.info("DateServlet init() called");
        bgColor = getServletConfig().getInitParameter(BG_COLOR_PROPERTY);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOGGER.info("DateServlet doGet() called");
        resp.setContentType(CONTENT_TYPE);
        try {
            PrintWriter out = resp.getWriter();
            out.println("<h1 style=\"color:" + bgColor + "\">" + LocalDateTime.now() + "</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
