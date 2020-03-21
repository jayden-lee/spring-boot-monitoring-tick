package com.jayden.monitoring.service

import com.jayden.monitoring.domain.User
import com.jayden.monitoring.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Transactional
    fun createUser(name: String): Long {
        val user = User(name = name)
        userRepository.save(user)
        return user?.id!!
    }

    fun getUserList(): List<User> {
        return userRepository.findAll()
    }

    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { RuntimeException() }
    }
}
