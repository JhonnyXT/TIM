package com.example.tim

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity()  {

    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtCodEmpleado.addTextChangedListener(textWather)
        edtPin.addTextChangedListener(textWather)

        //obtener el cod_empleado que colocan en el editText
        val codEmpleado = edtCodEmpleado.text

        //cuando dan click en el boton "Iniciar sesion" guardar los datos en firestore
        btnIniciar.setOnClickListener {

            db.collection("usuarios").document(codEmpleado.toString()).get().addOnSuccessListener { document ->
                if (!document.exists()) {
                    showAlert()
                } else {
                    showActivity(codEmpleado.toString())
                }
            }
        }
    }

    //Funcion para abrir la MainActivity
    private fun showActivity(codEmployed: String){
        startActivity(Intent(this, MainActivity::class.java).putExtra("codEmpleado", codEmployed))
        finish()
    }

    //Metodo para mostrar la alerta cuando el usuario no se encuentra registrado en firestore database
    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alerta")
        builder.setMessage("El usuario no se encuentra registrado")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Clase TextWather para poder habilitar el boton
    private val textWather = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            btnIniciar.isEnabled = !edtCodEmpleado.text.isEmpty() && !edtPin.text.isEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}