package com.subwaytrip.app.model.repository;

import com.subwaytrip.app.model.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {

    @Transactional
    @Query(value = "SELECT SEQ_FILE_NO_NEXTVAL()",nativeQuery = true)
    int getFileNo();

}
