package com.mrfatworm.kotlinmvvm

import android.os.AsyncTask

class AsyncTask(): AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String {
        TODO("Not yet implemented")
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}