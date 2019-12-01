package cn.actadd.dao.impl;

import cn.actadd.dao.UserDao;
import cn.actadd.domain.User;
import cn.actadd.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName UserDaoImpl
 * @Description //TODO
 * @Author Actadd
 * @Date 22:12 2019/11/25
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * @description 列出所有用户
     * @param
     * @return java.util.List<cn.actadd.domain.User>
     */
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    /**
     * @description 管理员登录，查询是否存在
     * @param username
     * @param password
     * @return cn.actadd.domain.User
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),
                    username, password);

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description 添加用户
     * @param null
     * @return
     */
    @Override
    public void add(User user) {
        String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, null, null)";
        template.update(sql, user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail());
    }

    /**
     * @description 删除用户
     * @param null
     * @return
     */
    @Override
    public void delete(int id) {
        String sql = "delete from user where id = ?";
        template.update(sql, id);
    }

    /**
     * @description 根据id查询用户
     * @param id
     * @return cn.actadd.domain.User
     */
    @Override
    public User findById(int id) {
        String sql = "select * from user where id = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
    }

    /**
     * @description 修改用户信息
     * @param user
     * @return void
     */
    @Override
    public void updateUser(User user) {
        String sql = "update user set name = ?, gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * @description 查询总记录数
     * @param condition
     * @return int
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {

        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        //2.遍历map
        Set<String> keySet = condition.keySet();

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and " + key + " like ? ");
                //？条件的值
                params.add("%" + value + "%");
            }
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());

    }

    /**
     * @description 查询页码
     * @param start
     * @param rows
     * @param condition
     * @return java.util.List<cn.actadd.domain.User>
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {

        //1.定义模板初始化sql
        String sql = "select * from user  where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        //2.遍历map
        Set<String> keySet = condition.keySet();

        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+ key + " like ? ");
                //？条件的值
                params.add("%" + value + "%");
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");

        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());

    }
}
