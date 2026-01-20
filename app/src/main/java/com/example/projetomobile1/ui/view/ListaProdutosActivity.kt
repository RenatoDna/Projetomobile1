package com.example.projetomobile1.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetomobile1.databinding.ActivityListaProdutosBinding
import com.example.projetomobile1.ui.adapter.ProductAdapter
import com.example.projetomobile1.ui.viewmodel.ProductViewModel

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaProdutosBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        viewModel.getProducts()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(emptyList())
        binding.recyclerViewProdutos.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(this@ListaProdutosActivity)
        }
    }

    private fun observeViewModel() {
        viewModel.productsList.observe(this) { products ->
            productAdapter.updateProducts(products)
        }

        viewModel.errorMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}
