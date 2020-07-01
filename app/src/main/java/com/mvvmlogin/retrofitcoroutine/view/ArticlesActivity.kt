package com.chirag.retrofitcoroutine.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chirag.retrofitcoroutine.R
import com.chirag.retrofitcoroutine.adapters.ArticlesAdapter
import com.chirag.retrofitcoroutine.util.CustomProgressDialog
import com.chirag.retrofitcoroutine.util.Utility
import com.chirag.retrofitcoroutine.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_second.*

class ArticlesActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val articlesAdapter = ArticlesAdapter()
    lateinit var customProgressDialog: CustomProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        customProgressDialog = CustomProgressDialog(this)
        // initObservables()

        if (Utility.isInternetConnected(this)) {
            observeLiveData()
        } else {
            Toast.makeText(
                this,
                "No internet found. Showing cached list in the view",
                Toast.LENGTH_LONG
            ).show()
        }
        initializeList()
    }

    private fun observeLiveData() {
        customProgressDialog.show()
        //observe live data emitted by view model
        viewModel.userPagedList.observe(this, Observer {
            it?.let {
                if (customProgressDialog.isShowing) {
                    customProgressDialog.dismiss()
                }
                articlesAdapter.submitList(it)
            }
        })
    }

    private fun initializeList() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = articlesAdapter
    }
}