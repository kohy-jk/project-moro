package cz.kohnh.moro.users;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	User selectUserById(int id);

	void insertUser(User user);

	void updateUser(User user);

	void deleteUser(int id);

	List<User> selectAllUsers();
	
	User selectByUsername(String username);
}