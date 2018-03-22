package me.maxct;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class App {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        Response response = Jsoup.connect("http://bkjws.sdu.edu.cn/b/ajaxLogin").method(Method.POST)
                .data("j_username", "2015xxxxx", "j_password", md5("123456"))
                .ignoreContentType(true)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64…) Gecko/20100101 Firefox/59.0")
                .header("Referer", "http://bkjws.sdu.edu.cn/").execute();
        if (response.statusCode() == 200) {
            String res = response.body();
            if (res.contains("success")) {
                System.out.println("Login Success");
            } else {
                System.out.println(res);
            }
        }
    }

    public static String md5(String str) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] res = digest.digest(str.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            int val = res[i] & 0xff;
            if (val < 16)
                sb.append("0" + Integer.toHexString(val));
            else
                sb.append(Integer.toHexString(val));
        }
        return sb.toString();

    }
}