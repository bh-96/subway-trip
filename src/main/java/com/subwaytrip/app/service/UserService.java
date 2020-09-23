package com.subwaytrip.app.service;

import com.subwaytrip.app.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    // 사용자 정보 저장
    UserDTO saveUser(UserDTO userDTO);

    // 사용자 정보 조회
    UserDTO getUser(int id);

    // 사용자 리스트
    List<UserDTO> getUserList();

    // 아이디, 비밀번호 체크 (로그인)
    UserDTO checkAccountAndPassword(String account, String password);

    // 아이디 중복 체크
    boolean isDuplicatedAccount(String account);

    // 이메일 중복 체크
    boolean isDuplicatedEmail(String email);

    // 인증번호 생성
    String makeCertCode();

    // redis 에 이메일, 인증번호 저장
    void saveCertCodeToRedis(String email, String certCode);

    // 인증번호 체크
    boolean checkCertCode(String email, String certCode);

    // 사용자 정보 수정 (이름, 비밀번호, 이메일)
    UserDTO updateUser(UserDTO userDTO);

    // 사용자 정보 삭제
    boolean deleteUser(int id, String password);

    // 사용자 권한 변경 (ROLE_USER -> ROLE_ADMIN, ROLE_ADMIN -> ROLE_USER)
    boolean changeRole(int adminId, int userId, String adminPassword);

}
