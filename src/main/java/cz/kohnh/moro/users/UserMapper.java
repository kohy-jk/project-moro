package cz.kohnh.moro.users;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User selectUserById(Long id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
    List<User> selectAllUsers();
}