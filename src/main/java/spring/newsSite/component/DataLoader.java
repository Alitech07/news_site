package spring.newsSite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.newsSite.entity.Role;
import spring.newsSite.entity.User;
import spring.newsSite.entity.enums.Permisson;
import spring.newsSite.repository.RoleReposiotry;
import spring.newsSite.repository.UserRepository;
import spring.newsSite.utils.AppConstants;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleReposiotry roleReposiotry;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMod;

    @Override
    public void run(String... args) throws Exception {

        if (initMod.equals("always")){
            Permisson[] permissons = Permisson.values();
            Role admin = roleReposiotry.save(new Role(
                    AppConstants.admin,
                    Arrays.asList(permissons),
                    "Tizim adminstratori."
            ));
            Role user = roleReposiotry.save(new Role(
                    AppConstants.user,
                    Arrays.asList(
                            Permisson.ADD_COMMENT,
                            Permisson.DELETE_MY_COMMENT,
                            Permisson.EDIT_COMMENT
                    ),
                    "Oddiy foydalanuvchi."
            ));


            userRepository.save(
                    new User(
                            "Admin",
                            "admin",
                            passwordEncoder.encode("admin1234"),
                            admin,
                            true
                    )
            );

            userRepository.save(
                    new User(
                            "User",
                            "user",
                            passwordEncoder.encode("user789"),
                            user,
                            true
                    )
            );
        }

    }
}
