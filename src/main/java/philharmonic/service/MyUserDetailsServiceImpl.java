package philharmonic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public MyUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        philharmonic.model.User user = userService.findByEmail(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Can't get user by email: "
                        + userName));
        User.UserBuilder userBuilder = User.builder();
        userBuilder.username(user.getEmail());
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles().stream()
                .map(r -> r.getRoleName().toString())
                .toArray(String[]::new));
        return userBuilder.build();
    }
}
