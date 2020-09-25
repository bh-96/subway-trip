package com.subwaytrip.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 파일 저장 -> 파일명 리턴
     */
    String saveFile(MultipartFile file);

    /**
     * 파일 불러오기
     */
    Resource loadFile(String fileName);

    /**
     * 파일 삭제
     */
    boolean deleteFile(String fileName);
}
