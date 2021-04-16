package com.mrfatworm.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ExpenseActivity : AppCompatActivity() {

    val expenseData = arrayListOf<Expense>(
            Expense("2021/04/15", "lunch", 120),
            Expense("2021/04/15", "dinner", 80),
            Expense("2021/04/15", "shampoo", 180),
            Expense("2021/04/15", "Steam: Valheim", 318),
            Expense("2021/04/16", "Steam: The forest", 128)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        val database = Room.databaseBuilder(this,
                ExpenseDatabase::class.java, "expense.db")
                .build()
        Executors.newSingleThreadExecutor().execute(){
            for (expense in expenseData) {
                database.expenseDao().add(expense)
            }
        }

    }
}