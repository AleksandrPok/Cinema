package philharmonic.service.mappers;

import org.springframework.stereotype.Component;
import philharmonic.model.User;
import philharmonic.model.dto.UserResponseDto;

@Component
public class UserMapper {
    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
