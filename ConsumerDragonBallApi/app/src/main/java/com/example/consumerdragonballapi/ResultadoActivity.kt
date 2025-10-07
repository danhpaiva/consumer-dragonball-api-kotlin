package com.example.consumerdragonballapi

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val personagemImagem = findViewById<ImageView>(R.id.personagemImg)
        val nomeTextView = findViewById<TextView>(R.id.nameTv)
        val kiTextView = findViewById<TextView>(R.id.kiTv)
        val racaTextView = findViewById<TextView>(R.id.raceTv)
        val generoTextView = findViewById<TextView>(R.id.genderTv)

        val id = intent.getStringExtra("ID_EXTRA")

        if (id != null) {
            val dbzApi = RetrofitHelper.getInstance().create(DbzApi::class.java)

            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = dbzApi.getPersonagem(id)
                    if (response.isSuccessful) {
                        val persona = response.body()
                        Log.d("Retorno da API: ", persona.toString())

                        withContext(Dispatchers.Main) {
                            // ⭐️ Usando o Glide para carregar a imagem da URL
                            Glide.with(this@ResultadoActivity)
                                .load(persona?.image)
                                .into(personagemImagem)
                            nomeTextView.text = "NOME: ${persona?.name}"
                            kiTextView.text = "KI: ${persona?.ki}"
                            racaTextView.text = "RACA: ${persona?.race}"
                            generoTextView.text = "GENERO: ${persona?.gender}"
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            nomeTextView.text = "Erro: ${response.code()}"
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        nomeTextView.text = "Ocorreu um erro: ${e.message}"
                    }
                }
            }
        } else {
            nomeTextView.text = "Personagem não encontrado."
        }
    }
}