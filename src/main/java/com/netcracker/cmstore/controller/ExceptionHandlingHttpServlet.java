package com.netcracker.cmstore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandlingHttpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingHttpServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            super.service(request, response);
        } catch (ServletException | IOException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.error("Exception while http request handling", ex);
            if (!response.isCommitted()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
