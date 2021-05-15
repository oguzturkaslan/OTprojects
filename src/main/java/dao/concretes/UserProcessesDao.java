/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.concretes;

import dao.abstracts.IUserProcessesDao;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author oguz_
 */
public class UserProcessesDao implements IUserProcessesDao {

    List<User> userLists;

    public UserProcessesDao() {
        this.userLists = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        userLists.add(user);
    }

    @Override
    public boolean findEmail(String eMailStr) {
        boolean result = false;
        for (User user : userLists) {
            if (user.getEmail().equals(eMailStr)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean getEmail(User user) {
        boolean result = false;
        for (User userList : userLists) {
            if (userList.getEmail().equals(user.getEmail()) && userList.getPassword().equals(user.getPassword())) {
                result = true;
            }
        }
        return result;
    }

}
