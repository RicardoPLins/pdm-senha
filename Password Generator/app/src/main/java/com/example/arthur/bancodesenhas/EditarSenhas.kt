package com.example.arthur.bancodesenhas
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class EditarSenhas : AppCompatActivity() {
    private lateinit var nomeSenha:EditText
    private lateinit var tamanho:SeekBar
    private lateinit var caracterEspecial:CheckBox
    private lateinit var numero:CheckBox
    private lateinit var letraMaiuscula:CheckBox
    private lateinit var btnExcluir:Button
    private lateinit var btnCancelar:Button
    private lateinit var btnAlterar:Button
    private lateinit var tamanhoSenha:TextView
    private lateinit var listView: ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_senhas)

        nomeSenha= findViewById(R.id.nomeSenha)
        tamanho=findViewById(R.id.seekBar)
        caracterEspecial=findViewById(R.id.caixaCaracterEspecial)
        numero=findViewById(R.id.caixaNumero)
        letraMaiuscula=findViewById(R.id.caixaLetraMaiuscula)
        btnExcluir=findViewById(R.id.botaoExcluir)
        btnCancelar=findViewById(R.id.botaoCancelar)
        btnAlterar= findViewById(R.id.botaoAlterar)
        tamanhoSenha=findViewById(R.id.tamanhoSenhaEdi)
        listView = findViewById(R.id.listaSenhas)


        val senhasList: MutableList<Senhas> = mutableListOf()
        val senhaAdapter = SenhaAuxiliar(this, senhasList)



        val position = listView.selectedItemPosition



        val senhaEdi = intent.getParcelableExtra<Senhas>("senha1")

        if (senhaEdi != null) {
            val descricaoEditable = Editable.Factory.getInstance().newEditable(senhaEdi.senha1)
            nomeSenha.text = descricaoEditable
            tamanho.progress = senhaEdi.tamanho
            if (senhaEdi.maiusculo)
                letraMaiuscula.isChecked = true
            if (senhaEdi.numero)
                numero.isChecked = true
            if (senhaEdi.especial)
                caracterEspecial.isChecked = true

            tamanho.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    tamanhoSenha.text = progress.toString()
                }
                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }
                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            btnAlterar.setOnClickListener {
                val senhaAlterada = editarSenha(senhaEdi)
                // Atualiza a lista de senhas após a edição
                senhasList[position] = senhaAlterada

                senhaAdapter.notifyDataSetChanged()
            }

            btnExcluir.setOnClickListener {
                senhasList.remove(senhaEdi)
                senhaAdapter.notifyDataSetChanged()
                finish()
            }

            this.btnCancelar.setOnClickListener {
                finish()
            }
        } else {

        }
    }

    fun editarSenha(novaSenha: Senhas): Senhas {
        novaSenha.maiusculo = letraMaiuscula.isChecked
        novaSenha.numero = numero.isChecked
        novaSenha.especial = caracterEspecial.isChecked
        novaSenha.gerarSenhaAleatoria(tamanho.progress)
        novaSenha.nomeS = nomeSenha.text.toString()
        novaSenha.tamanho = tamanho.progress
        return novaSenha
    }
}
