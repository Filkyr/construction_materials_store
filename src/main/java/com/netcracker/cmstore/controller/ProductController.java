package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.ProductDAO;
import com.netcracker.cmstore.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Product.jsp";
    private static String list_product = "/ListProduct.jsp";
    private ProductDAO productDAO;

    public ProductController() {
        super();
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int productId = Integer.parseInt(request.getParameter("productId"));

            productDAO.removeProduct(productId);

            forward = list_product;
            try {
                request.setAttribute("products", productDAO.getProducts());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int productId = Integer.parseInt(request.getParameter("productId"));
            try {
                Product product = productDAO.getProductById(productId);
                request.setAttribute("product", product);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("listProduct")) {
            forward = list_product;
            try {
                request.setAttribute("products", productDAO.getProducts());

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
        Product product = new Product();
        product.setTitle(request.getParameter("title"));
        product.setCategoryId(Integer.valueOf(request.getParameter("categoryId")));
        product.setProducerId(Integer.valueOf(request.getParameter("producerId")));
        product.setImage(request.getParameter("image"));
        product.setDescription(request.getParameter("description"));
        String productId = request.getParameter("productId");

        if (productId == null || productId.isEmpty()) {
            productDAO.addProduct(product);
        } else {
            product.setProductId(Integer.parseInt(productId));
            productDAO.updateProduct(product);
        }

        response.sendRedirect(request.getContextPath() + "/ProductController?action=listProduct");

    }

}

