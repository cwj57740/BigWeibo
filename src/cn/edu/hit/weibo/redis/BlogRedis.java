package cn.edu.hit.weibo.redis;

import cn.edu.hit.weibo.dao.BlogDao;
import cn.edu.hit.weibo.model.Blog;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogRedis {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池

    public BlogRedis(){
        initialPool();
        jedis = jedisPool.getResource();
    }

    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100); // 设置最大连接数
        config.setMaxIdle(5);// 设置最大空闲连接
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }

    //将微博记录缓存在redis中
    public void saveBlog(List<Blog> blogList){
        Map<String, String> blogmap = new HashMap<String, String>();

        try{
            jedis=jedisPool.getResource(); // 获取连接

            for (Blog blog:blogList){
                long dt = blog.getDatetime().getTime();

                blogmap.put("uid",Integer.toString(blog.getUid()));
                blogmap.put("title",blog.getTitle());
                blogmap.put("text",blog.getText());
                blogmap.put("views",Integer.toString(blog.getViews()));
                blogmap.put("isDeleted",Integer.toString(blog.getIsDeleted()));
                blogmap.put("datetime",Long.toString(dt));
                jedis.hmset(Integer.toString(blog.getBid()), blogmap);// 设置值
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }

    //判断缓存中是否有该微博
    public boolean isExist(int bid){
        try{
            jedis=jedisPool.getResource(); // 获取连接
            List<String> rsmap = jedis.hmget(Integer.toString(bid), "uid","title","text","views","isDeleted","datetime");
            if(rsmap != null){
                return true;
            }else {
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
        return false;
    }

    //从缓存中获取微博信息
    public Blog getBlogBybid(int bid){
        try{
            jedis=jedisPool.getResource(); // 获取连接
            List<String> rsmap = jedis.hmget(Integer.toString(bid), "uid","title","text","views","isDeleted","datetime");
            if(rsmap.isEmpty()){
                Blog blog = new Blog();
                Long dt = Long.parseLong(rsmap.get(5));
                Date time = new Date(dt);
                blog.setBid(bid);
                blog.setUid(Integer.parseInt(rsmap.get(0)));
                blog.setTitle(rsmap.get(1));
                blog.setText(rsmap.get(2));
                blog.setViews(Integer.parseInt(rsmap.get(3)));
                blog.setIsDeleted(Integer.parseInt(rsmap.get(4)));
                blog.setDatetime(time);
                return blog;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
        return null;
    }

    //更新缓存
    public void updateRedis(List<Blog> blogList){
        Map<String, String> blogmap = new HashMap<String, String>();
        try{
            jedis=jedisPool.getResource(); // 获取连接
            for (Blog blog:blogList){

                jedis.del(Integer.toString(blog.getBid()));//删除原有键
                //重新设置键值
                long dt = blog.getDatetime().getTime();

                blogmap.put("uid",Integer.toString(blog.getUid()));
                blogmap.put("title",blog.getTitle());
                blogmap.put("text",blog.getText());
                blogmap.put("views",Integer.toString(blog.getViews()));
                blogmap.put("isDeleted",Integer.toString(blog.getIsDeleted()));
                blogmap.put("datetime",Long.toString(dt));
                jedis.hmset(Integer.toString(blog.getBid()), blogmap);// 设置值
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }

    //更新微博单条记录
    public void updateSingle(Blog blog){
        Map<String, String> blogmap = new HashMap<String, String>();
        try{
            jedis=jedisPool.getResource(); // 获取连接

            jedis.del(Integer.toString(blog.getBid()));//删除原有键
            //重新设置键值
            long dt = blog.getDatetime().getTime();

            blogmap.put("uid",Integer.toString(blog.getUid()));
            blogmap.put("title",blog.getTitle());
            blogmap.put("text",blog.getText());
            blogmap.put("views",Integer.toString(blog.getViews()));
            blogmap.put("isDeleted",Integer.toString(blog.getIsDeleted()));
            blogmap.put("datetime",Long.toString(dt));
            jedis.hmset(Integer.toString(blog.getBid()), blogmap);// 设置值

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }

    //删除微博单条记录
    public void deleteSingle(Blog blog){
        Map<String, String> blogmap = new HashMap<String, String>();
        try{
            jedis=jedisPool.getResource(); // 获取连接

            jedis.del(Integer.toString(blog.getBid()));//删除原有键

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }
    }

}
