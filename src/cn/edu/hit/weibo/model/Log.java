package cn.edu.hit.weibo.model;

import java.util.Date;

/**
 * The type Log.
 */
public class Log {
    //主键
    private int lid;
    //微博外键
    private int bid;
    //日志记录
    private String message;
    //记录时间
    private Date time;

    /**
     * Gets lid.
     *
     * @return the lid
     */
    public int getLid() {
        return lid;
    }

    /**
     * Sets lid.
     *
     * @param lid the lid
     */
    public void setLid(int lid) {
        this.lid = lid;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Gets bid.
     *
     * @return the bid
     */
    public int getBid() {
        return bid;
    }

    /**
     * Sets bid.
     *
     * @param bid the bid
     */
    public void setBid(int bid) {
        this.bid = bid;
    }
}
