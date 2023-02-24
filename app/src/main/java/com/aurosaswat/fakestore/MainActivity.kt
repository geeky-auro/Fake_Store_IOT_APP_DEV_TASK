package com.aurosaswat.fakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        We're using GlobalScope.launch to launch a coroutine to make the API request.
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val serviceGenerator = ServiceGenerator().buildService(ApiService::class.java)
                val call = serviceGenerator.getPosts()
                withContext(Dispatchers.Main) {
                    call.enqueue(object : Callback<MutableList<PostModel>> {
                        override fun onResponse(
                            call: Call<MutableList<PostModel>>,
                            response: Response<MutableList<PostModel>>
                        ) {
                            if (response.isSuccessful) {
                                Log.e(".MainActivity", "${response.body().toString()}")
                                recycler_view.apply {
                                    layoutManager = LinearLayoutManager(this@MainActivity)
                                    adapter =
                                        response.body()?.let { FakeStoreRecyclerViewAdapter(it) }
                                }
                            }
                        }
                        override fun onFailure(call: Call<MutableList<PostModel>>, t: Throwable) {
                            t.printStackTrace()
                            Log.e(".MainActivity", t.message.toString())
                        }
                    })
                }
            } catch (e: Exception) {
                Log.e(".MainActivity", "${e.message}")
            }
        }
        /**
         *  GlobalScope.launch is used to launch a coroutine on the IO dispatcher, which is optimized for network and disk I/O.
         *  Within the coroutine, the apiService is used to fetch data from the API, and the response is processed on the main thread using the withContext function and the Main dispatcher.
         *  This ensures that any UI updates or interactions with UI elements are done on the main thread.
         * */
    }
}