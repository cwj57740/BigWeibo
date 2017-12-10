package cn.edu.hit.weibo.service;

import cn.edu.hit.weibo.dao.BlogDao;
import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.User;
import cn.edu.hit.weibo.redis.BlogRedis;

import java.util.List;
import java.util.Observable;


/**
 * The type Weibo service.
 */
public class WeiboService extends Observable {
    /**
     * The enum Event.
     */
    public enum event {
        /**
         * Read event.
         */
        Read, /**
         * Add event.
         */
        Add, /**
         * Update event.
         */
        Update, /**
         * Delete event.
         */
        Delete
    }
    private Blog blog;
    private BlogDao blogDao = new BlogDao();
    private BlogRedis blogRedis = new BlogRedis();

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
    public boolean addBlog(Blog blog){
        this.blog = blog;
        boolean b = blogDao.addBlog(blog);
        this.setChanged();
        this.notifyObservers(event.Add);
        return b;
    }

    public Blog readBlog(int bid){
        //判断缓存中是否有该条微博，如果有，则从缓存中取，如果没有，则从数据库中取
        blog = blogRedis.getBlogBybid(bid);
        if(blog == null){
            blog = blogDao.getBlogById(bid);
        }

        this.setChanged();
        this.notifyObservers(event.Read);
        return blog;
    }

    public boolean updateBlog(Blog blog){
        boolean b = blogDao.updateBlog(blog);
        this.setChanged();
        this.notifyObservers(event.Update);
        return b;
    }

    public boolean deleteBlog(int bid){
        blog = blogDao.getBlogById(bid);
        if(blog!=null){
            boolean b = blogDao.deleteBlog(blog);
            this.setChanged();
            this.notifyObservers(event.Delete);
            return b;
        }
        return false;
    }

    public List<Blog> getAllBlogList(int index, int num){
        return blogDao.getBlogList(index,num);
    }

    public List<Blog> getUserBlogList(User user,int index,int num){
        return blogDao.getBlogListByUser(user,index,num);
    }


}
