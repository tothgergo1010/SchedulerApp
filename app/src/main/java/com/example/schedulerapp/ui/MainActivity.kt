package com.example.schedulerapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schedulerapp.AppLocaleManager
import com.example.schedulerapp.R
import com.example.schedulerapp.model.ScheduleItem
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: ScheduleAdapter
    private lateinit var btnLanguageSwitch: Button
    private lateinit var localeManager: AppLocaleManager

    override fun attachBaseContext(newBase: android.content.Context) {
        localeManager = AppLocaleManager(newBase)
        val lang = localeManager.getLanguage()
        val context = localeManager.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)
        btnLanguageSwitch = findViewById(R.id.btnLanguageSwitch)

        adapter = ScheduleAdapter(mutableListOf())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnLanguageSwitch.setOnClickListener {
            val currentLang = localeManager.getLanguage()
            val newLang = if (currentLang == "hu") "en" else "hu"
            localeManager.setLocale(this, newLang)
            recreate()
        }

        fabAdd.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val title = data?.getStringExtra("title") ?: ""
            val date = data?.getStringExtra("date") ?: ""
            val time = data?.getStringExtra("time") ?: ""
            adapter.addItem(ScheduleItem(title, date, time))
        }
    }
}
