package com.example.chatapp.Fragments;

import Notification.MyResponse;
import Notification.Sender;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAArYcP0gM:APA91bF51lzxj-m_mR-uYfoUiBNyLTss1POAa0NV4sS2VVngrN0eYrtx0wPTMXHuXeUSGE0zqNAdNrakCZfvvO3QF_HF12cdMeIDtvMC6FTZU8Sd4PlgG-2fiOAlMn1fKuO_8jIG02fA"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
