package cn.actadd.web.servlet;

import cn.actadd.service.UserService;
import cn.actadd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName DelSelectedServlet
 * @Description //TODO
 * @Author Actadd
 * @Date 16:09 2019/11/29
 * @Version 1.0        
 */
@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取所有id
        String[] ids = request.getParameterValues("uid");

        //2、调用Service删除
        UserService service = new UserServiceImpl();
        service.delSelectedUser(ids);

        //3、跳转查询所有Servlet
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
