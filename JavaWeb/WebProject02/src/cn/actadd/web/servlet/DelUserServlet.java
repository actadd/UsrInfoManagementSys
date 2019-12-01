package cn.actadd.web.servlet;

        import cn.actadd.service.UserService;
        import cn.actadd.service.impl.UserServiceImpl;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@WebServlet("/delUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、获取id
        String id = request.getParameter("id");

        //2、调用Service删除
        UserService service = new UserServiceImpl();
        service.deleteUser(id);

        //3、跳转到查询所有Service
        response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
