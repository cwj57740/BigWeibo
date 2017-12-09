package cn.edu.hit.weibo.model;

import java.util.Date;

/**
 * The type Blog.
 */
public class Blog {
    //主键
    private int bid;
    //用户外键
    private int uid;
    //标题
    private String title;
    //正文
    private String text;
    //点击量
    private int views;
    //是否被删除
    private int isDeleted;
    //创建时间
    private Date datetime;


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

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * Sets uid.
     *
     * @param uid the uid
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets views.
     *
     * @return the views
     */
    public int getViews() {
        return views;
    }

    /**
     * Sets views.
     *
     * @param views the views
     */
    public void setViews(int views) {
        this.views = views;
    }

    /**
     * Gets is deleted.
     *
     * @return the is deleted
     */
    public int getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets is deleted.
     *
     * @param isDeleted the is deleted
     */
    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Gets datetime.
     *
     * @return the datetime
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * Sets datetime.
     *
     * @param datetime the datetime
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
