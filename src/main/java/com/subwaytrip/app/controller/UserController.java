package com.subwaytrip.app.controller;

import com.subwaytrip.app.model.dto.UserDTO;
import com.subwaytrip.app.service.SendMailService;
import com.subwaytrip.app.service.UserService;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController extends LoggerUtils {

    private UserService userService;
    private SendMailService sendMailService;

    @Autowired
    public UserController(UserService userService, SendMailService sendMailService) {
        this.userService = userService;
        this.sendMailService = sendMailService;
    }

    @GetMapping(value = "/duplicated/account")
    public ResponseEntity<?> isDuplicatedAccount(@RequestParam String account) {
        logger.info("isDuplicatedAccount : " + account);
        return new ResponseEntity<>(userService.isDuplicatedAccount(account), HttpStatus.OK);
    }

    @GetMapping(value = "/duplicated/email")
    public ResponseEntity<?> isDuplicatedEmail(@RequestParam String email) {
        logger.info("isDuplicatedEmail : " + email);
        return new ResponseEntity<>(userService.isDuplicatedEmail(email), HttpStatus.OK);
    }

    @PostMapping(value = "/certification")
    public ResponseEntity<?> sendCertCode(@RequestBody String payload) {
        logger.info("sendCertCode : " + payload);
        JSONObject requestJson = StaticHelper.getJsonObject(payload);

        if (requestJson != null) {
            try {
                String email = StaticHelper.getJsonValue(requestJson, "email", "");
                String certCode = userService.makeCertCode();
                // redis 에 이메일, 인증번호 저장 (3분)
                userService.saveCertCodeToRedis(email, certCode);
                // 메일 전송
                sendMailService.send(email, certCode);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("sendCertCode", e);
                return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        logger.info("sendCertCode : error = RequestBody parse error");
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    @GetMapping(value = "/check/certcode")
    public ResponseEntity<?> checkCertCode(@RequestParam String email, @RequestParam String certCode) {
        logger.info("checkCertCode : email = " + email + ", certCode = " + certCode);
        return new ResponseEntity<>(userService.checkCertCode(email, certCode), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        logger.info("register : " + userDTO.toString());
        return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        logger.info("login : " + userDTO.toString());
        return new ResponseEntity<>(userService.checkAccountAndPassword(userDTO.getAccount(), userDTO.getPassword()), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        logger.info("updateUser : " + userDTO.toString());
        if (userDTO.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}/{password}")
    public ResponseEntity<?> deleteUser(@PathVariable int id, @PathVariable String password) {
        logger.info("deleteUser : id = " + id + ", password = " + password);
        return new ResponseEntity<>(userService.deleteUser(id, password), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable int id) {
        logger.info("getUserInfo : id = " + id );
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    /**
     * 관리자
     */
    @GetMapping
    public ResponseEntity<?> getUserList() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @PatchMapping(value = "/role")
    public ResponseEntity<?> changeRole(@RequestParam int adminId, @RequestParam int userId, @RequestParam String adminPassword) {
        logger.info("changeRole : adminId = " + adminId + ", userId = " + userId + ", adminPassword = " + adminPassword);
        return new ResponseEntity<>(userService.changeRole(adminId, userId, adminPassword), HttpStatus.OK);
    }

}
