package cn.edu.hit.weibo.client;

import cn.edu.hit.weibo.model.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        User user = null;
        Login login = new Login();
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("请选择操作：1.登录；2.注册");
            int op = scanner.nextInt();
            if (op == 1){
                user = login.weiboLogin();

            }else {
                login.weiboRegister();
            }
        }while (user == null);



    }
}
