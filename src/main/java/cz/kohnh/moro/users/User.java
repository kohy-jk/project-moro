package cz.kohnh.moro.users;

import cz.kohnh.moro.config.SecurityConfig;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;


    public String getCriptedPassword(){
        return SecurityConfig.encoder.encode(password);
    }
}
