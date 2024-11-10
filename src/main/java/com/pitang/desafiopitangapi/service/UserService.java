package com.pitang.desafiopitangapi.service;

import com.pitang.desafiopitangapi.dto.UserDTO;
import com.pitang.desafiopitangapi.dto.ResponseDTO;
import com.pitang.desafiopitangapi.exceptions.BusinessException;
import com.pitang.desafiopitangapi.infra.security.TokenService;
import com.pitang.desafiopitangapi.model.Car;
import com.pitang.desafiopitangapi.model.User;
import com.pitang.desafiopitangapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CarService carService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService, CarService carService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.carService = carService;
    }

    public ResponseDTO register(UserDTO userDTO) throws BusinessException {
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new BusinessException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BusinessException("Email already exists", HttpStatus.BAD_REQUEST);
        }
        User newUser = UserDTO.toEntity(userDTO);
        newUser.validate();
        if(newUser.getCars() != null)
            carService.validateCarList(newUser.getCars());

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        this.userRepository.save(newUser);

        if(newUser.getCars() != null){
            for(Car car: newUser.getCars()){
                car.setUser(newUser);
                carService.register(car);
            }
        }
        String token = tokenService.generateToken(newUser);
        return new ResponseDTO(newUser.getFirstName(), token);
    }

    public void updateLastLogin(User user){
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for(User user: users){
            userDTOs.add(User.toDTO(user));
        }
        return userDTOs;
    }

    public UserDTO findByMe(HttpServletRequest request){
        var token = tokenService.recoverToken(request);
        var login = tokenService.verifyToken(token);
        User user = userRepository.findByLogin(login).orElseThrow(() -> new BadCredentialsException("Invalid login"));
        return User.toDTO(user);
    }

    public UserDTO update(String id, UserDTO userDTO){
        User user = findById(id);
        User newuser = UserDTO.toEntity(userDTO);
        if(newuser.getId() == null)
            newuser.setId(id);
        if(newuser.getPassword() == null)
            newuser.setPassword(user.getPassword());
        if (user.getCars() != null)
            newuser.setCars(user.getCars());
        newuser.setCreatedAt(user.getCreatedAt());
        newuser.setLastLogin(user.getLastLogin());

        newuser.validate();
        userRepository.save(newuser);
        return User.toDTO(user);
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("Invalid id"));
    }

}
