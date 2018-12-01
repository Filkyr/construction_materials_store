package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.model.Product;
import com.netcracker.cmstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "/ProductController")
@Controller
public class ProductController {

    private static String insert_or_edit = "WEB-INF/Product.jsp";
    private static String list_product = "WEB-INF/ListProduct.jsp";
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));

            productService.removeProduct(productId);

            forward = list_product;
            request.setAttribute("products", productService.getProducts());

        } else if ("edit".equalsIgnoreCase(action)) {
            forward = insert_or_edit;
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productService.getProductById(productId);
            request.setAttribute("product", product);

        } else if ("listProduct".equalsIgnoreCase(action)) {
            forward = list_product;
            request.setAttribute("products", productService.getProducts());

        } else if ("insert".equalsIgnoreCase(action)) {

            forward = insert_or_edit;

        }
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product();
        product.setTitle(request.getParameter("title"));
        product.setCategoryId(Integer.valueOf(request.getParameter("categoryId")));
        product.setProducerId(Integer.valueOf(request.getParameter("producerId")));
        product.setImage(request.getParameter("image"));
        product.setDescription(request.getParameter("description"));
        String productId = request.getParameter("productId");

        if (productId == null || productId.isEmpty()) {
            productService.addProduct(product);
        } else {
            product.setProductId(Integer.parseInt(productId));
            productService.updateProduct(product);
        }

        response.sendRedirect(request.getContextPath() + "ProductController?action=listProduct");

    }

}

