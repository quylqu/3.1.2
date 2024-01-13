package com.ash.preproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ash.preproject.dao.UserDao;
import com.ash.preproject.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDao userDAO;

    @Autowired
    public UsersController(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    @Transactional
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users";
    }

    @GetMapping("/idForUpdate")
    public String showUpdateUserForm() {
        return "idForUpdate";
    }

    @PostMapping("/idForUpdate")
    public String processUpdateUserForm(@RequestParam("userId") Long userId) {
        return "redirect:/users/update/" + userId;
    }

    @GetMapping("/update/{id}")
    public String updateUserByID(@PathVariable("id") Long id, Model model) {
        User user = userDAO.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update/{id}")
    @Transactional
    public String update(@RequestParam("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userDAO.update(user);
        return "redirect:/users";
    }

    @GetMapping("/idForDelete")
    public String showDeleteUserForm() {
        return "idForDelete";
    }

    @PostMapping("/idForDelete")
    @Transactional
    public String processDeleteUserForm(@RequestParam("userId") Long userId) {
        userDAO.delete(userId);
        return "redirect:/users";
    }
}