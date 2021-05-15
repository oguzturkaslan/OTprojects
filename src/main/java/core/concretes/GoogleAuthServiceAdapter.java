/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.concretes;

import core.abstracts.IGoogleAuthServiceAdapter;
import googleServices.GoogleAuthService;

/**
 *
 * @author oguz_
 */
public class GoogleAuthServiceAdapter implements IGoogleAuthServiceAdapter {

    GoogleAuthService googleAuthService = new GoogleAuthService();

    @Override
    public String login(String email, String password) {
        return googleAuthService.loginWithGoogle(email, password);
    }

    @Override
    public String signUp(String email, String password) {
        return googleAuthService.signUpWithGoogle(email, password);
    }

}
