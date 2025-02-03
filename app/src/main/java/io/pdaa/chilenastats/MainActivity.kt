package io.pdaa.chilenastats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import io.pdaa.chilenastats.ui.navigation.AUTH_PORT
import io.pdaa.chilenastats.ui.navigation.LOCALHOST
import io.pdaa.chilenastats.ui.navigation.Navigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureFirebaseServices()
        enableEdgeToEdge()
        setContent {
            Navigation()
        }
    }

    private fun configureFirebaseServices() {
        if (true/*BuildConfig.DEBUG*/) {
            Firebase.auth.useEmulator(LOCALHOST, AUTH_PORT)
            //Firebase.firestore.useEmulator(LOCALHOST, FIRESTORE_PORT)
        }
    }

}

