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
import android.content.pm.PackageManager
import android.provider.ContactsContract
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

class MaterialActivity : AppCompatActivity() {

    companion object{
        val REQUEST_CONTACTS = 100
    }
    val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        val permission =ActivityCompat.checkSelfPermission(this, READ_CONTACTS)
        if(permission !=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(READ_CONTACTS, WRITE_CONTACTS),
                REQUEST_CONTACTS)
        }else{
            readContacts()
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
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

    private fun readContacts() {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null)

        cursor?.run {
            while (cursor.moveToNext()){
               val id = cursor.getInt(
                   cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))

                contacts.add(Contact(name, ""))
            }
            setupRecyclerView()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CONTACTS -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts()
                }else{
                    AlertDialog.Builder(this)
                        .setMessage("Must grant Contact Permission to show datum")
                        .setPositiveButton("OK", null)
                        .show()
                }

            }
        }
    }

    class ContactViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name = view.tv_name
        val phone = view.tv_phone

    }
}