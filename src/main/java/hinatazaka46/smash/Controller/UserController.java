package hinatazaka46.smash.Controller;


import hinatazaka46.smash.Domain.User;
import hinatazaka46.smash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    
    private final UserService userService;
    
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable int id) {
        return this.userService.getById(id);
    }
    
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody User user) {
        this.userService.add(user);
    }
}
