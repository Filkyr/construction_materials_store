package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@RequestMapping(path = "/OrderController")
@Controller
public class OrderController {

    private static String insert = "WEB-INF/Order.jsp";
    private static String list_order = "WEB-INF/ListOrder.jsp";
    private final OrderDAO orderDao;

    @Autowired
    public OrderController(OrderDAO orderDao) {
        super();
        this.orderDao = orderDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {

            Enumeration<String> params = request.getParameterNames();
            while(params.hasMoreElements()){
                String paramName = params.nextElement();
                System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
            }

            String orderIdString = request.getParameter("id").replaceAll("\\s+","");
            String productIdString = request.getParameter("productId").replaceAll("\\s+","");

            int orderId = 0;
            int productId = 0;

            try {
                orderId = Integer.parseInt(orderIdString);
                productId = Integer.parseInt(productIdString);
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException in 'OrderController' while processing orderId or productId");
            }

            if (productId > 0) {
                orderDao.removeOrderProduct(orderId, productId);
            } else {
                orderDao.removeOrder(orderId);
            }

            forward = list_order;

            request.setAttribute("orders", orderDao.getOrders());

        } else if ("listOrder".equalsIgnoreCase(action)) {
            forward = list_order;
            request.setAttribute("orders", orderDao.getOrders());

        } else if ("insert".equalsIgnoreCase(action)) {
            forward = insert;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order order = new Order();

        order.setCustomerId(Integer.valueOf(request.getParameter("customerId")));
        order.setDate(request.getParameter("date"));

        orderDao.addOrder(order);

        System.out.println("customerId: " + Integer.valueOf(request.getParameter("customerId")));
        System.out.println("date: " + request.getParameter("date"));


        response.sendRedirect(request.getContextPath() + "/OrderController?action=listOrder");

    }
}

