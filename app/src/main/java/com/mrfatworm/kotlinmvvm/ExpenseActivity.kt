package com.mrfatworm.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_expense.*
import kotlinx.android.synthetic.main.list_expense.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class ExpenseActivity : AppCompatActivity() {

    lateinit var database: ExpenseDatabase

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

        database = Room.databaseBuilder(this,
                ExpenseDatabase::class.java, "expense.db")
                .build()

        CoroutineScope(Dispatchers.IO).launch {
            val expenses = database.expenseDao().getAll()
            Log.d("Size",expenses.size.toString())

            val adapter = object :RecyclerView.Adapter<ExpenseViewHolder>(){
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): ExpenseViewHolder {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_expense, parent, false)
                    return ExpenseViewHolder(view)
                }

                override fun getItemCount(): Int {
                    return expenses.size
                }

                override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
                    val exp = expenses.get(position)
                    holder.date.text = exp.date
                    holder.info.text = exp.info
                    holder.cost.text = exp.amount.toString()
                }
            }
            runOnUiThread{
                recyclerview_expense.setHasFixedSize(true)
                recyclerview_expense.layoutManager = LinearLayoutManager(this@ExpenseActivity)
                recyclerview_expense.adapter = adapter
            }
        }



        fab.setOnClickListener {
            Executors.newSingleThreadExecutor().execute(){
                for (expense in expenseData) {
                    database.expenseDao().add(expense)
                }
            }
        }
    }
    class ExpenseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date = itemView.tv_date
        val info = itemView.tv_info
        val cost = itemView.tv_cost
    }
}