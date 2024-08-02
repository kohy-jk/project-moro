package cz.kohnh.moro.users;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.kohnh.moro.config.SecurityConfig;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
class UserService {
		
	public static final BCryptPasswordEncoder encoder = SecurityConfig.encoder;
		
    private final UserMapper userMapper;

    User getUserById(int id) {
        return userMapper.selectUserById(id);
    }

    void createUser(User user) {
        userMapper.insertUser(user);
    }

    void updateUser(User user) {
        userMapper.updateUser(user);
    }

    void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }
}
