package org.d3if4037.hitungbmi.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import org.d3if4037.hitungbmi.R
import org.d3if4037.hitungbmi.databinding.FragmentHitungBinding

class HitungFragment : Fragment() {

    private lateinit var binding: FragmentHitungBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHitungBinding.inflate(layoutInflater,
                container, false)
        binding.button.setOnClickListener { hitungBMI() }
        binding.saranbutton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                R.id.action_hitungFragment_to_saranFragment
            )
        }
        return binding.root
    }
        private fun hitungBMI() {
            val berat = binding.isiBerat.text.toString()
            if (TextUtils.isEmpty(berat)) {
                Toast.makeText(context, R.string.berat_invalid, Toast.LENGTH_LONG).show()
                return
            }
            val tinggi = binding.isiTinggi.text.toString()
            if (TextUtils.isEmpty(tinggi)) {
                Toast.makeText(context, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
                return
            }
            val tinggiCm = tinggi.toFloat() / 100

            val selectedId = binding.rg.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(context, R.string.gender_invalid, Toast.LENGTH_LONG).show()
                return
            }
            val isMale = selectedId == R.id.priaRb
            val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
            val kategori = getKategori(bmi, isMale)

            binding.bmiTextView.text = getString(R.string.bmi_x, bmi)
            binding.kategoriTextView.text = getString(R.string.kategori_x, kategori)
            binding.saranbutton.visibility = View.VISIBLE
        }

        private fun getKategori(bmi: Float, ismale: Boolean): String {
            val stringRes = if (ismale) {
                when {
                    bmi < 20.5 -> R.string.kurus
                    bmi >= 27.0 -> R.string.gemuk
                    else -> R.string.ideal
                }
            } else {
                when {
                    bmi < 18.5 -> R.string.kurus
                    bmi >= 25.0 -> R.string.gemuk
                    else -> R.string.ideal
                }
            }
            return getString(stringRes)
        }
    }


