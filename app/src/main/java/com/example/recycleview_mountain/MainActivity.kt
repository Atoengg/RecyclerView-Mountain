package com.example.recycleview_mountain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mountainAdapter: MountainAdapter? = null
    private var mountainList: MutableList<Mountain> = mutableListOf()

    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        mountainAdapter = MountainAdapter(this, mountainList)
        recyclerView!!.adapter = mountainAdapter

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0) {
                        loadMoreItems()
                    }
                }
            }
        })

        siapkanData()
    }


    private fun siapkanData() {
        mountainList.addAll(
            listOf(
                Mountain("Gunung Arjuana", R.drawable.arjuna),
                Mountain("Gunung Argopuro", R.drawable.arogopuro),
                Mountain("Gunung Buthak", R.drawable.buthak),
                Mountain("Gunung Raung", R.drawable.raung),
                Mountain("Gunung Sumeru", R.drawable.sumeru),
                Mountain("Galeri 1", R.drawable.teknik),
                Mountain("Galeri 2", R.drawable.penanggungan),
            )
        )
        mountainAdapter?.notifyDataSetChanged()
    }

    private fun loadMoreItems() {
        isLoading = true
        currentPage += 1

        // Fake delay to simulate network call
        Handler().postDelayed({
            mountainList.addAll(
                listOf(
                    Mountain("Gunung Arjuana", R.drawable.arjuna),
                    Mountain("Gunung Argopuro", R.drawable.arogopuro),
                    Mountain("Gunung Buthak", R.drawable.buthak),
                    Mountain("Gunung Raung", R.drawable.raung),
                    Mountain("Gunung Sumeru", R.drawable.sumeru),
                    Mountain("Galeri 1", R.drawable.teknik),
                    Mountain("Galeri 2", R.drawable.penanggungan),
                )
            )
            mountainAdapter?.notifyDataSetChanged()

            isLoading = false
            if (currentPage == 5) {
                isLastPage = true
            }
        }, 2000)
    }
}
