package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Order;
import com.netcracker.cmstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/")
@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        super();
        this.orderService = orderService;
    }

    @GetMapping("/order/list")
    public String listOrder(ModelMap model) {
        List orders = orderService.getOrders();
        model.addAttribute("orders", orders);
        return "ListOrder";
    }

    @PostMapping("/order-{orderId}/delete/product-{productId}")
    public String deleteOrder(@PathVariable String orderId, @PathVariable String productId) {

        int oId = 0;
        int pId = 0;

        try {
            oId = Integer.valueOf(orderId);
            pId = Integer.valueOf(productId);
        } catch (NumberFormatException e) {
            System.err.println("NumberFormatException");
        }

        if (pId > 0) {
            System.out.println("pId:" + pId);
            orderService.removeOrderProduct(oId, pId);
        } else {
            System.out.println("oId:" + oId);
            orderService.removeOrder(oId);
        }
        return "redirect:/order/list";
    }

    @GetMapping("/order/insert")
    public String newOrder(ModelMap model) {
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("edit", false);
        return "Order";
    }

    @PostMapping("/order/insert")
    public String saveOrder(@Valid Order order, BindingResult result) {
        System.out.println(result);
        if (result.hasErrors()) {
            return "Order";
        }
        orderService.addOrder(order);
        return "redirect:/order/list";
    }

    @GetMapping("/order/product/insert")
    public String newOrderProduct(ModelMap model) {
        Order order = new Order();
        model.addAttribute("order", order);
        model.addAttribute("edit", false);
        return "OrderProduct";
    }

    @PostMapping("/order/product/insert")
    public String saveOrderProduct(@RequestParam Map<String,Object> body) {
        int oId = Integer.valueOf(String.valueOf(body.get("orderId")));
        int pId = Integer.valueOf(String.valueOf(body.get("productId")));

        orderService.addOrderProduct(oId, pId);
        return "redirect:/order/list";
    }
}

