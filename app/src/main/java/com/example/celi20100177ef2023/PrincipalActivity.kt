package com.example.celi20100177ef2023

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.celi20100177ef2023.BD.Products
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class PrincipalActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)


        val idproducto = findViewById<EditText>(R.id.edtId)
        val descripcion = findViewById<EditText>(R.id.edtDescription)
        val cantidad = findViewById<EditText>(R.id.edtQuantity)
        val precio = findViewById<EditText>(R.id.edtPrice)
        val btnregistrar = findViewById<Button>(R.id.btnRegistrar)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("Productos")

        btnregistrar.setOnClickListener {
            val id = idproducto.toString()
            val descrip = descripcion.toString()
            val cant = cantidad.toString()//.toInt
            val pre = precio.toString()//.toDouble
            val fakeEmail = "hola@example.com"
            val fakePassword = "fakepassword"
            auth.createUserWithEmailAndPassword(fakeEmail, fakePassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Se registró en Firebase Auth y deberá registrarse en Firestore
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid
                        val product = Products(id, descrip, cant, pre)

                        collectionRef.add(product)
                            .addOnCompleteListener {

                            }.addOnFailureListener { error ->
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content),
                                        "Ocurrió un error al registrar el modelo",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Registro exitoso ",
                                Snackbar.LENGTH_LONG
                            ).show()


                    } else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Ocurrió un error ",
                                Snackbar.LENGTH_LONG
                            ).show()


                    }
                }


        }

    }
}