package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import cn.itcast.travel.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        User loginUser = userService.login(user);

        ResultInfo info = new ResultInfo();
        if(loginUser!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", loginUser);
            info.setFlag(true);
        }else{
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        writeValue(response,info);

    }

    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Object user = request.getSession().getAttribute("user");
        writeValue(response,user);

    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        request.getSession().removeAttribute("user");


    }

}
