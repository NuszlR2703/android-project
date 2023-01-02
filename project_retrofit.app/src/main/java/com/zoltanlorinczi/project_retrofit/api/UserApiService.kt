package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit web service API.
 *
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_GROUPS_URL)
    suspend fun getGroups(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<GroupsResponse>>

    @GET(BackendConstants.GET_USERS_URL)
    suspend fun getUsers(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<UsersResponse>>

}