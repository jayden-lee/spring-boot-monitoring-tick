package com.jayden.monitoring.user;

import com.jayden.monitoring.db.entity.User;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MeterRegistry meterRegistry;

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        increaseCount(user, "create.user");
        return user;
    }

    @GetMapping
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        increaseCount(user, "get.user");
        return user;
    }

    private void increaseCount(User user, String name) {
        Counter counter = meterRegistry.counter(name, "user", user.getId().toString(), "name", user.getName());
        counter.increment();
    }
}
