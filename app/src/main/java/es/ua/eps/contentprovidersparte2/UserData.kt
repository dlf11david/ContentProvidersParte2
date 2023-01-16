package es.ua.eps.contentprovidersparte2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.ua.eps.contentprovidersparte2.databinding.ActivityUserDataBinding

class UserData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            tvWelcome.append(": \n"+welcomeName)
            tvName.append(": \n"+userName)
            btnBack.setOnClickListener {
                this@UserData.finish()
            }
        }
    }
}