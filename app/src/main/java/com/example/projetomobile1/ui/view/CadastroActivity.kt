package com.example.projetomobile1.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomobile1.databinding.ActivityCadastroBinding
import com.example.projetomobile1.ui.viewmodel.AuthViewModel

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val email = binding.etRegEmail.text.toString()
            val name = binding.etRegName.text.toString()
            val password = binding.etRegPassword.text.toString()

            if (email.isNotEmpty()&& name.isNotEmpty() && password.isNotEmpty()){
                viewModel.registrar(email,password)
            }else{
                Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.registerResult.observe(this){ sucesso ->
            if(sucesso){
                Toast.makeText(this,"Conta criada", Toast.LENGTH_SHORT).show()
                val intentLogin = Intent(this, LoginActivity::class.java)
                startActivity(intentLogin)
            }else{
                Toast.makeText(this,"Erro ao cadastrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}