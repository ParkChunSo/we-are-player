package com.wap.cores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JWTSecretKeySource {
    private static final String YAML_FILE_NAME = "jwt-%s.properties";

    public static String readSecretKey() {
        try {
            Properties properties = new Properties();
            InputStream input = JWTSecretKeySource.class.getClassLoader().getResourceAsStream(String.format(YAML_FILE_NAME, System.getenv("--spring.profiles.active")));
            if(input == null){
                input = new FileInputStream(new File("src/main/resources/jwt-dev.properties"));
            }
            properties.load(input);
            return properties.getProperty("wap.jwt.cores.secretKey");
        } catch (IOException e) {
//            log.error("e => {}", e.toString());
            throw new RuntimeException(e);
        }
    }
}
