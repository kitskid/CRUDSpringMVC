package com.example.crudusers.controller;
import com.example.crudusers.models.User2;
import com.example.crudusers.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ControllerUser {

    private final UserServices userServices;
    @Autowired
    public ControllerUser(UserServices userServices){
        this.userServices = userServices;
    }
//метод по открытию страницы с пользователями
    @GetMapping("user/start")
    public String index(Model model) {
//        model.addAttribute("user", daoUser.getUsersList());
        model.addAttribute("user", userServices.getAllUser());
        return "index";
    }

    @GetMapping("user/reg")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User2());

        return "registration";
    }

    @PostMapping("user/new")
    public String addToBaseNewUser(@ModelAttribute("user") @Valid User2 user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registration";
        }
    //    daoUser.addToBaseNewUser(user);
        userServices.save(user);
        return "redirect:/start";
    }
    @GetMapping("/start")
    public String reload() {
        return "redirect:user/start";
    }

    //метод по отправке информации о конкретном пользователе на шаблон
    @GetMapping("user/{id}")
    public String infoUser(@PathVariable("id") int id, Model model){
//        model.addAttribute("infoUser", daoUser.getUserById(id));
        model.addAttribute("infoUser", userServices.getUserId(id));
        return "infoUser";
    }
    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id){
        model.addAttribute("editUser", userServices.getUserId(id));
        return "editUser";
    }
    @PostMapping("{id}/edit")
    public String editUser(@ModelAttribute("editUser") @Valid User2 user, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "editUser";
        }
  //      daoUser.updateUser(id, user);
        userServices.update(id, user);
        return "redirect:/start";
    }


    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id){
 //       daoUser.deleteUser(id);
        userServices.delete(id);
        return "redirect:/start";
    }


    @GetMapping("user/add")
    public String newUser(Model model) {
        model.addAttribute("user", new User2());
        return "addUser";
    }
    @PostMapping("user/add")
    public String newUser(@ModelAttribute("user") @Valid User2 user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "addUser";
        }
 //       daoUser.addToBaseNewUser(user);
        userServices.save(user);
        return "redirect:/start";
    }

}
