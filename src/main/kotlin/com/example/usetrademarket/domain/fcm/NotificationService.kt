package com.example.usetrademarket.domain.fcm

import com.example.usetrademarket.domain.user.entity.User
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service

@Service
class NotificationService {

    private val firebaseApp by lazy {
        val inputStream = ClassPathResource(
                "usetradeapp-firebase-adminsdk-32ap3-dfff88a549.json"
        ).inputStream

        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()

        FirebaseApp.initializeApp(options)
    }

    fun sendToUser(user: User, title: String, content: String) =
            user.fcmToken?.let { token ->
                val message = Message.builder()
                        .setToken(token)
                        .putData("title", title)
                        .putData("content", content)
                        .build()

                FirebaseMessaging.getInstance(firebaseApp).send(message)
            }
}