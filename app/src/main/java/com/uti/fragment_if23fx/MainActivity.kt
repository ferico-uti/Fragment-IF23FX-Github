package com.uti.fragment_if23fx

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uti.fragment_if23fx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    definisikan variabel "menu"
//    var menu = "1"
    lateinit var menu : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        definisikan "binding"
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        panggil fungsi "openFragment1"
        openFragment1()

//        buat event
//        event "img_menu1"
        binding.imgMenu1.setOnClickListener {
//            buka "Menu1Fragment"
            openFragment1()
        }

//        event "img_menu2"
        binding.imgMenu2.setOnClickListener {
            menu = "2"

            supportFragmentManager.beginTransaction().replace(R.id.frm_content, Menu2Fragment())
                .commit()
        }

//        event "img_menu3"
        binding.imgMenu3.setOnClickListener {
            menu = "3"

//            buat variabel fragment
            val fragment = Menu3Fragment()
//            buat variabel bundle
            val bundle = Bundle()
//            kirim parameter bundle
            bundle.putString("content3","Ini Contoh Fragment 3 Lagi Dong")
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction().replace(R.id.frm_content, fragment)
                .commit()
        }

//        buat method untuk tombol "back"
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                jika menu = "2" atau menu = "3"
                if(menu == "2" || menu == "3")
                {
//                    panggil method "openFragment1"
                    openFragment1()
                }
//                    jika menu = "21"
                    else if(menu == "21")
                {
//                        panggil method "imgMenu2"
                        binding.imgMenu2.performClick()
                }
//                jika menu = "1"
                else
                {
//                    tutup aplikasi
                    finish()
                }
            }

        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //    buat fungsi untuk buka "Menu1Fragment"
    fun openFragment1() {
        menu = "1"

        supportFragmentManager.beginTransaction().replace(R.id.frm_content, Menu1Fragment())
            .commit()
    }
}