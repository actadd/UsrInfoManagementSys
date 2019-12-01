package cn.actadd.service;

import cn.actadd.domain.PageBean;
import cn.actadd.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description //TODO
 * @Author Actadd
 * @Date 20:12 2019/11/28
 * @Version 1.0
 */
public interface UserService {

    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 添加User
     * @param user
     */
    void addUser(User user);

    /**
     * 根据id删除User
     * @param id
     */
    void deleteUser(String id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * @description 修改用户信息
     * @param user
     * @return void
     */
    void updateUser(User user);

    /**
     * @description 批量删除用户
     * @param ids
     * @return void
     */
    void delSelectedUser(String[] ids);

    /**
     * @description 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return cn.actadd.domain.PageBean<cn.actadd.domain.User>
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

}
