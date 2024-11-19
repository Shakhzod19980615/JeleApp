package com.example.jeleapps

import android.app.Application
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase or other global libraries
        FirebaseApp.initializeApp(this)
    }
}