package cinema.service;

import cinema.dao.UserDao;
import cinema.lib.Inject;
import cinema.lib.ServiceImpl;
import cinema.model.User;
import cinema.util.HashUtil;
import java.util.Optional;

@ServiceImpl
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
