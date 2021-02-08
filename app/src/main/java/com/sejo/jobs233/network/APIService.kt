package com.sejo.jobs233.network

import android.content.Context
import com.sejo.jobs233.models.data.user.User
import com.sejo.jobs233.models.responses.*
import com.sejo.jobs233.network.API.APIContext
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://192.168.43.161:8000/api/v1/"

private const val httpConnectTimeoutSeconds = 20
private const val httpWriteTimeoutSeconds = 20
private const val httpReadTimeoutSeconds = 20

private val client = OkHttpClient.Builder()
    .addInterceptor(AuthTokenInterceptor(APIContext))
    .followRedirects(true)
    .followSslRedirects(true)
    .retryOnConnectionFailure(true)
    .cache(null)
    .connectTimeout(httpConnectTimeoutSeconds.toLong(), TimeUnit.SECONDS)
    .writeTimeout(httpWriteTimeoutSeconds.toLong(), TimeUnit.SECONDS)
    .readTimeout(httpReadTimeoutSeconds.toLong(), TimeUnit.SECONDS)
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .build()


interface Jobs233API {

    //Authentication
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun registerUser(
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("preference") preference: String,
    ): RegisterResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("user")
    suspend fun getUser(): User

    @Headers("Accept: application/json")
    @GET("auth/logout")
    fun logoutUser(): Response<Void>

    @FormUrlEncoded
    @POST("auth/request_token")
    suspend fun requestToken(
        @Field("identity") email: String,
        @Field("password") password: String,
        @Field("device_name") deviceName: String
    ): Response<String>


    //Email
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @GET("projects")
    suspend fun verifyEmail(): AllProjectsResponse


    //Forgot Password
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @FormUrlEncoded
    @POST("password-reset")
    suspend fun forgotPassword(@Field("email") email: String): AllProjectsResponse

    //Projects
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @GET("projects")
    suspend fun getAllProjects(): AllProjectsResponse

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @GET("projects/assigned")
    suspend fun getAssignedProjects(): AllProjectsResponse

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @GET("projects/{projectID}")
    suspend fun getProject(@Path("projectID") projectID: Int): ProjectResponse


    //Profile
    @Headers("Content-Type: application/json", "Accept: application/json")
    @FormUrlEncoded
    @PATCH("profile/")
    suspend fun updateProfile(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("title") title: String,
        @Field("phone_number") phoneNumber: String,
        @Field("gender") gender: String,
        @Field("bio") bio: String,
        @Field("country") country: String,
        @Field("city") city: String,
        @Field("address") address: String
    ): Response<ResponseBody>


    //Messaging
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("messages/contacts")
    suspend fun getContacts(): ContactsResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("messages/{userId}/getAll")
    suspend fun getAllUserMessages(@Path("userId") userID: Int): MessagesResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("messages")
    suspend fun sendMessage(): ResponseBody


    //Notifications
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("notifications")
    suspend fun getNotifications(): NotificationsResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("notifications/mark_all_as_read")
    suspend fun markAllNotificationsAsRead(): AllNotificationsReadResponse
}


object API {
    lateinit var APIContext: Context

    val instance: Jobs233API by lazy {
        retrofit.create(Jobs233API::class.java)
    }
}