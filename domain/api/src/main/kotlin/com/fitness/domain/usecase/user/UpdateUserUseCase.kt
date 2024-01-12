package com.fitness.domain.usecase.user

import usecase.DataStateUseCase

abstract class UpdateUserUseCase: DataStateUseCase<UpdateUserUseCase.Params, Unit>(){
    sealed class Params{
        data class UpdateDisplayName(val id: String, val firstname: String, val lastname: String): Params()
        data class UpdateEmail(val id: String, val email: String): Params()
        data class UpdatePhoneNumber(val id: String, val phone: String): Params()
        data class UpdateProfilePictureUrl(val id: String, val profilePictureUrl: String): Params()
        data class UpdateIsTermsPrivacyAccepted(val id: String, val isTermAndPrivacyAccepted: Boolean): Params()
        data class UpdateIsNewUser(val id: String, val isNewUser: Boolean): Params()
    }

}