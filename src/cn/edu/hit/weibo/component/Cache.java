package cn.edu.hit.weibo.component;

import cn.edu.hit.weibo.dao.BlogDao;
import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.redis.BlogRedis;
import cn.edu.hit.weibo.service.WeiboService;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static cn.edu.hit.weibo.service.WeiboService.event.Delete;
import static cn.edu.hit.weibo.service.WeiboService.event.Update;

public class Cache implements Observer{
    private WeiboService weiboService = new WeiboService();
    private BlogRedis blogRedis = new BlogRedis();
    private BlogDao blogDao = new BlogDao();
    private List<Blog> blogList;
    Cache(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        if(o.hasChanged()){
            Blog blog = weiboService.getBlog();
            if(Update.equals(arg)){
                blogRedis.updateSingle(blog.getBid());
            }
            if(Delete.equals(arg)){
                blogRedis.deleteSingle(blog.getBid());
            }
        }

    }
}
