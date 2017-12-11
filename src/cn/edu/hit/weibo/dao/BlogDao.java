package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Blog;
import cn.edu.hit.weibo.model.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    private Dao<Blog> dao = new Dao<>(Blog.class);
    public int addBlog(Blog blog){
        String sql = "insert into blog (uid, title, text, datetime) values (?, ?, ?, ?)";
        return dao.addT(sql,blog.getUid(),blog.getTitle(),blog.getText(),new Timestamp(System.currentTimeMillis()));
    }
    public boolean updateBlog(Blog blog){
        String sql = "update blog set title = ?, text = ? where bid = ?";
        return dao.updateT(sql,blog.getTitle(),blog.getText(),blog.getBid());
    }
    public boolean deleteBlog(Blog blog){
        String sql = "update blog set isDeleted = '1' where bid = ?";
        return dao.updateT(sql,blog.getBid());
    }
    public boolean addViews(Blog blog){
        String sql = "update blog set views = views + 1 where bid = ?";
        return dao.updateT(sql,blog.getBid());
    }
    public Blog getBlogById(int bid){
        String sql = "select * from blog where bid = ?";
        return dao.getTByParams(sql,bid);
    }
    public List<Blog> getBlogList(int index, int num){
        String sql = "select * from blog where isDeleted = 0 order by datetime desc limit ?, ?";
        return dao.getTListByParams(sql,index,num);
    }

    public List<Blog> getHotBlogList(){
        List<Blog> blogList = new ArrayList<>();
        String userSql = "select * from user order by hits limit 100";
        List<User> userList = new Dao<User>(User.class).getTListByParams(userSql);
        for(User user:userList){
            String sql = "select * from blog where isDeleted = 0 and uid = ? order by datetime limit 100";
            List<Blog> blogListByUser= dao.getTListByParams(sql, user.getUid());
            blogList.addAll(blogListByUser);
        }
        return blogList;
    }
    public List<Blog> getBlogListByUser(User user, int index , int num){
        String sql = "select * from blog where isDeleted = 0 and uid = ? order by datetime desc limit ?, ?";
        return dao.getTListByParams(sql,user.getUid(),index,num);
    }

}
