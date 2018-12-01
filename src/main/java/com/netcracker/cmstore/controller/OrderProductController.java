package com.netcracker.cmstore.controller;

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

@RequestMapping(path = "/OrderProductController")
@Controller
public class OrderProductController {

    private static String insert = "WEB-INF/OrderProduct.jsp";
    private final OrderService orderService;

    @Autowired
    public OrderProductController(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("insert".equalsIgnoreCase(action)) {
            forward = insert;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!(request.getParameter("orderId") == null || request.getParameter("orderId").isEmpty())) {

            orderService.addOrderProduct(Integer.valueOf(request.getParameter("orderId")), Integer.valueOf(request.getParameter("productId")));
        }

        response.sendRedirect(request.getContextPath() + "/OrderController?action=listOrder");

    }
}


