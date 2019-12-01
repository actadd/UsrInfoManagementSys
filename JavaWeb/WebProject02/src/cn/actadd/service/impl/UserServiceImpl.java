package cn.actadd.service.impl;

import cn.actadd.dao.UserDao;
import cn.actadd.dao.impl.UserDaoImpl;
import cn.actadd.domain.PageBean;
import cn.actadd.domain.User;
import cn.actadd.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description //TODO
 * @Author Actadd
 * @Date 22:07 2019/11/25
 * @Version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    /**
     * @description 查询
     * @param
     * @return java.util.List<cn.actadd.domain.User>
     */
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    /**
     * @description 登录
     * @param user
     * @return cn.actadd.domain.User
     */
    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * @description 添加
     * @param user
     * @return void
     */
    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    /**
     * @description 删除
     * @param id
     * @return void
     */
    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    /**
     * @description 根据id查询
     * @param id
     * @return cn.actadd.domain.User
     */
    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /**
     * @description 修改用户信息
     * @param user
     * @return void
     */
    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    /**
     * @description 批量删除用户
     * @param ids
     * @return void
     */
    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    /**
     * @description /分页查询
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return cn.actadd.domain.PageBean<cn.actadd.domain.User>
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if (currentPage <= 0) {
            currentPage = 1;
        }

        //1、创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();

        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3、调用dao查询总记录
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //4、调用dao查询List集合
        //计算索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start, rows, condition);
        pb.setList(list);

        //5、计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        //？没用！
        if (currentPage >= totalPage) {
            currentPage = totalPage;
        }

        return pb;
    }
}