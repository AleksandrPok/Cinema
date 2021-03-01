package philharmonic.service;

import java.util.Optional;
import philharmonic.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User get(Long id);
}
