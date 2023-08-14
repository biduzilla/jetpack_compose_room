package com.example.composeroomretrofit.ui

import com.example.composeroomretrofit.ContactEvent
import com.example.composeroomretrofit.SortType
import com.example.composeroomretrofit.model.Contact

data class ContactState(
    val contacts:List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName:String = "",
    val phoneNumber:String = "",
    val isAddContact:Boolean = false,
    val sortType: SortType = SortType.FIRST_NAME
)