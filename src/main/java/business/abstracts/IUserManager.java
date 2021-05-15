/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.abstracts;

import models.User;

/**
 *
 * @author oguz_
 */
public interface IUserManager {

    void signIn(User loginUser);

    void signUp(User user);
}
