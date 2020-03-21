package com.jayden.monitoring.controller

import com.jayden.monitoring.domain.User
import com.jayden.monitoring.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/v1/users")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @PostMapping
    fun createUser(@RequestBody request: CreateUserRequest) : Long {
        return userService.createUser(request.name)
    }

    @GetMapping
    fun getUserList() : List<User> {
        return userService.getUserList()
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable id: Long) : User {
        return userService.getUser(id)
    }
}

data class CreateUserRequest(
    @NotEmpty
    @NotNull
    val name: String
)
