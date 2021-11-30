package com.example.dicodingsubmission3.ui.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.dicodingsubmission3.databinding.ActivityThemeBinding
import com.example.dicodingsubmission3.helper.ViewModelFactory
import com.example.dicodingsubmission3.viewmodels.ThemeViewModel

class ThemeActivity : AppCompatActivity() {
    private lateinit var viewModel: ThemeViewModel
    private lateinit var binding: ActivityThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = obtainViewModel(this as AppCompatActivity)
        title = "Theme"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        darkMode()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun darkMode() {
        binding.switchTheme.apply {
            viewModel.getThemeSettings().observe(this@ThemeActivity, {
                if (it) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    this.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    this.isChecked = false
                }
            })
            setOnCheckedChangeListener { _: CompoundButton?, isCheck: Boolean ->
                viewModel.saveThemeSetting(isCheck)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): ThemeViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ThemeViewModel::class.java]
    }

}