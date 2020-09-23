package br.uea.mypay.tutoriais.demomylaundry.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.Toast
import br.uea.mypay.tutoriais.demomylaundry.R
import br.uea.mypay.tutoriais.demomylaundry.models.Servico
import kotlinx.android.synthetic.main.item_preco_geral.view.*


class TabelaPrecosAdapter(
    private val context: Context,
    private val listaServicos: List<Servico>
) : BaseAdapter() {

    companion object {
        data class ViewHolder(val view: View) {
            val txtItemNome = view.txtItemNome
            val txtPreco = view.txtPreco
            val btnEditar = view.imgbtEditarServico
        }
    }

    // para saber calcula as posições e montar a lista, o ListView obtém primeiramente a contagem
    override fun getCount(): Int = listaServicos.size

    // p
    override fun getItem(position: Int): Servico = listaServicos[position]

    override fun getItemId(position: Int): Long = position.toLong()


    // o ListView percorre a lista de servicos criando as views de cada um individualmente
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val servico = listaServicos[position]
        val holder: ViewHolder
        val row: View

        println(position.toString())

        if (convertView == null) {
            row = LayoutInflater.from(context).inflate(R.layout.item_preco_geral, parent, false)
            holder = ViewHolder(row)
            row.tag = holder
        } else {
            row = convertView
            holder = convertView.tag as ViewHolder
        }
        holder.txtItemNome.text = servico.nome
        holder.txtPreco.text = servico.preco.toString()
        holder.btnEditar.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.preco))
            builder.setMessage(context.getString(R.string.digite_preco))
            val input = EditText(context)
            input.setText(servico.preco.toString())
            input.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
            builder.setView(input)
            builder.setPositiveButton(context.getString(R.string.ok),
                DialogInterface.OnClickListener { dialog, which ->
                    try {
                        servico.preco = input.text.toString().toFloat()
                    } catch (e: NumberFormatException) {
                        Toast.makeText(context, "Informe um valor", Toast.LENGTH_SHORT).show()
                    }
                    notifyDataSetChanged()
                })
            builder.setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }
        return row
    }
}