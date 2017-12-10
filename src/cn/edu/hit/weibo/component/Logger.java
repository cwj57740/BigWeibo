package cn.edu.hit.weibo.component;

import cn.edu.hit.weibo.activemq.Producer;
import cn.edu.hit.weibo.service.WeiboService;

import java.util.Observable;
import java.util.Observer;

import static cn.edu.hit.weibo.service.WeiboService.event.*;
/**
 * The type Logger.
 */
public class Logger implements Observer {
    private WeiboService weiboService = new WeiboService();
    Logger(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.hasChanged()){
            String message = weiboService.getBlog().getBid()+".";
            if (Add.equals(arg)){
                message += "新增微博";
            }
            if (Update.equals(arg)){
                message += "更新微博";
            }
            if (Delete.equals(arg)){
                message += "删除微博";
            }
            Producer.sendMessage(message, "First Queue");
        }
    }
}
