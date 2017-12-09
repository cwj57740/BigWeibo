package cn.edu.hit.weibo.model;

/**
 * The type User.
 */
public class User {
    //用户主键
    private int uid;
    //用户名
    private String username;
    //密码
    private String password;
    //点击量
    private int hits;

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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets hits.
     *
     * @return the hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * Sets hits.
     *
     * @param hits the hits
     */
    public void setHits(int hits) {
        this.hits = hits;
    }
}
