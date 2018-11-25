package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "/ProducerController")
@Controller
public class ProducerController {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "WEB-INF/Producer.jsp";
    private static String list_producer = "WEB-INF/ListProducer.jsp";
    private final ProducerDAO producerDao;

    @Autowired
    public ProducerController(ProducerDAO producerDao) {
        super();
        this.producerDao = producerDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) { // better to eq start from constant, cuz it prevent you from NPE
            int producerId = Integer.parseInt(request.getParameter("producerId"));

            producerDao.removeProducer(producerId);

            forward = list_producer;
            request.setAttribute("producers", producerDao.getProducers());

        } else if ("edit".equalsIgnoreCase(action)) {
            forward = insert_or_edit;
            int producerId = Integer.parseInt(request.getParameter("producerId"));
            Producer producer = producerDao.getProducerById(producerId);
            request.setAttribute("producer", producer);

        } else if ("listProducer".equalsIgnoreCase(action)) {
            forward = list_producer;
            request.setAttribute("producers", producerDao.getProducers());

        } else if ("insert".equalsIgnoreCase(action)) {

            forward = insert_or_edit;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Producer producer = new Producer();
        producer.setBrand_name(request.getParameter("brand_name"));
        producer.setDescription(request.getParameter("description"));
        producer.setLogo(request.getParameter("logo"));
        String producerId = request.getParameter("producerId");

        if (producerId == null || producerId.isEmpty()) {
            producerDao.addProducer(producer);
        } else {
            producer.setProducerId(Integer.parseInt(producerId));
            producerDao.updateProducer(producer);
        }

        response.sendRedirect(request.getContextPath() + "/ProducerController?action=listProducer");
    }

}
