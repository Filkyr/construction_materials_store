package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Customer.jsp";
    private static String list_customer = "/ListCustomer.jsp";
    private CustomerDAO customerDAO;

    public CustomerController() {
        super();
        customerDAO = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int customerId = Integer.parseInt(request.getParameter("id"));

            customerDAO.removeCustomer(customerId);

            forward = list_customer;
            try {
                request.setAttribute("customers", customerDAO.getCustomers());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int customerId = Integer.parseInt(request.getParameter("id"));
            try {
                Customer customer = customerDAO.getCustomerById(customerId);
                request.setAttribute("customer", customer);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("listCustomer")) {
            forward = list_customer;
            try {
                request.setAttribute("customers", customerDAO.getCustomers());

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
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setPhoneNum(request.getParameter("phoneNum"));
        customer.setAddress(request.getParameter("address"));
        String customerId = request.getParameter("id");

        if (customerId == null || customerId.isEmpty()) {
            customerDAO.addCustomer(customer);
        } else {
            customer.setId(Integer.parseInt(customerId));
            customerDAO.updateCustomer(customer);
        }

        response.sendRedirect(request.getContextPath() + "/CustomerController?action=listCustomer");

    }

}

