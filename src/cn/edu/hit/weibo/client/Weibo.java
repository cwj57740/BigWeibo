package cn.edu.hit.weibo.client;

import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.WeiboService;

import java.util.Scanner;

public class Weibo {
    private WeiboService weiboService = new WeiboService();

    //添加微博
    public void addWeibo(User user){
        Blog blog = new Blog();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入微博标题：");
        String title = scanner.next();
        System.out.println("请输入微博正文：");
        String text = scanner.next();
        blog.setUid(user.getUid());
        blog.setTitle(title);
        blog.setText(text);
        boolean b = weiboService.addBlog(blog);
        if (b){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }

    //删除微博
    public void deleteWeibo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的微博的序号：");
        int bid = scanner.nextInt();

        weiboService.deleteBlog()
    }
}
