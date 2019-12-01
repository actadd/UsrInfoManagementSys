package cn.actadd.web.servlet;

import cn.actadd.domain.User;
import cn.actadd.service.UserService;
import cn.actadd.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、设置编码
        request.setCharacterEncoding("utf-8");

        //2、获取参数
        Map<String, String[]> map = request.getParameterMap();

        //3、封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4、调用 Service保存
        UserService service = new UserServiceImpl();
        service.addUser(user);

        //5、跳转到 UserListServlet
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
