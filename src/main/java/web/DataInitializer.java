package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
/*

!!!!!!! Копался в коде, не могу понять почему не работает класс, вроде что то со связью(могу ошибаться)
Кажется не работает метод save в RoleService!!!!!!!

 */
@Component
public class DataInitializer {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Init() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role(1, "ROLE_ADMIN"));
        allRoles.add(new Role(2, "ROLE_USER"));
        for (Role role : allRoles) {
            roleService.save(role);
        }

        Set<Role> allRolesUser = new HashSet<>();
        String[] rol = {"ROLE_USER"};
        for (String s : rol) {
            allRolesUser.add(roleService.findRoles(s));
        }

        Set<Role> allRolesAdmin = new HashSet<>();
        String[] rol2 = {"ROLE_ADMIN", "ROLE_USER"};
        for (String s : rol2) {
            allRolesAdmin.add(roleService.findRoles(s));
        }

        User user = new User();
        user.setUsername("User");
        user.setLogin("User");
        user.setEmail("User@mail.ru");
        user.setAge(12);
        user.setPassword(new BCryptPasswordEncoder(12).encode("123"));
        user.setRoles(allRolesUser);

        User admin = new User();
        admin.setUsername("Admin");
        admin.setLogin("Admin");
        admin.setEmail("Admin@mail.ru");
        admin.setAge(12);
        admin.setPassword(new BCryptPasswordEncoder(12).encode("123"));
        admin.setRoles(allRolesAdmin);

        userService.save(user);
        userService.save(admin);
    }
}
