package br.uea.mypay.tutoriais.demomylaundry.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.uea.mypay.tutoriais.demomylaundry.R
import kotlinx.android.synthetic.main.activity_cadastro_manual.*

class CadastroManualActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_manual)
        supportActionBar?.title = getString(R.string.cadastro)
        supportActionBar?.subtitle = getString(R.string.manual)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btCadastrarManual.setOnClickListener { confirmaAlteraValor() }
    }

    fun confirmaAlteraValor() {
        val alertDialog = AlertDialog.Builder(this@CadastroManualActivity).create()
        alertDialog.setTitle(getString(R.string.alterar_preco))
        alertDialog.setMessage(getString(R.string.alterar_tabela_precos))
        alertDialog.setButton(
            AlertDialog.BUTTON_POSITIVE, getString(R.string.ok)
        ) { dialog, which ->
            dialog.dismiss()
            startActivity(Intent(this, AlterarPrecosActivity::class.java))
            finish()
        }
        alertDialog.setButton(
            AlertDialog.BUTTON_NEGATIVE, getString(R.string.nao)
        ) { dialog, which ->
            dialog.dismiss()
            startActivity(Intent(this, AbrirCaixaActivity::class.java))
            finish()
        }
        alertDialog.show()
    }
}