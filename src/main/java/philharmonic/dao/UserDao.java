package philharmonic.dao;

import java.util.Optional;
import philharmonic.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> get(Long id);
}
