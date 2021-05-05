package com.example.expensetracker.data

import androidx.room.*
import com.example.expensetracker.model.Expense

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addExpense(expense: Expense)

    @Query("SELECT * FROM expense_table ORDER BY expenseId DESC")
    fun getAllExpense():List<Expense>

    @Query("SELECT * FROM expense_table WHERE expenseId = :expenseId")
    fun getExpense(expenseId: Int): Expense

    @Delete
    fun deleteExpense(expense: Expense)

}