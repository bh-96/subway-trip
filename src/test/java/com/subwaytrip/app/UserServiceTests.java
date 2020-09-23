package com.subwaytrip.app;

import com.subwaytrip.app.service.UserService;
import com.subwaytrip.app.utils.AES256Utils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private AES256Utils aes256Utils;

    @Test
    @Ignore
    public void encryptTest() throws Exception {
        String password = "test";
        String encryptPw = aes256Utils.encrypt(password);
        System.out.println(encryptPw);
        System.out.println(aes256Utils.decrypt(encryptPw));
    }

}
