package cn.edu.hit.weibo.client;

import cn.edu.hit.weibo.dao.BlogDao;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.redis.BlogRedis;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        User user = null;
        Login login = new Login();
        Weibo weibo = new Weibo();
        BlogDao blogDao = new BlogDao();
        BlogRedis blogRedis = new BlogRedis();
        blogRedis.saveBlog(blogDao.getHotBlogList());
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

        System.out.println("选择要查看的微博：1.别人的微博；2.自己的微博");
        int whos = scanner.nextInt();
        switch (whos){
            case 1:
                weibo.getAllWeibo();
                weibo.showSingleWeibo();
                System.out.println("==========");
                break;
            case 2:
                do {
                    weibo.getWeibolist(user);
                    System.out.println("请选择对自己的微博记录进行的操作：1.添加微博；2.删除微博；3.查看微博；4.退出");
                    int opweibo = scanner.nextInt();
                    if (opweibo == 1){
                        weibo.addWeibo(user);
                        weibo.getWeibolist(user);
                    }else if (opweibo == 2){
                        weibo.deleteWeibo();
                        weibo.getWeibolist(user);
                    }else if (opweibo == 3){
                        weibo.showSingleWeibo();
                        continue;
                    } else {
                        user = null;
                        System.exit(0);
                        break;
                    }
                }while (true);
                break;
            default:
                break;
        }


    }
}
