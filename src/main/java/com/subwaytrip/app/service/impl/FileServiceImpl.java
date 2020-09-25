package com.subwaytrip.app.service.impl;

import com.subwaytrip.app.model.repository.FileInfoRepository;
import com.subwaytrip.app.service.FileService;
import com.subwaytrip.app.utils.LoggerUtils;
import com.subwaytrip.app.utils.StaticHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class FileServiceImpl extends LoggerUtils implements FileService {

    @Value("${file.path}")
    private String filePath;

    private FileInfoRepository fileInfoRepository;
    private Path rootLocation;

    @Autowired
    public FileServiceImpl(String uploadPath, FileInfoRepository fileInfoRepository) {
        this.fileInfoRepository = fileInfoRepository;
        this.rootLocation = Paths.get(uploadPath);
    }

    @Override
    public String saveFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                logger.error("saveFile : file = " + file.getOriginalFilename() + ", error = file is empty.");
                return "";
            }

            if (!(file.getContentType().equals(MediaType.IMAGE_JPEG_VALUE) || file.getContentType().equals(MediaType.IMAGE_PNG_VALUE))) {
                logger.error("saveFile : file = " + file.getOriginalFilename() + ", error = invalid file type. (" + file.getContentType() + ")");
                return "";
            }

            String date = StaticHelper.getFormatDateTime("yyyyMMdd", new Date());
            String path = filePath + File.separator + date;
            String fileName = getFileName();
            String fileInfo = path + File.separator + fileName;

            if (fileInfo.equals("")) {
                logger.error("saveFile : file = " + file.getOriginalFilename() + ", error = invalid file type. (" + file.getContentType() + ")");
                return "";
            }

            // 폴더 없으면 생성
            if (!isExistDir(path)) {
                new File(path).mkdirs();
            }

            File target = new File(fileInfo);
            FileCopyUtils.copy(file.getBytes(), target);

            return date + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("saveFile : file = " + file.getOriginalFilename() + ", error = " + e.getMessage());
            return "";
        }
    }

    private String getFileName() {
        return fileInfoRepository.getFileNo() + ".png";
    }

    private boolean isExistDir(String dir) {
        return new File(dir).exists();
    }

    @Override
    public Resource loadFile(String fileName) {
        try {
            Path file = loadPath(fileName);
            logger.info("loadFile : " + file.toUri());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Could not read file: " + filePath);
        }
        return null;
    }

    /** file 경로 **/
    public Path loadPath(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public boolean deleteFile(String fileName) {
        // 파일 삭제
        String fileInfo = filePath + File.separator + fileName;
        File file = new File(fileInfo);
        if (file.exists()) {
            file.delete();

            // 디렉토리 삭제
            if (fileName.contains("/") || fileName.contains(File.separator)) {
                String[] strArr = fileName.split("/");
                String dirName = strArr[0];
                File dir = new File(dirName);
                if (dir.exists() && dir.isDirectory()) {
                    File[] files = dir.listFiles();
                    if (ObjectUtils.isEmpty(files) || files.length == 0) {
                        dir.delete();
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
