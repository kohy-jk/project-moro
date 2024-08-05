package cz.kohnh.moro.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseUser {

	private Integer id;
	private String name;
	private String username;
	private String password;

	
	ResponseUser(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.password = "~~hidden~~";
	}
	
	User toUser() {
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setUsername(username);
		u.setPassword(password);
		return u;
	}
}
