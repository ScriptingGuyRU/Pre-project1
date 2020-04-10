package Servlets;

import entities.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editUser")
public class EditServlet extends HttpServlet {

    private List<User> users = UserService.getAllUsers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("users",users);
        RequestDispatcher rd = req.getRequestDispatcher("EditUserPage.jsp");
        rd.forward(req,resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");

//        User user = UserService.getUserById(id);

        if (UserService.editUserById(id, name, password)) {
            req.setAttribute("result","Succses");
        } else {
            req.setAttribute("result","Oops, there was an error somewhere");
        }


        //редирект.
        req.setAttribute("users",users);
        RequestDispatcher rd = req.getRequestDispatcher("EditUserPage.jsp");
        rd.forward(req,resp);
    }
}
