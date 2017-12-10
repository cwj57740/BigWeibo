package cn.edu.hit.weibo.client;

import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.WeiboService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

        boolean b = weiboService.deleteBlog(bid);
        if (b) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    //获取用户微博列表
    public void getWeibolist(User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入微博的起始序号：");
        int index = scanner.nextInt();
        System.out.println("请输入要显示的条数：");
        int num = scanner.nextInt();
        List<Blog> blogList = weiboService.getUserBlogList(user,index,num);
        printWeiboList(blogList);
    }

    //获取最新微博列表
    public void getAllWeibo(){
        Scanner scanner = new Scanner(System.in);
        String flag = null;
        do {
            System.out.println("请输入微博的起始序号：");
            int index = scanner.nextInt();
            System.out.println("请输入要显示的条数：");
            int num = scanner.nextInt();
            List<Blog> blogList = weiboService.getAllBlogList(index,num);
            printWeiboList(blogList);
            System.out.println("是否进行下一轮查询？y/n");
            flag = scanner.next();
        }while (flag.equals('y') || flag.equals('Y'));

    }

    //打印微博列表
    private void printWeiboList(List<Blog> blogList){
        System.out.println("主键    标题    点击量    创建时间");
        for (int i=0;i<blogList.size();i++){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Blog blog = blogList.get(i);
            Date date = blog.getDatetime();
            String datetime = sdf.format(date);
            System.out.println(blog.getBid()+"    "+blog.getTitle()+"    "+blog.getViews()+"    "+datetime);
        }
    }

    //显示单条微博内容
    public void showSingleWeibo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查看的微博的主键");

        int bid = scanner.nextInt();
        Blog blog = weiboService.readBlog(bid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = blog.getDatetime();
        String datetime = sdf.format(date);
        System.out.println("标题："+blog.getTitle());
        System.out.println("点击量："+blog.getViews());
        System.out.println("创建时间："+datetime);
        System.out.println("正文："+blog.getText());
    }
}
