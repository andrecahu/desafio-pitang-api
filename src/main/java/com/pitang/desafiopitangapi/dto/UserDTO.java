package com.pitang.desafiopitangapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;
    private List<Car> cars;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    private PasswordEncoder passwordEncoder;

    public static User toEntity(UserDTO dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setPhone(dto.getPhone());
        user.setCreatedAt(LocalDateTime.now());
        user.setCars(dto.getCars());
        return user;
    }
}
