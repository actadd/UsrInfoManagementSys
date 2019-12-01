package cn.actadd.web.servlet;

import cn.actadd.domain.User;
import cn.actadd.service.UserService;
import cn.actadd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * @description 用户信息列表
 * @date 13:30 2019/11/28
 * @version 1.0
 **/

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、调用 UserService 完成查询
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();

        //2、将 list 存入 request 域
        request.setAttribute("users", users);

        //3、转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
