package cn.edu.hit.weibo.dao;

import cn.edu.hit.weibo.model.User;

import java.util.List;

/**
 * The type User dao.
 */
public class UserDao {

    /**
     * The Dao.
     */
    private Dao<User> dao = new Dao<>(User.class);

    /**
     * Add user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addUser(User user){
        String sql = "insert into user (username, password) values (?, ?)";
        return dao.updateT(sql,user.getUsername(),user.getPassword());
    }

    /**
     * Update user boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean updateUser(User user){
        String sql = "update user set username = ?, password = ? where uid = ?";
        return dao.updateT(sql,user.getUsername(),user.getPassword(),user.getUid());
    }

    /**
     * Add hits boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean addHits(User user){
        String sql = "update user set hits = hits + 1 where uid = ?";
        return dao.updateT(sql,user.getUid());
    }

    /**
     * Get user by id user.
     *
     * @param id the id
     * @return the user
     */
    public User getUserById(int id){
        String sql = "select * from user where uid = ?";
        return dao.getTByParams(sql,id);
    }

    /**
     * Get user by username user.
     *
     * @param username the username
     * @return the user
     */
    public User getUserByUsername(String username){
        String sql = "select * from user where username = ?";
        return dao.getTByParams(sql,username);
    }

    /**
     * Get all user list list.
     *
     * @return the list
     */
    public List<User> getAllUserList(){
        String sql = "select * from user";
        return (List<User>)dao.getTListByParams(sql);
    }
}
