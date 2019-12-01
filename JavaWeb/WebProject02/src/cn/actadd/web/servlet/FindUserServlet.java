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

/**
 * @ClassName FindUserServlet
 * @Description //TODO
 * @Author Actadd
 * @Date 20:07 2019/11/28
 * @Version 1.0
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取id
        String id = request.getParameter("id");

        //2、调用Service查询
        UserService service = new UserServiceImpl();
        User user = service.findUserById(id);

        //3、将user存入request
        request.setAttribute("user", user);

        //4、转发到update.jsp
        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
