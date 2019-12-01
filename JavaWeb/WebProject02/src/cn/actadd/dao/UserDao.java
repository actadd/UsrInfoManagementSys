package cn.actadd.dao;

import cn.actadd.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description 用户操作的DAO
 * @Author Actadd
 * @Date 20:23 2019/11/28
 * @Version 1.0        
 */
public interface UserDao {

    /**
     * @description 查询
     * @param
     * @return java.util.List<cn.actadd.domain.User>
     */
    public List<User> findAll();

    /**
     * @description 登录
     * @param username
     * @param password
     * @return cn.actadd.domain.User
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * @description 添加
     * @param user
     * @return void
     */
    void add(User user);

    /**
     * @description 删除
     * @param id
     * @return void
     */
    void delete(int id);

    /**
     * @description 根据id查询
     * @param id
     * @return cn.actadd.domain.User
     */
    User findById(int id);

    /**
     * @description 修改用户信息
     * @param user
     * @return void
     */
    void updateUser(User user);

    /**
     * @description 查询总记录数
     * @param condition
     * @return int
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * @description 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return java.util.List<cn.actadd.domain.User>
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
