package com.study.alura.challenge.journeymiles.user.service.impl

import com.study.alura.challenge.journeymiles.model.exceptions.UserNotFoundByEmailException
import com.study.alura.challenge.journeymiles.model.exceptions.UserNotFoundException
import com.study.alura.challenge.journeymiles.model.exceptions.UsernameAlreadyExistsException
import com.study.alura.challenge.journeymiles.pictures.service.PicturesService
import com.study.alura.challenge.journeymiles.security.dto.UserDetail
import com.study.alura.challenge.journeymiles.user.dto.request.CreateUserRequestDTO
import com.study.alura.challenge.journeymiles.user.dto.response.UserDTO
import com.study.alura.challenge.journeymiles.user.mappers.toDTO
import com.study.alura.challenge.journeymiles.user.mappers.toEntity
import com.study.alura.challenge.journeymiles.user.repository.UserRepository
import com.study.alura.challenge.journeymiles.user.service.UserService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val picturesService: PicturesService
) : UserService {

    override fun createUser(createUserRequestDTO: CreateUserRequestDTO, picture: MultipartFile?): UserDTO {
        if (verifyIfEmailExists(createUserRequestDTO.email)) {
            throw UsernameAlreadyExistsException(createUserRequestDTO.email)
        }
        val userToSave = createUserRequestDTO.copy(password = passwordEncoder.encode(createUserRequestDTO.password))
        val savedUser = userRepository.save(userToSave.toEntity())
        picture?.run {
            picturesService.saveUserProfilePicture(this, savedUser.id!!)
        }
        return savedUser.toDTO()
    }

    override fun findUser(userId: Long) =
        getUserFromDatabase(userId)

    override fun verifyIfEmailExists(email: String) = userRepository.findByEmail(email) != null

    override fun verifyIfUserExists(userId: Long): Boolean {
        return runCatching { getUserFromDatabase(userId) }.onFailure { throw it }.isSuccess
    }

    override fun findUserByUsername(email: String) =
        userRepository.findByEmail(email)?.toDTO() ?: throw UserNotFoundByEmailException(email)

    private fun getUserFromDatabase(userId: Long) =
        userRepository.findByIdOrNull(userId)?.toDTO() ?: throw UserNotFoundException(userId)


    override fun loadUserByUsername(username: String?) = userRepository.findByEmail(username)?.let {
        UserDetail(it)
    } ?: throw UserNotFoundByEmailException(username)
}