package com.ammar.socialpocketa.api;

import com.ammar.socialpocketa.models.Login;
import com.ammar.socialpocketa.models.Post;
import com.ammar.socialpocketa.models.Register;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIService {

    //The register call
    @FormUrlEncoded
    @POST("api/users/register")
    Call<Register> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password2") String confirmPassword);



    //the login call
    @FormUrlEncoded
    @POST("api/users/login")
    Call<Login> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );


    //the post call
    // defined the Call type as a List and the List type as Post.
    @GET("api/posts")
    Call<List<Post>> getPosts();



}

