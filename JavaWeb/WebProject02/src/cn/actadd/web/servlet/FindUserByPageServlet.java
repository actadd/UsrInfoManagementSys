package cn.actadd.web.servlet;

import cn.actadd.domain.PageBean;
import cn.actadd.domain.User;
import cn.actadd.service.UserService;
import cn.actadd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName FindUserByPageServlet
 * @Description //TODO
 * @Author Actadd
 * @Date 14:36 2019/11/29
 * @Version 1.0
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        //1.获取参数
        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页显示条数
        String rows = request.getParameter("rows");

        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }

        if(rows == null || "".equals(rows)){
            rows = "5";
        }

        //获取条件查询参数 condition
        Map<String, String[]> condition = request.getParameterMap();

        //2.调用 service 查询
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, condition);

        //3、将 PageBean 存入 request
        request.setAttribute("pb", pb);
        request.setAttribute("condition", condition);

        //4、转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
