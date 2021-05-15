/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.abstracts;

/**
 *
 * @author oguz_
 */
public interface IGoogleAuthServiceAdapter {

    String login(String email, String password);

    String signUp(String email, String password);
}
