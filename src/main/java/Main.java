
import business.abstracts.IUserManager;
import business.concretes.UserManager;
import core.abstracts.IGoogleAuthServiceAdapter;
import core.concretes.GoogleAuthServiceAdapter;
import dao.concretes.UserProcessesDao;
import models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author oguz_
 */
public class Main {

    public static void main(String[] args) {
        boolean isSelectedGoogleServices = false;
        IUserManager iUserManager = new UserManager(new UserProcessesDao(), isSelectedGoogleServices, new GoogleAuthServiceAdapter());//Google Servisleri ile giriş
        User newUser = new User(1, "Oğuz", "Türkaslan", "oguz42421@gmail.com", "123456");
        iUserManager.signUp(newUser);
        iUserManager.signIn(newUser);
    }
}
