package com.example.lab6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab6.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val item = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

        binding.btnToast.setOnClickListener {
            showToast("預設 Toast")
        }

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(it, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕") {
                    showToast("已回應")
                }.show()
        }

        binding.btnDialog1.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("按鈕式 AlertDialog")
                .setMessage("AlertDialog 內容")
                .setNeutralButton("左按鈕") { dialogInterface, which ->
                    showToast("左按鈕")
                }.setNegativeButton("中按鈕") { dialogInterface, which ->
                    showToast("中按鈕")
                }.setPositiveButton("右按鈕") { dialogInterface, which ->
                    showToast("右按鈕")
                }.show()
        }

        binding.btnDialog2.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("列表式 AlertDialog")
                .setItems(item) { dialogInterface, i ->
                    showToast("你選的是${item[i]}")
                }.show()
        }

        binding.btnDialog3.setOnClickListener {
            var position = 0
            AlertDialog.Builder(this)
                .setTitle("單選式 AlertDialog")
                .setSingleChoiceItems(item, 0) { dialogInterface, i ->
                    position = i
                }.setPositiveButton("確定") { dialog, which ->
                    showToast("你選的是${item[position]}")
                }.show()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
