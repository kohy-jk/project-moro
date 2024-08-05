package cz.kohnh.moro.users;

import java.util.List;

import jakarta.validation.Valid;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;
    private final ResponseUserValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUser> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new ResponseUser(user));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody ResponseUser user, BindingResult result) throws BindException {
        if (result.hasErrors()) {
            throw new BindException(result);
        }

        User u = user.toUser();
        userService.createUser(u);
        return ResponseEntity.ok(new ResponseUser(u));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUser> updateUser(@PathVariable int id, @RequestBody ResponseUser user, BindingResult result) throws NotFoundException, BindException {
        checkExistingUser(id);
        user.setId(id);
        validator.validate(user, result);
        if (result.hasErrors()) {
            throw new BindException(result);
        }
        User u = user.toUser();
        userService.updateUser(u);
        return ResponseEntity.ok(new ResponseUser(u));
    }

    private void checkExistingUser(int id) throws NotFoundException {
        User userById = userService.getUserById(id);
        if (userById == null) {
            throw new NotFoundException("User with id=" + id + " not exists");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(ResponseUser::new).toList());
    }
}

