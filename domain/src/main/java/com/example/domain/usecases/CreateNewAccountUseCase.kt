package com.example.domain.usecases
import com.example.domain.repositories.GRegisterRepository
import javax.inject.Inject

class CreateNewAccountUseCase @Inject constructor(
private val gRegisterRepository: GRegisterRepository
){
suspend operator fun invoke(username: String, password:String) = gRegisterRepository.createAccount(username =username,
    password=password )
}