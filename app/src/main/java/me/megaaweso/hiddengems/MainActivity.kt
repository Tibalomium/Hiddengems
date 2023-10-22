package me.megaaweso.hiddengems

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var etEmail : EditText
    lateinit var etPassword :  EditText
    lateinit var btnLogin : Button
    lateinit var btnSignup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)

        btnLogin.setOnClickListener {
            login()
        }

        btnSignup.setOnClickListener {
            signUp()
        }

        /*if(auth.currentUser != null) {
            goToListgems()
        }*/
    }

    fun goToListgems() {
        val id = auth.currentUser?.uid.toString()
        if(id != null) {
            DataManager.startListener(id)
            val intent = Intent(this, ListgemsActivity::class.java)
            startActivity(intent)
        }
    }

    fun signUp() {
        val email = etEmail.text.toString()   // Skapa konto för kund
        val password = etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goToListgems()
                } else {

                }
            }
    }

    fun login() {
        val email = etEmail.text.toString()   // Skapa konto för kund
        val password = etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    goToListgems()
                } else {

                }
            }
    }

}