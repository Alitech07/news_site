package spring.newsSite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.newsSite.entity.Role;
import spring.newsSite.payload.ApiResponse;
import spring.newsSite.payload.RoleDto;
import spring.newsSite.repository.RoleReposiotry;

import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleReposiotry roleReposiotry;

    public ApiResponse addRoleService(RoleDto roleDto){
        Optional<Role> optionalRole = roleReposiotry.findByName(roleDto.getName());
        if (!optionalRole.isPresent()) return new ApiResponse("Bunday lavozim mavjud.",false);
        Role role = new Role();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setPermissons(roleDto.getPermissons());
        roleReposiotry.save(role);
        return new ApiResponse("Lavozim saqlandi.",true);
    }
}
