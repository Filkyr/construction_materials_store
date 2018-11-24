package com.netcracker.cmstore.controller;

import com.netcracker.cmstore.dao.CategoryDAO;
import com.netcracker.cmstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "/CategoryController")
@Controller
public class CategoryController {

    private static final long serialVersionUID = 1L;
    private static String insert_or_edit = "WEB-INF/Category.jsp";
    private static String list_category = "WEB-INF/ListCategory.jsp";

    private final CategoryDAO categoryDAOimpl;

    @Autowired
    public CategoryController(CategoryDAO categoryDao) {
        super();
        this.categoryDAOimpl = categoryDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    @RequestMapping(method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
