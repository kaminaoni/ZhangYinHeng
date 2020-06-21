package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();


    @Override
    public User login(User user) {
        User loginUser = userDao.login(user);
        return loginUser;
    }
}
