package com.example.notesapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.Room.NotesDATABASE
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.ui.HomeFrgment
import com.example.notesapp.ui.LoginFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    private val REQUEST_CODE_ASK_PERMISSIONS = 101

    companion object {
        lateinit var mGoogleSignInClient: GoogleSignInClient
        var signInCode = 100
        lateinit var acct: GoogleSignInAccount
        lateinit var sharedPrefs: SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



 // Inflate the layout for this fragmentte
        sharedPrefs = this.getSharedPreferences("notes_shared_prefs", Context.MODE_PRIVATE)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //login fragment launch
        val fragLogin = LoginFragment()
        val fragHome = HomeFrgment()
        if (sharedPrefs.getBoolean("is_signed", false)) {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragHome, "HomeFragment").commit()
        } else {
            this.supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragLogin, "LoginFragment").commit()
        }
    }






}