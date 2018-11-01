package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.dao.factory.DaoFactory;
import com.netcracker.cmstore.model.Producer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProducerController", urlPatterns = {"/ProducerController"})
public class ProducerController extends ExceptionHandlingHttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Producer.jsp";
    private static String list_producer = "/ListProducer.jsp";
    private ProducerDAO producerDAOImpl;

    public ProducerController() {
        super();
        producerDAOImpl = DaoFactory.getInstance().getProducerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) { // better to eq start from constant, cuz it prevent you from NPE
            int producerId = Integer.parseInt(request.getParameter("producerId"));

            producerDAOImpl.removeProducer(producerId);

            forward = list_producer;
            request.setAttribute("producers", producerDAOImpl.getProducers());

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int producerId = Integer.parseInt(request.getParameter("producerId"));
            Producer producer = producerDAOImpl.getProducerById(producerId);
            request.setAttribute("producer", producer);

        } else if (action.equalsIgnoreCase("listProducer")) {
            forward = list_producer;
            request.setAttribute("producers", producerDAOImpl.getProducers());

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

        if (producerId == null || producerId.isEmpty()) {
            producerDAOImpl.addProducer(producer);
        } else {
            producer.setProducerId(Integer.parseInt(producerId));
            producerDAOImpl.updateProducer(producer);
        }

        response.sendRedirect(request.getContextPath() + "/ProducerController?action=listProducer");
    }

}
