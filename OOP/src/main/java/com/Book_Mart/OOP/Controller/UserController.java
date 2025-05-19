package com.Book_Mart.OOP.Controller;

import com.Book_Mart.OOP.DTO.UserDTO;
import com.Book_Mart.OOP.JWT.JwtUtil;
import com.Book_Mart.OOP.Model.User;
import com.Book_Mart.OOP.Services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private JwtUtil jwtService;


    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/user/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        UserDTO user = userServices.findByEml(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userServices.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO loginDTO) {
        try {
            UserDTO user = userServices.authenticate(loginDTO.getEmail(), loginDTO.getPassword());

            String token = JwtUtil.generateToken(user.getEmail(), user.getRole());

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("role", user.getRole());
            response.put("email", user.getEmail());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/display")
    public List<UserDTO> display() {
        return userServices.displayAll();
    }

    @GetMapping("/searchByEmailOrID/{value}")
    public List<UserDTO> search(@PathVariable String value) {
        return userServices.searchByEmailNId(value);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
        try {
            userServices.updateUser(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        try {
            userServices.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String email = jwtService.extractUsername(token); // Assuming you use email in token
        UserDTO userDTO = userServices.findByEml(email);
        User user = modelMapper.map(userDTO , User.class);
        return ResponseEntity.ok(user);
    }
}