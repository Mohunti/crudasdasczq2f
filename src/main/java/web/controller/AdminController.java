package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;


    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "adminIndex";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user,
                         BindingResult bindingResult, @RequestParam(value = "getRoles",required = false) String status) {
        if(bindingResult.hasErrors())
            return "new";
        Set<Role> roles = new HashSet<>();
        if(status.equals("ADMIN")){
            roles.add(new Role(1,"ROLE_ADMIN"));
        }

        if(status.equals("USER")){
            roles.add(new Role(2,"ROLE_USER"));
        }

        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/user";
    }
}
