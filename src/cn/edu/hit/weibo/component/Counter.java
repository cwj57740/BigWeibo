package cn.edu.hit.weibo.component;

import cn.edu.hit.weibo.dao.BlogDao;
import cn.edu.hit.weibo.dao.UserDao;
import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.service.WeiboService;

import java.util.Observable;
import java.util.Observer;

import static cn.edu.hit.weibo.service.WeiboService.event.*;

/**
 * The type Counter.
 */
public class Counter implements Observer {
    private WeiboService weiboService;
    private BlogDao blogDao = new BlogDao();
    private UserDao userDao = new UserDao();
    public Counter(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        WeiboService.event e = null;
        if(arg instanceof WeiboService.event){
            e = (WeiboService.event)arg;
        }
        if(e!=null){
            if(o.hasChanged()){
                if(Read.toString().equals(e.toString())){
                    Blog blog = weiboService.getBlog();
                    blogDao.addViews(blog);
                    User user = userDao.getUserById(blog.getUid());
                    userDao.addHits(user);
                }
            }
        }

    }
}
