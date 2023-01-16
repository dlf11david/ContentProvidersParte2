package es.ua.eps.contentprovidersparte2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.contentprovidersparte2.databinding.ActivityMainBinding

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
            texto.text = "Name & Email: \n"

            if (cursor!!.moveToFirst()) {
                do {
                    texto.append(cursor.getString(0).toString()+": ")
                    texto.append(cursor.getString(1).toString()+"\n")
                } while (cursor.moveToNext())
            }

            cursor.close()



        }
    }
}