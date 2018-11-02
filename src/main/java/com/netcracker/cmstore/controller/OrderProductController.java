package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.dao.factory.DaoFactory;
import com.netcracker.cmstore.model.OrderProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderProductController", urlPatterns = {"/OrderProductController"})
public class OrderProductController extends ExceptionHandlingHttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert = "WEB-INF/OrderProduct.jsp";
    private OrderProductDAO orderProductDAOImpl;

    public OrderProductController() {
        super();
        orderProductDAOImpl = DaoFactory.getInstance().getOrderProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("insert".equalsIgnoreCase(action)) {
            forward = insert;
        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(Integer.valueOf(request.getParameter("orderId")));
        orderProduct.setProductId(Integer.valueOf(request.getParameter("productId")));

        if (request.getParameter("orderId") == null || request.getParameter("orderId").isEmpty()) {

        } else {
            orderProductDAOImpl.addOrderProduct(orderProduct);
        }

        response.sendRedirect(request.getContextPath() + "/OrderController?action=listOrder");

    }
}


