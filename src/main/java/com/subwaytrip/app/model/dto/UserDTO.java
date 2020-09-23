package com.subwaytrip.app.model.dto;

import com.subwaytrip.app.model.domain.User;
import com.subwaytrip.app.utils.MapperUtils;
import lombok.Data;
import org.modelmapper.TypeToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDTO {

    private int id;

    @NotNull
    @NotEmpty
    private String account;

    @NotNull
    @NotEmpty
    private String password;

    private String name;

    @Email
    private String email;
    private String role;
    private String regDt;

    public static UserDTO of(User user) {
        return MapperUtils.convert(user, UserDTO.class);
    }

    public static List<UserDTO> of(List<User> users) {
        return MapperUtils.convert(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

}
