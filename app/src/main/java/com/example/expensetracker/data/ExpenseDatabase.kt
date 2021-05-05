package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expensetracker.model.Expense


@Database(entities =  [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase: RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object{
        @Volatile
        private var instance: ExpenseDatabase? = null

        fun getDatabase(context: Context):ExpenseDatabase{

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,
                        ExpenseDatabase::class.java,
                        "expense_table"
                    ).allowMainThreadQueries()
                        .build()
                }
                return instance as ExpenseDatabase
        }
    }
}