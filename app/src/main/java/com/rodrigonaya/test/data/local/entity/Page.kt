package com.rodrigonaya.test.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Page(
    val htmlContent: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
