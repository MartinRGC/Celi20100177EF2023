package com.example.celi20100177ef2023

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()
        val edtemail: EditText = findViewById(R.id.edtEmail)
        val edtpassw: EditText = findViewById(R.id.edtPassword)
        val btnLogin: Button = findViewById(R.id.btnlogin)


        btnLogin.setOnClickListener {
            var correo: String = edtemail.text.toString()
            var clave: String = edtpassw.text.toString()

            auth.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Credenciales Validadas",
                                Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this, PrincipalActivity::class.java))
                    } else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Credenciales Inválidas",
                                Snackbar.LENGTH_LONG
                            ).show()

                    }

                    }

                }
        }
    }

