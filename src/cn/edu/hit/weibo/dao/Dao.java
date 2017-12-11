package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.util.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Dao<T> {
    Dao(Class <T> entityClass){
        this.entityClass = entityClass;
    }
    Class <T> entityClass;
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
            return qr.query(sql, new BeanHandler<T>(getClazz()),params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<T> getTListByParams(String sql, Object ... params){
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        try {
            return qr.query(sql, new BeanListHandler<>(getClazz()),params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private Class<T> getClazz(){
        return entityClass;
    }

    /**
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.
     * 如public BookManager extends GenricManager<Book>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     */

}
