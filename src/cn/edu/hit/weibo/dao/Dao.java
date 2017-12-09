package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class Dao<T> {
    public boolean updateT(String sql,Object ... params){
        try {
            QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
            qr.update(sql,params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public T getTByParams(String sql,Object ... params){
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            T t = qr.query(sql, new BeanHandler<T>(getClazz()),params);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<T> getTListByParams(String sql, Object ... params){
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            List<T> list = qr.query(sql, new BeanListHandler<>(getClazz()),params);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Class<T> getClazz(){
        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return entityClass;
    }
}
