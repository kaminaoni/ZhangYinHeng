package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //拿到JdbcTemplate对象，它可以方便的操作数据用的
    JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User login(User user) {
        try {
            User user1 = jt.queryForObject("select * from tab_user where username=? and password=?",
                    new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            if(user1!=null){
                return user1;
            }
        } catch (DataAccessException e) {

        }
        return null;
    }
}
