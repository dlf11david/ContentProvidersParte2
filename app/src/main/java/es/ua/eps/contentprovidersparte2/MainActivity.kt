package es.ua.eps.contentprovidersparte2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.ua.eps.contentprovidersparte2.databinding.ActivityMainBinding

var welcomeName = ""
var userName = ""

class MainActivity : AppCompatActivity() {
    companion object {
        val PROVIDER_NAME = "es.ua.eps.sqlite/ProviderCode"
        val URL = "content://$PROVIDER_NAME/usuarios"
        val CONTENT_URI = Uri.parse(URL)
        val _ID = "_id"
        val NOMBRE_USUARIO = "nombre_usuario"
        val PASSWORD = "password"
        val NOMBRE_COMPLETO = "nombre_completo"
        val EMAIL = "email"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cursor = contentResolver.query(
            CONTENT_URI,
            arrayOf(NOMBRE_USUARIO, EMAIL),
            null,
            null,
            null)
        with(binding) {
            btnLogin.setOnClickListener{
                if (etUser.text.isNotBlank() &&
                    etPass.text.isNotBlank()) {
                    val cursor = contentResolver.query(
                        CONTENT_URI,
                        arrayOf(NOMBRE_COMPLETO, NOMBRE_USUARIO),
                        "${NOMBRE_USUARIO} = ? AND ${PASSWORD} = ?",
                        arrayOf(etUser.text.toString(),etPass.text.toString()),
                        null)
                    if (cursor!!.moveToFirst()) {
                        welcomeName = cursor.getString(0).toString()
                        userName = cursor.getString(1).toString()
                        cursor.close()
                        Intent(this@MainActivity,UserData::class.java).also {
                            startActivity(it)
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(this@MainActivity, "Error usuario/password incorrectos", Toast.LENGTH_LONG).show()
                }
            }
            btnClose.setOnClickListener{
                this@MainActivity.finish()
            }
        }
    }
}