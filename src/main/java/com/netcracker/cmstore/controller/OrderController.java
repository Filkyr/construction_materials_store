package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Order;
import com.netcracker.cmstore.service.OrderService;
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
    private final OrderService orderService;
    private final static String ERROR_MESSAGE = "NumberFormatException in 'OrderController' while processing orderId or productId";

    @Autowired
    public OrderController(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {

            String orderIdString = request.getParameter("id").replaceAll("\\s+", "");
            String productIdString = request.getParameter("productId").replaceAll("\\s+", "");

            int orderId = 0;
            int productId = 0;

            try {
                orderId = Integer.parseInt(orderIdString);
                productId = Integer.parseInt(productIdString);
            } catch (NumberFormatException e) {
                System.out.println(ERROR_MESSAGE);
            }

            if (productId > 0) {
                orderService.removeOrderProduct(orderId, productId);
            } else {
                orderService.removeOrder(orderId);
            }

            forward = list_order;

            request.setAttribute("orders", orderService.getOrders());

        } else if ("listOrder".equalsIgnoreCase(action)) {
            forward = list_order;
            request.setAttribute("orders", orderService.getOrders());

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

        orderService.addOrder(order);

        System.out.println("customerId: " + Integer.valueOf(request.getParameter("customerId")));
        System.out.println("date: " + request.getParameter("date"));

        response.sendRedirect(request.getContextPath() + "/OrderController?action=listOrder");

    }
}

