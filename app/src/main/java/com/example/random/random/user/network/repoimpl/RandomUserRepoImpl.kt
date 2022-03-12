package com.example.random.random.user.network.repoimpl

import com.example.random.random.user.network.data.RandomUserResult
import com.example.random.random.user.network.endpoint.RandomUserEndPoints
import com.example.random.random.user.network.networkmodel.ServiceResult
import com.example.random.random.user.network.repo.RandomUserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class RandomUserRepoImpl @Inject constructor(
    private val dispatcher : Dispatchers,
    private val retroObject : RandomUserEndPoints
) : RandomUserRepo {
    override suspend fun fetchUser(): ServiceResult<RandomUserResult?> {
        return withContext(dispatcher.IO)
        {
            val dataResponse = retroObject.getUser()

            if(dataResponse.isSuccessful)
            {
                ServiceResult.Success(dataResponse.body())
            }
            else
            {
                ServiceResult.Error(Exception(dataResponse.errorBody().toString()))
            }
        }
    }

    override suspend fun fetchUsers(): ServiceResult<RandomUserResult?> {
        return withContext(dispatcher.IO)
        {
            val dataResponse = retroObject.getUsers()

            if(dataResponse.isSuccessful)
            {
                ServiceResult.Success(dataResponse.body())
            }
            else
            {
                ServiceResult.Error(Exception(dataResponse.errorBody().toString()))
            }
        }
    }
}
