package com.example.tim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle as Bundle1

class MainActivity : AppCompatActivity() {

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val dbLocal = Room.databaseBuilder(applicationContext, DetailDatabase::class.java, "prueba").build()

        //obtener parametro enviado desde LoginActivity
        val bundle = intent.extras
        val dato = bundle?.getString("codEmpleado")

        //Funcion para consultar nombre_empleado en firestore y colocarlo en un TextView
        fun getNameEmployed() {
            db.collection("usuarios").document(dato.toString()).get().addOnSuccessListener { document ->
                if (document.exists()){
                    val name = document.getString("nombre_empleado")
                    txt_saludo.text = "Bienvenido, $name"
                }
            }
        }
        getNameEmployed()

        //Traemos el id del recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CustomAdapter(dbLocal,this)

        //llenar el recyclerView con su data que llega del CustomAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //Cuando dan click en el boton volver al inicio (Login)
        btn_volver.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}