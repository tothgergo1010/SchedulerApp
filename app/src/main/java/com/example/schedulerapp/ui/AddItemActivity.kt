package com.example.schedulerapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.schedulerapp.AppLocaleManager
import com.example.schedulerapp.R
import java.util.Calendar

class AddItemActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var btnPickDate: Button
    private lateinit var btnPickTime: Button
    private lateinit var btnAdd: Button
    private lateinit var btnCancel: Button

    private var selectedDate = ""
    private var selectedTime = ""

    override fun attachBaseContext(newBase: Context) {
        val localeManager = AppLocaleManager(newBase)
        val lang = localeManager.getLanguage()
        val context = localeManager.setLocale(newBase, lang)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        etTitle = findViewById(R.id.etTitle)
        btnPickDate = findViewById(R.id.btnPickDate)
        btnPickTime = findViewById(R.id.btnPickTime)
        btnAdd = findViewById(R.id.btnAdd)
        btnCancel = findViewById(R.id.btnCancel)

        btnPickDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, y, m, d ->
                selectedDate = "$y-${m+1}-$d"
                btnPickDate.text = selectedDate
            }, year, month, day).show()
        }

        btnPickTime.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, h, min ->
                selectedTime = String.format("%02d:%02d", h, min)
                btnPickTime.text = selectedTime
            }, hour, minute, true).show()
        }

        btnAdd.setOnClickListener {
            val title = etTitle.text.toString().trim()
            if (title.isNotEmpty() && selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
                val intent = Intent()
                intent.putExtra("title", title)
                intent.putExtra("date", selectedDate)
                intent.putExtra("time", selectedTime)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                etTitle.error = getString(R.string.error_fill_all)
            }
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
