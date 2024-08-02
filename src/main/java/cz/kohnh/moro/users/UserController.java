package cz.kohnh.moro.users;

import java.util.List;

import jakarta.validation.Valid;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

		@GetMapping("/{id}")
		public ResponseEntity<ResponseUser> getUserById(@PathVariable int id) {
			User user = userService.getUserById(id);
			if (user != null) {
				return ResponseEntity.ok( new ResponseUser( user));
			}
			return ResponseEntity.notFound().build();
		}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody ResponseUser user) {
    	User u = user.toUser();
        userService.createUser(u);
        return ResponseEntity.ok( new ResponseUser(u));
    }

		@PutMapping("/{id}")
		public ResponseEntity<ResponseUser> updateUser(@PathVariable int id, @Valid @RequestBody ResponseUser user) throws NotFoundException {
			User userById = userService.getUserById(id);
			if(userById == null) {
				throw new NotFoundException("User with id="+id+" not exists");
			}
			User u = user.toUser();
			u.setId(id);
			userService.updateUser(u);
			return ResponseEntity.ok(new ResponseUser(u));
		}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(u -> new ResponseUser(u)).toList() );
    }
}

