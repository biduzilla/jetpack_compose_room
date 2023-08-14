package com.example.composeroomretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.composeroomretrofit.data.ContactDatabase
import com.example.composeroomretrofit.ui.ContactScreen
import com.example.composeroomretrofit.ui.ContactViewmodel
import com.example.composeroomretrofit.ui.theme.ComposeRoomRetrofitTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.db"
        ).build()
    }

    private val viewModel by viewModels<ContactViewmodel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewmodel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRoomRetrofitTheme {
                val state by viewModel.state.collectAsState()
                ContactScreen(state = state, onEvent =viewModel::onEvent)
            }
        }
    }
}
