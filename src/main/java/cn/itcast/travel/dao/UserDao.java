package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    User login(User user);
}
