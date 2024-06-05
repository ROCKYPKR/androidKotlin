package com.example.kt_p.datastorepractice
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class DataStoreSample(val context: Context) {
    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
  companion object{
      var USER_NAME = stringPreferencesKey(name = "user_name")
      var AGE=intPreferencesKey(name = "age")
  }
    suspend fun setUserNameAndAge(userName:String,age:Int){
        context.dataStore.edit {
            it[USER_NAME]=userName
            it[AGE]=age
        }

    }

    fun getAge()=context.dataStore.data.map {
        it[AGE]?:0
    }

    fun getUserNameNow()=context.dataStore.data.map {
        it[USER_NAME]?:""
    }

    val EXAMPLE_COUNTER = intPreferencesKey("example_counter")
    val exampleCounterFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[EXAMPLE_COUNTER] ?: 0
        }


    var userName:Flow<String> = context.dataStore.data
        .catch {
            if(it is IOException){

            }else{
                throw Exception()
            }
        }
        .map{
        it[USER_NAME]?:""
    }


}
