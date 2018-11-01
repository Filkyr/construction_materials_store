package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.dao.factory.DaoFactory;
import com.netcracker.cmstore.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends ExceptionHandlingHttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Customer.jsp";
    private static String list_customer = "/ListCustomer.jsp";
    private CustomerDAO customerDAOImpl;

    public CustomerController() {
        super();
        customerDAOImpl = DaoFactory.getInstance().getCustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int customerId = Integer.parseInt(request.getParameter("id"));

            customerDAOImpl.removeCustomer(customerId);

            forward = list_customer;

            request.setAttribute("customers", customerDAOImpl.getCustomers());

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int customerId = Integer.parseInt(request.getParameter("id"));

            Customer customer = customerDAOImpl.getCustomerById(customerId);

            request.setAttribute("customer", customer);

        } else if (action.equalsIgnoreCase("listCustomer")) {
            forward = list_customer;
            request.setAttribute("customers", customerDAOImpl.getCustomers());

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
            customerDAOImpl.addCustomer(customer);
        } else {
            customer.setId(Integer.parseInt(customerId));
            customerDAOImpl.updateCustomer(customer);
        }

        response.sendRedirect(request.getContextPath() + "/CustomerController?action=listCustomer");

    }

}

