package com.example.apod_nasaapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.apod_nasaapi.R
import com.example.apod_nasaapi.databinding.ActivityInputDateBinding
import com.example.apod_nasaapi.viewmodel.NasaViewModel

class InputDateActivity : AppCompatActivity() {

    private lateinit var mViewModel: NasaViewModel
    private lateinit var binding: ActivityInputDateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_date)

        mViewModel = ViewModelProvider(this).get(NasaViewModel::class.java)
        binding = ActivityInputDateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.btnSend.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putString("chave", binding.editTextDate.text.toString())
            intent.putExtras(bundle)
            startActivity(intent)
        }


    }
}
