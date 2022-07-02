package com.utsman.reqres.data

object Mapper {
    fun mapUserResponses(userResponses: UserResponses?): List<User> {
        val mapper: (UserResponses.Data?) -> User = {
            User(
                id = it?.id ?: 0,
                email = it?.email.orEmpty(),
                name = "${it?.firstName.orEmpty()} ${it?.lastName.orEmpty()}"
            )
        }

        return userResponses?.data?.map(mapper).orEmpty()
    }

    fun mapUserDetailResponse(userDetailResponse: UserDetailResponse?): User {
        return User(
            id = userDetailResponse?.data?.id ?: 0,
            email = userDetailResponse?.data?.email.orEmpty(),
            name = "${userDetailResponse?.data?.firstName.orEmpty()} ${userDetailResponse?.data?.lastName.orEmpty()}"
        )
    }
}