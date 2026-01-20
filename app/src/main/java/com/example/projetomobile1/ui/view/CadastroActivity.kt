package com.example.projetomobile1.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomobile1.databinding.ActivityCadastroBinding
import com.example.projetomobile1.ui.viewmodel.AuthViewModel

import kotlin.jvm.java
import kotlin.text.isNotEmpty
import kotlin.toString

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email = binding.etRegEmail.text.toString()
            val senha = binding.etRegPassword.text.toString()

            if(email.isNotEmpty() && senha.isNotEmpty()){
                viewModel.registrar(email, senha)
            }else{
                Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.registerResult.observe(this){ sucesso ->
            if(sucesso){
                Toast.makeText(this,"Conta criada! Faça Login", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Erro ao cadastrar usuario", Toast.LENGTH_SHORT).show()
            }
        }

    }
}