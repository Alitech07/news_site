package spring.newsSite.service;

import org.springframework.stereotype.Service;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.UserDTO;

@Service
public class UserService {

    public ApiResponse addUserService(UserDTO userDTO) {
        return new ApiResponse("User saqlandi.",true);
    }
}
