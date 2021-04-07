package com.mrfatworm.kotlinmvvm

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.list_contact.view.*
import android.Manifest.permission.*
import androidx.core.app.ActivityCompat

class MaterialActivity : AppCompatActivity() {

    val REQUEST_EXTERNAL_STORAGE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        ActivityCompat.requestPermissions(this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
            REQUEST_EXTERNAL_STORAGE)

        val contacts = listOf<Contact>(
            Contact("Jeff", "0919950227"),
            Contact("Sally", "0919960722"),
            Contact("Lance", "0919971209"),
            Contact("Amy", "0919670810"))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        recyclerview_contact.setHasFixedSize(true)
        recyclerview_contact.layoutManager = LinearLayoutManager(this)

        val adapter = object : RecyclerView.Adapter<ContactViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
                val view = layoutInflater.inflate(R.layout.list_contact, parent, false)
                return ContactViewHolder(view)
            }

            override fun getItemCount(): Int {
                return contacts.size
            }

            override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
                holder.name.text = contacts[position].name
                holder.phone.text = contacts[position].phone
            }
        }

        recyclerview_contact.adapter = adapter
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.tv_name
        val phone = view.tv_phone

    }
}