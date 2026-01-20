package com.example.projetomobile1.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomobile1.data.network.Prefs.validarEFormatarUrl
import com.example.projetomobile1.data.network.Prefs.salvaUrl
import com.example.projetomobile1.data.network.RetrofitClient
import com.example.projetomobile1.databinding.ActivityServerUrlBinding

class ServerUrlBdActivity : AppCompatActivity() {
    private lateinit var bindingServerUrl : ActivityServerUrlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingServerUrl = ActivityServerUrlBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindingServerUrl.root)

        bindingServerUrl.btnMenu.setOnClickListener {
            var intentListaProdutos = Intent(this, ListaProdutosActivity::class.java)
            startActivity(intentListaProdutos)
        }


        bindingServerUrl.btnSalvar.setOnClickListener {

            val edtUrl = bindingServerUrl.edtUrl.text.toString()
            val urlValida = validarEFormatarUrl(edtUrl)


            if(urlValida != null){
                salvaUrl(this,urlValida)
                RetrofitClient.resetClient()
            }else{
                Toast.makeText(this,"Digite uma URL ou IP valido",Toast.LENGTH_SHORT).show()
            }
        }
    }
}


