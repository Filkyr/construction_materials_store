package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.dao.factory.DaoFactory;
import com.netcracker.cmstore.model.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends ExceptionHandlingHttpServlet {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "WEB-INF/Category.jsp";
    private static String list_category = "WEB-INF/ListCategory.jsp";
    private CategoryDAO categoryDAOimpl;

    public CategoryController() {
        super();
        categoryDAOimpl = DaoFactory.getInstance().getCategoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        if ("delete".equalsIgnoreCase(action)) {
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            categoryDAOimpl.removeCategory(categoryId);

            forward = list_category;

            request.setAttribute("categories", categoryDAOimpl.getCategories());

        } else if ("edit".equalsIgnoreCase(action)) {
            forward = insert_or_edit;
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            Category category = categoryDAOimpl.getCategoryById(categoryId);

            request.setAttribute("category", category);

        } else if ("listCategory".equalsIgnoreCase(action)) {
            forward = list_category;

            request.setAttribute("categories", categoryDAOimpl.getCategories());
        } else if ("insert".equalsIgnoreCase(action)) {

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

        if (categoryId == null || categoryId.isEmpty()) {
            categoryDAOimpl.addCategory(category);
        } else {
            category.setCategoryId(Integer.parseInt(categoryId));
            categoryDAOimpl.updateCategory(category);
        }

        response.sendRedirect(request.getContextPath() + "/CategoryController?action=listCategory");
    }

}
