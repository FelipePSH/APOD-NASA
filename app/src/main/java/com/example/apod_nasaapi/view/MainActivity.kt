package com.example.apod_nasaapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.apod_nasaapi.R
import com.example.apod_nasaapi.databinding.ActivityInputDateBinding
import com.example.apod_nasaapi.databinding.ActivityMainBinding
import com.example.apod_nasaapi.viewmodel.NasaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: NasaViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mViewModel = ViewModelProvider(this).get(NasaViewModel::class.java)

        val date: String

        intent.extras?.let {
            date = it.getString("chave").toString()
            mViewModel.getDataFromUserDate(date)
        }



        observe()
    }

    private fun observe() {
        mViewModel.nasaResponse.observe(this, Observer { response ->
            binding.textViewTitle.text = response.title
            binding.textViewDate.text = response.date
            binding.textViewExplanation.text = response.explanation

            Glide.with(this)
                .load(response.url)
                .into(binding.imgViewApod)

        })

        mViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}
