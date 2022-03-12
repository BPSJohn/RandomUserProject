package com.example.random.random.user.network.repo

import com.example.random.random.user.network.data.RandomUserResult
import com.example.random.random.user.network.networkmodel.ServiceResult

interface RandomUserRepo {
    suspend fun fetchUser() : ServiceResult<RandomUserResult?>

    suspend fun fetchUsers() : ServiceResult<RandomUserResult?>
}
