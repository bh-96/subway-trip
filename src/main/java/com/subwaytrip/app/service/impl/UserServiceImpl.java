package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.constants.ConstValue;
import com.subwaytrip.app.model.domain.User;
import com.subwaytrip.app.model.dto.UserDTO;
import com.subwaytrip.app.model.repository.UserRepository;
import com.subwaytrip.app.service.UserService;
import com.subwaytrip.app.utils.AES256Utils;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends LoggerUtils implements UserService {

    private UserRepository userRepository;
    private RedisTemplate<String, String> redisTemplate;
    private AES256Utils aes256Utils;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RedisTemplate<String, String> redisTemplate, AES256Utils aes256Utils) {
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.aes256Utils = aes256Utils;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        try {
            userDTO.setRegDt(StaticHelper.getFormatDateTime("yyyyMMdd HHmm", new Date()));
            String password = userDTO.getPassword();
            userDTO.setPassword(aes256Utils.encrypt(password));
            if (StringUtils.isEmpty(userDTO.getRole())) {
                userDTO.setRole(ConstValue.ROLE_USER);
            }
            User user = User.of(userDTO);
            return UserDTO.of(userRepository.save(user));
        } catch (Exception e) {
            logger.error("saveUser", e);
            return null;
        }
    }

    @Override
    public UserDTO getUser(int id) {
        return UserDTO.of(userRepository.findById(id));
    }

    @Override
    public List<UserDTO> getUserList() {
        return UserDTO.of(userRepository.findAll());
    }

    @Override
    public UserDTO checkAccountAndPassword(String account, String password) {
        try {
            String encryptPassword = aes256Utils.encrypt(password);
            return UserDTO.of(userRepository.findByAccountAndPassword(account, encryptPassword));
        } catch (Exception e) {
            logger.error("checkAccountAndPassword", e);
            return null;
        }
    }

    @Override
    public boolean isDuplicatedAccount(String account) {
        return userRepository.findByAccount(account) != null;
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public String makeCertCode() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @Override
    public void saveCertCodeToRedis(String email, String certCode) {
        redisTemplate.opsForValue().set(email, certCode);
        // 인증번호 유효기간 : 3분
        redisTemplate.expire(email, 3, TimeUnit.MINUTES);
    }

    @Override
    public boolean checkCertCode(String email, String certCode) {
        String savedCertCode = redisTemplate.opsForValue().get(email);
        return !StringUtils.isEmpty(savedCertCode) && savedCertCode.equals(certCode);
    }

    // 이름, 비밀번호, 이메일
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        UserDTO user = UserDTO.of(userRepository.findById(userDTO.getId()));

        if (user != null) {
            try {
                if (!StringUtils.isEmpty(userDTO.getName()) && !user.getName().equals(userDTO.getName())) {
                    user.setName(userDTO.getName());
                }

                if (!StringUtils.isEmpty(userDTO.getPassword()) && !userDTO.getPassword().equals(aes256Utils.decrypt(user.getPassword()))) {
                    String password = userDTO.getPassword();
                    user.setPassword(aes256Utils.encrypt(password));
                }

                if (!StringUtils.isEmpty(userDTO.getEmail()) && !user.getEmail().equals(userDTO.getEmail())) {
                    user.setEmail(userDTO.getEmail());
                }

                return UserDTO.of(userRepository.save(User.of(user)));
            } catch (Exception e) {
                logger.error("updateUser", e);
            }
        }

        return null;
    }

    @Override
    public boolean deleteUser(int id, String password) {
        UserDTO user = UserDTO.of(userRepository.findById(id));

        try {
            if (user != null && user.getRole().equals(ConstValue.ROLE_ADMIN) && aes256Utils.decrypt(user.getPassword()).equals(password)) {
                userRepository.deleteById(id);
                return true;
            } else {
                logger.info("Access Denied.");
            }
        } catch (Exception e) {
            logger.error("deleteUser", e);
        }

        return false;
    }

    @Override
    public boolean changeRole(int adminId, int userId, String adminPassword) {
        UserDTO adminUser = UserDTO.of(userRepository.findById(adminId));

        try {
            if (adminUser != null && adminUser.getRole().equals(ConstValue.ROLE_ADMIN) && aes256Utils.decrypt(adminUser.getPassword()).equals(adminPassword)) {
                UserDTO user = UserDTO.of(userRepository.findById(userId));

                if (user != null) {
                    String userRole = user.getRole();
                    user.setRole(userRole.equals(ConstValue.ROLE_USER) ? ConstValue.ROLE_ADMIN : ConstValue.ROLE_USER);
                    return UserDTO.of(userRepository.save(User.of(user))) != null;
                }
            } else {
                logger.info("Access Denied.");
            }
        } catch (Exception e) {
            logger.error("changeRole", e);
        }

        return false;
    }

}
