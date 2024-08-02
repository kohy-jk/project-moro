package cz.kohnh.moro.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;
}
