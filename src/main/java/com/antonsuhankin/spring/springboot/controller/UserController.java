package com.antonsuhankin.spring.springboot.controller;

import com.antonsuhankin.spring.springboot.model.User;
import com.antonsuhankin.spring.springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<User> userList = userService.getListUser();
        model.addAttribute("UserList", userList);
        return "StartPage";
    }

    @RequestMapping(value = "/addNewUser1")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("User",user);
        return "UserInfo";
    }

    @GetMapping(value = "/addOrUpdate")
    public String AddingPerson(@ModelAttribute("User") User user) {
        if(user.getId()==null){
            userService.addUser(user.getName(),user.getSurname(),user.getDepartment(),user.getLocation());
        } else {
            userService.updateUser(user);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/updUser/{id}")
    public String updUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("User",user);
        return "UserInfo";
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/";
    }


}
