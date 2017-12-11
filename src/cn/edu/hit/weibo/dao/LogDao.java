package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.Log;

import java.sql.Timestamp;

public class LogDao {
    private Dao<Log> dao = new Dao<>(Log.class);
    public boolean addLog(Log log){
        String sql = "insert into log (bid, message, time) values (?, ?, ?)";
        return dao.updateT(sql,log.getBid(),log.getMessage(),new Timestamp(System.currentTimeMillis()));
    }
}
