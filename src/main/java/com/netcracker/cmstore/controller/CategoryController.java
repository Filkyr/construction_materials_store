package com.netcracker.cmstore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.model.Category;

@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "/Category.jsp";
    private static String list_category = "/ListCategory.jsp";
    private CategoryDAO categoryDAO;

    public CategoryController() {
        super();
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            categoryDAO.removeCategory(categoryId);

            forward = list_category;
            try {
                request.setAttribute("categories", categoryDAO.getCategories());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("edit")) {
            forward = insert_or_edit;
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            try {
                Category category = categoryDAO.getCategoryById(categoryId);
                request.setAttribute("category", category);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (action.equalsIgnoreCase("listCategory")) {
            forward = list_category;
            try {
                request.setAttribute("categories", categoryDAO.getCategories());

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
        Category category = new Category();
        category.setTitle(request.getParameter("title"));
        category.setDescription(request.getParameter("description"));
        String categoryId = request.getParameter("categoryId");
        System.out.println("categoryId:");
        System.out.println(categoryId);

        if (categoryId == null || categoryId.isEmpty()) {
            categoryDAO.addCategory(category);
        } else {
            category.setCategoryId(Integer.parseInt(categoryId));
            categoryDAO.updateCategory(category);
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}
