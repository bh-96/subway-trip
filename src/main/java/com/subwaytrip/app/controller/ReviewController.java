package com.subwaytrip.app.controller;

import com.subwaytrip.app.model.dto.ReviewDTO;
import com.subwaytrip.app.service.FileService;
import com.subwaytrip.app.service.ReviewService;
import com.subwaytrip.app.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping(value = "/review")
public class ReviewController extends LoggerUtils {

    @Value("${file.path}")
    private String filePath;

    private FileService fileService;
    private ReviewService reviewService;

    @Autowired
    public ReviewController(FileService fileService, ReviewService reviewService) {
        this.fileService = fileService;
        this.reviewService = reviewService;
    }

    /**
     * 파일 저장
     */
    @PostMapping(value = "/img")
    public ResponseEntity<?> saveFile(@RequestParam MultipartFile file) {
        return new ResponseEntity<>(fileService.saveFile(file), HttpStatus.OK);
    }

    /**
     * 파일 불러오기
     */
    @GetMapping(value = "/img")
    public ResponseEntity<?> loadFile(@RequestParam String fileName) throws Exception {
        String fileInfo = filePath + File.separator + fileName;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileInfo.getBytes("UTF-8"), "ISO-8859-1") + "\"");
        headers.setContentType(MediaType.IMAGE_PNG);
        Resource resource = fileService.loadFile(fileName);
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    /**
     * 파일 삭제
     */
    @DeleteMapping(value = "/img")
    public ResponseEntity<?> deleteFile(@RequestParam String fileName) {
        return new ResponseEntity<>(fileService.deleteFile(fileName), HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> saveReview(@PathVariable int userId, @Valid @RequestBody ReviewDTO reviewDTO) {
        logger.info("saveReview : userId = " + userId + ", reviewDTO = " + reviewDTO.toString());
        return new ResponseEntity<>(reviewService.saveReview(userId, reviewDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getReview(@PathVariable int id) {
        logger.info("getReview : id = " + id);
        return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<?> updateReview(@PathVariable int userId, @RequestBody ReviewDTO reviewDTO) {
        logger.info("updateReview : userId = " + userId + ", reviewDTO = " + reviewDTO.toString());
        if (reviewDTO.getId() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(reviewService.updateReview(userId, reviewDTO), HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{userId}/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable int userId, @PathVariable int reviewId) {
        logger.info("deleteReview : userId = " + userId + ", reviewId = " + reviewId);
        return new ResponseEntity<>(reviewService.deleteReview(userId, reviewId), HttpStatus.OK);
    }

    @GetMapping(value = "/list/station")
    public ResponseEntity<?> getReviewListByStationName(@RequestParam String stationName, @RequestParam(defaultValue = "") String sortParam) {
        logger.info("getReviewListByStationName : stationName = " + stationName + ", sortParam = " + sortParam);
        return new ResponseEntity<>(reviewService.getReviewListByStationName(stationName, sortParam), HttpStatus.OK);
    }

    @GetMapping(value = "/list/user")
    public ResponseEntity<?> getReviewListByUserId(@RequestParam int userId, @RequestParam(defaultValue = "") String sortParam) {
        logger.info("getReviewListByUserId : userId = " + userId + ", sortParam = " + sortParam);
        return new ResponseEntity<>(reviewService.getReviewListByUserId(userId, sortParam), HttpStatus.OK);
    }

}
