package com.example.expensetracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "expense_table")
data class Expense(

        @PrimaryKey(autoGenerate = true)
        val expenseId: Int,

        @ColumnInfo(name = "expense_title")
        val expenseTitle: String,

        @ColumnInfo(name = "expense_price")
        val expensePrice: Int,

        @ColumnInfo(name = "expense_type")
        val expenseType: Int,

        @ColumnInfo(name = "currency_type")
        val currencyType: Int

)