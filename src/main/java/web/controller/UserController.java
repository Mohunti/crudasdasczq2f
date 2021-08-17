package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "show";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());
        return "new";
    }


    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "new";

        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userService.showUserById(id));
        return "edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "edit";

        userService.updateUser(user);
        return "redirect:/user";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/user";
    }
}


















