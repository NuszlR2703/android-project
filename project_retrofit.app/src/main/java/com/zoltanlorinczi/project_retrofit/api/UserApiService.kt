package com.zoltanlorinczi.project_retrofit.api

import com.zoltanlorinczi.project_retrofit.api.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_GROUPS_URL)
    suspend fun getGroups(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<GroupsResponse>>

    @GET(BackendConstants.GET_USERS_URL)
    suspend fun getUsers(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<UsersResponse>>

    @GET(BackendConstants.GET_MY_USER_URL)
    suspend fun getMyUser(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<UsersResponse>

    @POST(BackendConstants.SAVE_USER_EDIT)
    suspend fun updateUser(@Header(BackendConstants.HEADER_TOKEN) token: String, @Body userRequest: UserRequestBody): Response<String>

}