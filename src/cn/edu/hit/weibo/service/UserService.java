package cn.edu.hit.weibo.service;

import cn.edu.hit.weibo.dao.UserDao;
import cn.edu.hit.weibo.model.User;

public class UserService {
    private UserDao userDao = new UserDao();
    public User login(String username, String password){
        User user = userDao.getUserByUsername(username);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public boolean register(String username,String password){
        User user = userDao.getUserByUsername(username);
        if(user==null){
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            boolean b = userDao.addUser(user);
            return b;
        }
        return false;
    }
}
