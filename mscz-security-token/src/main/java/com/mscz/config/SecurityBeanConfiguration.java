package com.mscz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static com.mscz.utils.KeyStoreCoder.KEY_STORE;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 15:54
 */
@Configuration
public class SecurityBeanConfiguration {

    private static final String PRIVATE_KEY ="-----BEGIN RSA PRIVATE KEY-----" +
            "MIICWwIBAAKBgQCM1YBbzMijYIp4/mf1+gdVBXQMJEv5KpuTDh6DiTGJAk1yrsWA" +
            "RfqjpC83/t0xzpmvHa1M7WykUg5E0PmneNddyD/MTjkCDNhqBgr0AnJTZsTnEjMa" +
            "PB0cXeVF1ty1p+ZBuvHKMvhJwqgNmQd7uGpl2Rq1gR1L86YTWSkYceSoNwIDAQAB" +
            "AoGAcYrr+pcGp5l86oGJhWm4IZbM8cENs2vjk9LNTRT9580AbdZ0Cq/gm7ASFZ4X" +
            "7UD47JMLljrQ3UX+lQK6VIf7cTUGZdR1nVArOqJaMKVvCYkwqR6bm5Gc6qx6XWAW" +
            "0/PY2LcWt0cW1Q1CU65M1oM08P+ohQvE4kJI45RcoIl6VwkCQQCO0Za4bYiZWtzE" +
            "UzRka+kHa//h1YjYbQVglPLb5FuOdSm62eGQThfQRpyLU1WD6sATv9yPWxUaRCEL" +
            "Fh+s/YfrAkEA/HFDLl/Nl439/A5Q05HWhMKWZ8tt8k448mNNlefJUK1ApCuWdDWm" +
            "kBTk8ytjRvdFlVKvVVXUV8LeSyWMXpR55QJACe/rXMnCR2lbEw33B0W64RlSpJQH" +
            "AYgUZ7P1cfdhp3fff3DJkRDd90/ydH9H4/Xhh35CCnd78GftJKhVa+P4IQJABYv3" +
            "je1M9yeHjSJDZGKv8/rSkzVFFS3i0nCcI88T/VHROco7ZBJJtqC+5xjs9YI5ZS6L" +
            "67QXFlaRy9TnYKyigQJAHjuzdwDgKBj2orf6k05ri+Ks1nGvp5S4JxzcGCmkQB+l" +
            "6KOJ8lAFma4qxWKaMeNi0ekrzkSrJNEt5yJPbw1Lmg==" +
            "-----END RSA PRIVATE KEY-----";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RsaSigner rsaSigner(KeyPair genKeyPair) throws Exception {

//        return new RsaSigner((RSAPrivateKey)genKeyPair.getPrivate());
        return new RsaSigner((RSAPrivateKey) getPrivateKey("classpath:i21mscz23.key", "i21mscz23", "lx63024477", "lx63024477"));
//        return new RsaSigner(PRIVATE_KEY);
    }

    @Bean
    public RsaVerifier getVerifier(KeyPair genKeyPair)  {
//        return new RsaVerifier((RSAPublicKey)genKeyPair.getPublic());
        return new RsaVerifier((RSAPublicKey) getPublicKey("classpath:i21mscz23.key", "i21mscz23", "lx63024477"));
//        return new RsaVerifier(PRIVATE_KEY);
    }

    /**
     * 生成秘钥对（每次服务器重启会生成新的一份）
     * @return
     */
    @Bean
    public KeyPair genKeyPair(){
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();

        return keyPair;
    }

    private PrivateKey getPrivateKey(String keyStorePath, String alias, String storePass, String keyPass)  {
        PrivateKey key = null;
        try {
            KeyStore ks = getKeyStore(keyStorePath, storePass);
            key = (PrivateKey) ks.getKey(alias, keyPass.toCharArray());
        }catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    private PublicKey getPublicKey(String keyStorePath, String alias, String storePass) {
        PublicKey key = null;
        try{
            KeyStore ks = getKeyStore(keyStorePath, storePass);
            key = ks.getCertificate(alias).getPublicKey();
        }catch (Exception e){
            e.printStackTrace();
        }
        return key;
    }

    private KeyStore getKeyStore(String keyStorePath, String password)
            throws Exception {
        File file = ResourceUtils.getFile(keyStorePath);
        FileInputStream is = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KEY_STORE);
        ks.load(is, password.toCharArray());
        is.close();
        return ks;
    }
}
