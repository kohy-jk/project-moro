package cz.kohnh.moro.users;

import jakarta.validation.constraints.NotBlank;

import cz.kohnh.moro.validation.UniqueUsername;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseUser {

	private Integer id;
	@NotBlank
  private String name;
	
	@UniqueUsername
	@NotBlank
  private String username;
	
	
	ResponseUser(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
	}
	
	User toUser() {
		User u = new User();
		u.setId(id);
		u.setName(name);
		u.setUsername(username);
		return u;
	}
}
