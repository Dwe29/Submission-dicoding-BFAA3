package com.example.dicodingsubmission3.viewmodels

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.dicodingsubmission3.helper.ThemePreference
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application): ViewModel() {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val pref: ThemePreference = ThemePreference.getInstance(application.dataStore)

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkMode)
        }
    }
}