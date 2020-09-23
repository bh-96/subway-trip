package com.subwaytrip.app.model.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "COMMENT")
public class Comment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @OneToOne
    @JoinColumn(name = "REVIEW_ID")
    private Review reviewId;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "REG_DT")
    private String regDt;

    @Column(name = "MOD_DT")
    private String modDt;

}
