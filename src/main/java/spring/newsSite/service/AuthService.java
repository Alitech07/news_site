package spring.newsSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.newsSite.entity.User;
import spring.newsSite.exceptions.ResurceNotFoundException;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.RegisterDTO;
import spring.newsSite.repository.RoleReposiotry;
import spring.newsSite.repository.UserRepository;
import spring.newsSite.utils.AppConstants;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleReposiotry roleReposiotry;
    @Autowired
    PasswordEncoder passwordEncoder;
    public ApiResponse registerUserService(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword())) return new ApiResponse("Parol xato.",false);
        boolean existsed = userRepository.existsByUsername(registerDTO.getUsername());
        if (existsed) return new ApiResponse("Bunday username oldin ro'yxatdan o'tgan",false);
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleReposiotry.findByName(AppConstants.user).orElseThrow(() -> new ResurceNotFoundException("Role","name",AppConstants.user)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yhatdan o'tdingiz.",true);
    }

    public UserDetails loadUserByUsername(String userNameForToken) {
        Optional<User> optionalUser = userRepository.findByUsername(userNameForToken);
        return optionalUser.orElse(null);
    }
}
