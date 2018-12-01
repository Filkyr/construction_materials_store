package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Customer;
import com.netcracker.cmstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "/CustomerController")
@Controller
public class CustomerController {

    private static String insert_or_edit = "WEB-INF/Customer.jsp";
    private static String list_customer = "WEB-INF/ListCustomer.jsp";
    private final CustomerService customerServiceImpl;

    @Autowired
    public CustomerController(CustomerService customerService) {
        super();
        this.customerServiceImpl = customerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {
            int customerId = Integer.parseInt(request.getParameter("id"));

            customerServiceImpl.removeCustomer(customerId);

            forward = list_customer;

            request.setAttribute("customers", customerServiceImpl.getCustomers());

        } else if ("edit".equalsIgnoreCase(action)) {
            forward = insert_or_edit;
            int customerId = Integer.parseInt(request.getParameter("id"));

            Customer customer = customerServiceImpl.getCustomerById(customerId);

            request.setAttribute("customer", customer);

        } else if ("listCustomer".equalsIgnoreCase(action)) {
            forward = list_customer;
            request.setAttribute("customers", customerServiceImpl.getCustomers());

        } else if ("insert".equalsIgnoreCase(action)) {

            forward = insert_or_edit;

        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = new Customer();
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setPhoneNum(request.getParameter("phoneNum"));
        customer.setAddress(request.getParameter("address"));
        String customerId = request.getParameter("id");

        if (customerId == null || customerId.isEmpty()) {
            customerServiceImpl.addCustomer(customer);
        } else {
            customer.setId(Integer.parseInt(customerId));
            customerServiceImpl.updateCustomer(customer);
        }

        response.sendRedirect(request.getContextPath() + "/CustomerController?action=listCustomer");

    }

}

