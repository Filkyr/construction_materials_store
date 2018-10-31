package com.netcracker.cmstore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.model.Producer;

@WebServlet(name = "ProducerController", urlPatterns = {"/ProducerController"})
public class ProducerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Producer.jsp";
    private static String list_producer = "/ListProducer.jsp";
    private ProducerDAO producerDAO;

    public ProducerController() {
        super();
        producerDAO = new ProducerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int producerId = Integer.parseInt(request.getParameter("producerId"));

            producerDAO.removeProducer(producerId);

            forward = list_producer;
            try {
                request.setAttribute("producers", producerDAO.getProducers());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int producerId = Integer.parseInt(request.getParameter("producerId"));
            try {
                Producer producer = producerDAO.getProducerById(producerId);
                request.setAttribute("producer", producer);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("listProducer")) {
            forward = list_producer;
            try {
                request.setAttribute("producers", producerDAO.getProducers());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (action.equalsIgnoreCase("insert")) {

            forward = insert_or_edit;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producer producer = new Producer();
        producer.setBrand_name(request.getParameter("brand_name"));
        producer.setDescription(request.getParameter("description"));
        producer.setLogo(request.getParameter("logo"));
        String producerId = request.getParameter("producerId");
//        System.out.println("producerId:");
//        System.out.println(producerId);

        if (producerId == null || producerId.isEmpty()) {
            producerDAO.addProducer(producer);
        } else {
            producer.setProducerId(Integer.parseInt(producerId));
            producerDAO.updateProducer(producer);
        }

        response.sendRedirect(request.getContextPath() + "/ProducerController?action=listProducer");
    }

}
