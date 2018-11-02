package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.dao.factory.DaoFactory;
import com.netcracker.cmstore.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends ExceptionHandlingHttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert = "WEB-INF/Order.jsp";
    private static String list_order = "WEB-INF/ListOrder.jsp";
    private OrderDAO orderDAOImpl;
    private OrderProductDAO orderProductDAOImpl;

    public OrderController() {
        super();
        orderDAOImpl = DaoFactory.getInstance().getOrderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                orderProductDAOImpl = DaoFactory.getInstance().getOrderProductDAO();
                orderProductDAOImpl.removeOrderProduct(orderId, productId);
            } else {
                orderDAOImpl.removeOrder(orderId);
            }

            forward = list_order;

            request.setAttribute("orders", orderDAOImpl.getOrders());

        } else if ("listOrder".equalsIgnoreCase(action)) {
            forward = list_order;
            request.setAttribute("orders", orderDAOImpl.getOrders());

        } else if ("insert".equalsIgnoreCase(action)) {
            forward = insert;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Order order = new Order();

        order.setCustomerId(Integer.valueOf(request.getParameter("customerId")));
        order.setDate(request.getParameter("date"));


        orderDAOImpl.addOrder(order);

        System.out.println("customerId: " + Integer.valueOf(request.getParameter("customerId")));
        System.out.println("date: " + request.getParameter("date"));


        response.sendRedirect(request.getContextPath() + "/OrderController?action=listOrder");

    }
}

