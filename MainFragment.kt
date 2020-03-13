package com.example.movielibrary.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielibrary.R
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        movieRecycler.layoutManager = LinearLayoutManager(activity)
        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://feeds.citibikenyc.com/stations/stations.json/").build()

        val postsApi = retrofit.create(INetworkAPI::class.java)

        var response = postsApi.getAllPosts()

        response.
        observeOn(AndroidSchedulers.mainThread()).
        subscribeOn(IoScheduler()).
        subscribe {
            movieRecycler.adapter = DataRecyclerAdapterAdapter(it, activity)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         viewModel =
             ViewModelProviders.of(this)
                 .get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
