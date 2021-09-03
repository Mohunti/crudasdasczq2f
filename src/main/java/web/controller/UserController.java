package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping()
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserName(auth.getName()));
        return "index";
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.showUserById(id));
//        return "show";
//    }
//
//
//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new User());
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "new";
//    }
//
//
//    @PostMapping()
//    public String create(@ModelAttribute("person") @Valid User user,
//                         BindingResult bindingResult,
//
//                         @RequestParam(value = "ADMIN", required = false) String ADMIN,
//                         @RequestParam(value = "USER", required = false) String USER) {
//        if (bindingResult.hasErrors())
//            return "new";
//
//        Set<Role> roles = new HashSet<>();
//        if(ADMIN != null){
//            roles.add(new Role(1,ADMIN));
//        }
//         if(USER != null){
//            roles.add(new Role(2,USER));
//        }
//         if(ADMIN == null && USER == null ){
//            roles.add(new Role(2,USER));
//        }
//        user.setRoles(roles);
//        userService.addUser(user);
//        return "redirect:/user";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("person", userService.showUserById(id));
//        model.addAttribute("roles", roleService.getAllRoles());
//
//        return "edit";
//    }
//
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("person") @Valid User user,
//                         BindingResult bindingResult,
//                         @PathVariable("id") int id,
//                         @RequestParam(value = "role", required = false) String[] AllRoles) {
//        if (bindingResult.hasErrors())
//            return "edit";
//        User user1 = user;
//        Set<Role> roles = new HashSet<>();
//        for (String role : AllRoles) {
//            roles.add(roleService.findRoles(role));
//        }
//        user1.setRoles(roles);
//        userService.updateUser(user1);
//
//
//
//        return "redirect:/user";
//    }
//
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.removeUser(id);
//        return "redirect:/user";
//    }
//
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public String loginPage() {
//        return "login";
//    }

}


















