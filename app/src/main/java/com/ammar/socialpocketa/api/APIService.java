package com.ammar.socialpocketa.api;

import com.ammar.socialpocketa.models.Comment;
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
import retrofit2.http.Path;


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


    //the comment call
    // defined the Call type as a List and the List type as Comment.

    @GET("api/posts/detail/{postId}")
    Call<Comment> getComments(@Path("postId") String postId);
//    @GET("api/posts/detail/5b951359d498032e88f99844")
//    Call<Comment> getComments();




}

