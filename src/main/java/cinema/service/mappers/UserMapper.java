package cinema.service.mappers;

import cinema.model.User;
import cinema.model.dto.UserResponseDto;
import cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final RoleService roleService;

    @Autowired
    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
