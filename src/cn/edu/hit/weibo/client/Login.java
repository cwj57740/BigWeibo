package cn.edu.hit.weibo.client;

import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.UserService;

import java.util.Scanner;

public class Login {
    private UserService userService = new UserService();

    //微博登录
    public User weiboLogin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        User user = userService.login(username,password);
       return user;

    }

    //用户注册
    public boolean weiboRegister(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        boolean flag = userService.register(username,password);
        if (flag){
            System.out.println("注册成功");
        }else {
            System.out.println("用户名已被注册");
        }
        return flag;
    }

}
