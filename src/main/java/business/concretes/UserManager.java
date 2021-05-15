/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.concretes;

import business.abstracts.IUserManager;
import core.abstracts.IGoogleAuthServiceAdapter;
import dao.abstracts.IUserProcessesDao;
import dao.concretes.UserProcessesDao;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.User;

/**
 *
 * @author oguz_
 */
public class UserManager implements IUserManager {

    private IUserProcessesDao userProcessesDao;
    private boolean useWithGoogleServices;
    private IGoogleAuthServiceAdapter iGoogleAuthServiceAdapter;

    public UserManager(IUserProcessesDao userProcessesDao, boolean useWithGoogleServices, IGoogleAuthServiceAdapter iGoogleAuthServiceAdapter) {
        this.userProcessesDao = userProcessesDao;
        this.useWithGoogleServices = useWithGoogleServices;
        this.iGoogleAuthServiceAdapter = iGoogleAuthServiceAdapter;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * Oturum Aç
     *
     * @param loginUser
     */
    @Override
    public void signIn(User loginUser) {
        boolean checkEmail = userProcessesDao.findEmail(loginUser.getEmail());
        if (checkEmail) {
            if (!useWithGoogleServices) {
                boolean getEmail = userProcessesDao.getEmail(loginUser);
                if (getEmail) {
                    System.out.println("Giriş Yapıldı");
                } else {
                    System.out.println("E-Mail veya Şifre Yanlış !! Lütfen Bilgilerinizi KOntrol Edip Tekrar Deneyiniz. ");
                }
            } else {
                System.out.println(iGoogleAuthServiceAdapter.login(loginUser.getEmail(), loginUser.getPassword()));
            }
        }
    }

    /**
     * Kaydol
     *
     * @param user
     */
    @Override
    public void signUp(User user) {
        if (!useWithGoogleServices) {
            boolean checkEmail = userProcessesDao.findEmail(user.getEmail());
            if (user.getName().isEmpty() || user.getSurname().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
                System.out.println(" Eksik bilgi girdiniz. Lütfen bütün bilgileri giriniz !!");
            } else if (user.getPassword().length() < 6) {
                System.out.println("Şifre uzunluğu 6'dan küçük olamaz !!");
            } else if (checkEmail) {
                System.out.println(" Girilen e-mail daha önce kaydedilmiş !!");
            } else if (!validate(user.getEmail())) {
                System.out.println(" Lütfen e-mail formatına uygun şekilde giriniz. (Örn :abc@abc.com)");
            } else if (user.getName().length() < 2 || user.getSurname().length() < 2) {
                System.out.println("Adınız ve soyadiniz en az 2 karakterli olmalıdır !!");
            } else {
                System.out.println("Kayıt İşlemi başarılı !!");
                if (sendVerificationMail(user)) {
                    userProcessesDao.add(user);
                }
            }
        } else {
            System.out.println(iGoogleAuthServiceAdapter.signUp(user.getEmail(), user.getPassword()));
            userProcessesDao.add(user);
        }
    }

    /**
     * Doğrulama E-Maili GÖnderen Fonksiyon(Simulasyon)
     *
     * @param user
     * @return
     */
    public boolean sendVerificationMail(User user) {
        String verificationResult = "";
        boolean verificationRes = false;
        System.out.println("Aktivasyon Maili Gönderildi. ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Aktivasyon İşlemi için Y ye basınız.");
        verificationResult = scanner.next();
        if (!verificationResult.equals("")) {
            if (verificationResult.equals("Y")) {
                System.out.println("Aktivasyon Başarılı !!");
                verificationRes = true;
            } else {
                System.out.println("AKTİVASYON BAŞARISIZ !!");
            }
        }
        return verificationRes;
    }

}
