package com.example.random.random.user.data.remote.repo

import com.example.random.random.user.data.model.RandomUserResult
import com.example.random.random.user.data.remote.networkmodel.ServiceResult

interface RandomUserRepo {
    suspend fun fetchUser() : ServiceResult<RandomUserResult?>

    suspend fun fetchUsers() : ServiceResult<RandomUserResult?>
}
