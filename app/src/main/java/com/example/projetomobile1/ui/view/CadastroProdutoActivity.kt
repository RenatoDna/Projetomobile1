package com.example.projetomobile1.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomobile1.data.model.Product
import com.example.projetomobile1.databinding.ActivityCadastroProdutoBinding
import com.example.projetomobile1.ui.viewmodel.ProductViewModel

class CadastroProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroProdutoBinding
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.buttonSalvarProduto.setOnClickListener {
            val nome = binding.editTextNomeProduto.text.toString()
            val descricao = binding.editTextDescricaoProduto.text.toString()
            val preco = binding.editTextPrecoProduto.text.toString().toDoubleOrNull()

            if (nome.isNotEmpty() && descricao.isNotEmpty() && preco != null) {
                val product = Product(nome, descricao, preco)
                viewModel.createProduct(product)
            } else {
                Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }
        }
        binding.buttonListaProdutos.setOnClickListener {
            val intent = Intent(this, ListaProdutosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {
        viewModel.createProductResult.observe(this) { success ->
            if (success) {
                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editTextNomeProduto.text.clear()
                binding.editTextDescricaoProduto.text.clear()
                binding.editTextPrecoProduto.text.clear()
            } else {
                Toast.makeText(this, "Erro ao cadastrar produto", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
