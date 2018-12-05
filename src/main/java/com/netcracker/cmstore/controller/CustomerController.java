package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Customer;
import com.netcracker.cmstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequestMapping("/")
@Controller
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
    }

    @GetMapping("/customer/list")
    public String listCustomer(ModelMap model) {
        List customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "ListCustomer";
    }

    @PostMapping("/customer/delete/{customerId}")
    public String deleteCustomer(@PathVariable String customerId) {
        customerService.removeCustomer(Integer.valueOf(customerId));
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/insert")
    public String newCustomer(ModelMap model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        model.addAttribute("edit", false);
        return "Customer";
    }

    @PostMapping("/customer/updateOrInsert")
    public String saveCustomer(@Valid Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "Customer";
        }
        customerService.insertOrUpdateCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/customer/edit/{customerId}")
    public String editCustomer(@PathVariable String customerId, ModelMap model) {
        Customer customer = customerService.getCustomerById(Integer.valueOf(customerId));
        model.addAttribute("customer", customer);
        model.addAttribute("edit", true);
        return "Customer";
    }
}

