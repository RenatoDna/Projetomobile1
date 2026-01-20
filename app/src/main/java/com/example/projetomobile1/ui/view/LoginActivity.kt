package com.example.projetomobile1.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomobile1.databinding.ActivityLoginBinding
import com.example.projetomobile1.ui.viewmodel.AuthViewModel


import kotlin.getValue

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val senha = binding.etPassword.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()) {
                binding.loading.visibility = View.VISIBLE
                viewModel.realizarLogin(email, senha)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvGoToRegister.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { sucesso ->
            binding.loading.visibility = View.GONE
            if (sucesso) {
                Toast.makeText(this, "Login com sucesso!", Toast.LENGTH_SHORT).show()
                val intentCadastroProdutoActivity = Intent(this, CadastroProdutoActivity::class.java)
                startActivity(intentCadastroProdutoActivity)
                finish() // Encerra a LoginActivity para que ela saia da pilha de telas

                // -------------------------------------------

            } else {
                Toast.makeText(this, "Erro: Email ou senha incorretos", Toast.LENGTH_LONG).show()
            }
        }
    }
}