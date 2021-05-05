package com.example.expensetracker.repository

import androidx.lifecycle.LiveData
import com.example.expensetracker.data.ExpenseDao
import com.example.expensetracker.model.Expense

class ExpenseRepository(private  val expenseDao: ExpenseDao) {

    fun getAllExpense(): List<Expense>{
        return expenseDao.getAllExpense()
    }

    fun getExpense(expenseId: Int): Expense{
        return expenseDao.getExpense(expenseId)
    }

    fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }

    fun deleteExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }

}