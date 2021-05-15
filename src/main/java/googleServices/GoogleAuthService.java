/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleServices;

/**
 *
 * @author oguz_
 */
public class GoogleAuthService {

    public String loginWithGoogle(String email, String password) {
        String result = "";
        if (email.equals("oguz42421@gmail.com") && password.equals("123456")) {
            result = "Google ile Giriş Yapıldı.";
        } else {
            result = "Kullanıcı Bilgisi Bulunamadı.";
        }
        return result;
    }

    public String signUpWithGoogle(String email, String password) {
        return "Google Hesabıyla Kayıt Yapıldı.";
    }

}
