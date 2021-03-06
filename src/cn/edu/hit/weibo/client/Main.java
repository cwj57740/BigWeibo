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
        blogRedis.updateRedis(blogDao.getHotBlogList());
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("请选择操作：1.登录；2.注册");
            int op = scanner.nextInt();
            if (op == 1){
                user = login.weiboLogin();
                if(user==null){
                    System.out.println("登录失败");
                }
            }else {
                login.weiboRegister();
            }
        }while (user == null);
        int flag = 0;
        do{
            System.out.println("选择要查看的微博：1.最近的微博；2.自己的微博；3.最新的热门微博");
            int whos = scanner.nextInt();
            switch (whos){
                case 1:
                    weibo.getAllWeibo();
                    weibo.showSingleWeibo();
                    break;
                case 2:
                    do {
                        weibo.getWeibolist(user);
                        System.out.println("请选择对自己的微博记录进行的操作：\n 1.添加微博；2.删除微博；3.查看微博；4.修改微博；5.退出");
                        int opweibo = scanner.nextInt();
                        if (opweibo == 1){
                            weibo.addWeibo(user);
                        }else if (opweibo == 2){
                            weibo.deleteWeibo();
                        }else if (opweibo == 3){
                            weibo.showSingleWeibo();
                        }else if (opweibo == 4){
                            weibo.modifySingleWeibo();
                        }
                        else {
                            user = null;
                            break;
                        }
                    }while (true);
                    break;
                case 3:
                    weibo.getHotWeibo();
                    weibo.showSingleWeibo();
                    break;
                default:
                    break;
            }
            System.out.println("是否继续操作？（输入-1退出,其他数字继续）");
            flag = scanner.nextInt();
        }while (flag!=-1);

        System.exit(0);

    }
}
