package com.fitness.domain.usecase.user

import cache.Fields
import com.fitness.data.repository.user.UserRepository
import kotlinx.coroutines.flow.FlowCollector
import state.DataState
import javax.inject.Inject

class UpdateUserUseCaseImpl  @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserUseCase() {

    override suspend fun FlowCollector<DataState<Unit>>.execute(params: Params) {
        val result = when(params){
            is Params.UpdateEmail -> {
                userRepository.updateUser(params.id, mapOf(Fields.EMAIL to params.email, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateDisplayName -> {
                userRepository.updateUser(params.id, mapOf(Fields.DISPLAY_NAME to "${params.firstname} ${params.lastname}", Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdatePhoneNumber -> {
                userRepository.updateUser(params.id, mapOf(Fields.PHONE_NUMBER to params.phone, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateProfilePictureUrl -> {
                userRepository.updateUser(params.id, mapOf(Fields.PROFILE_PICTURE_URL to params.profilePictureUrl, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateIsTermsPrivacyAccepted -> {
                userRepository.updateUser(params.id, mapOf(Fields.IS_TERMS_PRIVACY_ACCEPTED to params.isTermAndPrivacyAccepted, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
            is Params.UpdateIsNewUser -> {
                userRepository.updateUser(params.id, mapOf(Fields.IS_NEW_USER to params.isNewUser, Fields.LAST_UPDATED to System.currentTimeMillis()))
            }
        }

        emit(result)
    }
}