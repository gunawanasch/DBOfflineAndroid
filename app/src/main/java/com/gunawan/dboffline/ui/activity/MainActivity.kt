package com.gunawan.dboffline.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gunawan.dboffline.R
import com.gunawan.dboffline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tbMain.title = getString(R.string.app_name)

        binding.btnCrud.setOnClickListener {
            intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }

        binding.btnOnlineToOffline.setOnClickListener {
            intent = Intent(this, CustomerInfoActivity::class.java)
            startActivity(intent)
        }
    }

}