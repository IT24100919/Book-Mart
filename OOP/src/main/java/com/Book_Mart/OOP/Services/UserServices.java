package com.Book_Mart.OOP.Services;

import com.Book_Mart.OOP.DTO.UserDTO;
import com.Book_Mart.OOP.Model.User;
import com.Book_Mart.OOP.Repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServices {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO createUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public List<UserDTO> displayAll(){
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public List<UserDTO> searchByEmailNId(String value){
        List<User> searchedUser = userRepo.search(value);
        return searchedUser.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public void updateUser(UserDTO userDTO){
        User user =modelMapper.map(userDTO, User.class);
        Optional<User> cUser = userRepo.findById(user.getUserId());
        if(cUser.isPresent()){
            User uUser =cUser.get();
            uUser.setUsername(user.getUsername());
            uUser.setUserId(user.getUserId());
            uUser.setEmail(user.getEmail());
            uUser.setPassword(user.getPassword());
            userRepo.save(uUser);
        }else {
            throw new RuntimeException("Can't Find a User by This ID : "+user.getUserId());
        }
    }

    public void deleteUser(Long userId){
        userRepo.deleteById(userId);
    }

    public UserDTO authenticate(String email, String password) {
        Optional<Object> OUser = userRepo.findByEmail(email);
        if (OUser.isPresent()){
            User checkUser = (User) OUser.get();
            if (password.equals(checkUser.getPassword())){
                return modelMapper.map(checkUser , UserDTO.class);
            }
            else {
                throw new RuntimeException("Incorrect Password");
            }
        }
        else {
            throw new RuntimeException("Invalid Email" + email);
        }
    }
    public UserDTO findByEml(String email){
        Optional<Object> OUser = userRepo.findByEmail(email);
        if (OUser.isEmpty()){
            User FUser = (User) OUser.get();
            return modelMapper.map(FUser , UserDTO.class);
        }
        else {
            throw new RuntimeException("cant find User in email: " + email);
        }
    }
}