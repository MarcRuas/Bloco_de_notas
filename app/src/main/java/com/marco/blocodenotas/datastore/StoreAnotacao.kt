package com.marco.blocodenotas.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class StoreAnotacao(private val context: Context){

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configuration")
        val anotacaoKey = stringPreferencesKey("anotacao")
    }

    val getAnotacao: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[anotacaoKey] ?: ""
        }

    suspend fun salvarAnotacao(anotacao: String){
        context.dataStore.edit {prefereces ->
            prefereces[anotacaoKey] = anotacao
        }
    }
}