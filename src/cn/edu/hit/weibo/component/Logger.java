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
    public Logger(Observable o){
        o.addObserver(this);
        if(o instanceof WeiboService) {
            weiboService = (WeiboService) o;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String message = weiboService.getBlog().getBid()+".";
        WeiboService.event e = (WeiboService.event)arg;
        if (Add.toString().equals(e.toString())){
            message += "新增微博";
            Producer.sendMessage(message, "First Queue");
        }
        if (Update.toString().equals(e.toString())){
            message += "更新微博";
            Producer.sendMessage(message, "First Queue");
        }
        if (Delete.toString().equals(e.toString())){
            message += "删除微博";
            Producer.sendMessage(message, "First Queue");
        }


    }
}
