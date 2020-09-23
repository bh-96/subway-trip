package com.subwaytrip.app.model.domain;

import com.subwaytrip.app.model.dto.UserDTO;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "REG_DT")
    private String regDt;

    public static User of(UserDTO userDTO) {
        return MapperUtils.convert(userDTO, User.class);
    }

}
