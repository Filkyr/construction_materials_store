package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

            int orderId = Integer.parseInt(request.getParameter("id"));
            int productId = 0;

            try {
                productId = Integer.parseInt(request.getParameter("productId"));
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException in productId");
            }

            if (productId > 0) {
               // orderDao.removeOrderProduct(orderId, productId);
            } else {
                //orderDao.removeOrder(orderId);
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

