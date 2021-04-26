package org.d3if4037.hitungbmi.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if4037.hitungbmi.R
import org.d3if4037.hitungbmi.data.HitungBmi
import org.d3if4037.hitungbmi.databinding.ItemHistoriBinding
import org.d3if4037.hitungbmi.db.BmiEntity
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : RecyclerView.Adapter<HistoriAdapter.ViewHolder>() {
    private val data = mutableListOf<BmiEntity>()

    fun updateData(newData: List<BmiEntity>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class  ViewHolder(
        private val binding: ItemHistoriBinding
    ): RecyclerView.ViewHolder(binding.root){

        private val  dateFormartter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID")
        )

        fun bind(item: BmiEntity) = with(binding){
            tanggalTv.text = dateFormartter.format(Date(item.tanggal))

            val hasilBmi = HitungBmi.hitung(item)
            kategoriTv.text = hasilBmi.kategori.toString()
            bmiTv.text = root.context.getString(R.string.bmi_x,
                hasilBmi.bmi)
            beratTv.text = root.context.getString(R.string.berat_x, item.berat)
            tinggiTv.text = root.context.getString(R.string.tinggi_x, item.tinggi)

            val stringRes = if (item.isMale) R.string.pria else R.string.wanita
            genderTv.text = root.context.getString(stringRes)
        }
    }
}