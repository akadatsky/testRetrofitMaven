package com.akadatsky;

import com.akadatsky.api.ApiManager;
import com.akadatsky.model.Post;
import com.akadatsky.model.SendPostResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //requestPosts();
        sendPost();
    }

    private static void requestPosts() {
        ApiManager.getApiService().getPosts().enqueue(new Callback<List<Post>>() {
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                if (posts != null) {
                    System.out.println("posts.size: " + posts.size());
                }
            }

            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                System.out.println("onFailure: " + throwable);
            }
        });
    }

    private static void sendPost() {

        Post post = new Post(1,1, "test title", "test body");

        try {
            SendPostResult result = ApiManager.getApiService().sendPost(post).execute().body();
            System.out.println("result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
