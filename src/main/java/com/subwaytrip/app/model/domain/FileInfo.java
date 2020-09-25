package com.subwaytrip.app.model.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "FILE_INFO")
public class FileInfo {

    @Id
    @Column(name = "FILE_NO")
    private int fileNo;

    @Column(name = "REG_DT")
    private Date regDt;

}
