package com.ash.preproject.controllers;

import com.ash.preproject.model.User;
import com.ash.preproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userServiceI;

    @Autowired
    public UsersController(UserService userServiceI) {
        this.userServiceI = userServiceI;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceI.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PutMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceI.save(user);
        return "redirect:/users";
    }

    @GetMapping("/idForUpdate")
    public String showUpdateUserForm() {
        return "idForUpdate";
    }

    @PostMapping("/idForUpdate")
    public String processUpdateUserForm(@RequestParam("id") Long id) {
        return "redirect:/users/update/" + id;
    }

    @GetMapping("/update/{id}")
    public String updateUserByID(@PathVariable("id") Long id, Model model) {
        User user = userServiceI.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/update/{id}")
    public String update(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userServiceI.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/idForDelete")
    public String showDeleteUserForm() {
        return "idForDelete";
    }

    @DeleteMapping("/idForDelete")
    public String processDeleteUserForm(@RequestParam("id") Long id) {
        userServiceI.delete(id);
        return "redirect:/users";
    }
}