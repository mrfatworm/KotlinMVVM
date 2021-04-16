package com.mrfatworm.kotlinmvvm

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Expense::class), version = 1)
abstract class ExpenseDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

}