package cn.edu.hit.weibo.component;

import cn.edu.hit.weibo.dao.LogDao;
import cn.edu.hit.weibo.model.Log;
import cn.edu.hit.weibo.service.WeiboService;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.Observable;
import java.util.Observer;

import static cn.edu.hit.weibo.service.WeiboService.event.*;
/**
 * The type Logger.
 */
public class Logger implements Observer {
    private WeiboService weiboService = new WeiboService();
    private LogDao logDao = new LogDao();
    Logger(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.hasChanged()){
            Log log = new Log();
            if (Add.equals(arg)){
                log.setMessage("新增微博");
            }
            if (Update.equals(arg)){
                log.setMessage("更新微博");
            }
            if (Delete.equals(arg)){
                log.setMessage("删除微博");
            }
            log.setBid(weiboService.getBlog().getBid());
            TODO 消息队列;
        }
    }
}
