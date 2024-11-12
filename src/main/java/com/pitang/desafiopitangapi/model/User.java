package com.pitang.desafiopitangapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pitang.desafiopitangapi.dto.UserDTO;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "BIRTHDAY", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDate createdAt;

    @Column(name = "LAST_LOGIN")
    private LocalDate lastLogin;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Car> cars;

    public void validate(){
        if(firstName == null || firstName.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(lastName == null || lastName.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(email == null || email.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(birthday == null)
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(login == null || login.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(password == null || password.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);
        if(phone == null || phone.isEmpty())
            throw new BusinessException("“Missing fields", HttpStatus.BAD_REQUEST);

        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))
            throw new BusinessException("Invalid fields", HttpStatus.BAD_REQUEST);

        String regex = "^(\\+\\d{1,2}\\s?)?\\(?\\d{2,3}\\)?\\s?-?\\d{4,5}-?\\d{4}$|^\\d{8,9}$";

        if (!phone.matches(regex)) {
            throw new BusinessException("Invalid fields", HttpStatus.BAD_REQUEST);
        }
    }

    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setLogin(user.getLogin());
        userDTO.setPhone(user.getPhone());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setLastLogin(user.getLastLogin());
        return userDTO;
    }
}