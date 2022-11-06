package org.binar.msib.CinemaApp.controller;

import org.binar.msib.CinemaApp.dto.UserDTO;
import org.binar.msib.CinemaApp.entity.EntityUser;
import org.binar.msib.CinemaApp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //add user
    @PostMapping("/add")
    public UserDTO insertUser(@RequestBody UserDTO request){
        EntityUser entityUser = userService.mapToEntity(request);
        EntityUser result = userService.insertUser(entityUser);
        return userService.mapToDto(result);
    }
    //update user
    @PutMapping("/updateUser/{user_id}")
    public UserDTO updateUser(@PathVariable Integer user_id, @RequestBody UserDTO request){
        EntityUser entityUser = userService.mapToEntity(request);
        EntityUser result = userService.updateUser(user_id, entityUser);
        return userService.mapToDto(result);
    }
    //delete user
    @DeleteMapping("/deleteUser/{user_id}")
    public boolean deleteUser(@PathVariable String user_id){
        return userService.delete(Integer.valueOf(user_id));
    }
}
