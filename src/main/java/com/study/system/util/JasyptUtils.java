package com.study.system.util;

import jxl.write.DateTime;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class JasyptUtils {

    private final static Logger logger = LoggerFactory.getLogger(JasyptUtils.class);

    public static String encrypt(String plainText) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法，这个算法是默认的
        config.setPassword("hyj");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        logger.info(encryptedText);
        return encryptedText;
    }

    public static String de(String pwd) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("hyj");
        standardPBEStringEncryptor.setConfig(config);
        String plainText = standardPBEStringEncryptor.decrypt(pwd); //解密
        logger.info(plainText);
        return plainText;
    }

}
