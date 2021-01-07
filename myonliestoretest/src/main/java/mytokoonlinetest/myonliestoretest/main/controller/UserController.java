package mytokoonlinetest.myonliestoretest.main.controller;

import mytokoonlinetest.myonliestoretest.auth.model.User;
import mytokoonlinetest.myonliestoretest.main.exception.ResourceNotFoundException;
import mytokoonlinetest.myonliestoretest.main.repository.UserDuaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserDuaRepository userDuaRepository;

    @GetMapping("/api/auth/user")
    public Page<User> getAllUser(Pageable pageable) {
        return userDuaRepository.findAll(pageable);
    }

    @PostMapping("/api/auth/user")
    public User createUser(@Valid @RequestBody User user) {
        return userDuaRepository.save(user);
    }

    @PutMapping("/api/auth/user/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userDuaRepository.findById(userId).map(user -> {
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPhone(userRequest.getPhone());
            user.setPassword(userRequest.getPassword());
            return userDuaRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
    }


    @DeleteMapping("/api/auth/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userDuaRepository.findById(userId).map(user -> {
            userDuaRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + userId + " not found"));
    }

}
