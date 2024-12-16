package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var recyclerViewAdapter: CustomAdapter
    private var articles = Clothes.cloths

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerViewAdapter = CustomAdapter(articles)
        binding.recyclerViewMainRV.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewMainRV.adapter = recyclerViewAdapter
        binding.recyclerViewMainRV.setHasFixedSize(true)
        recyclerViewAdapter.setOnArticleClickListener(object :
            CustomAdapter.OnArticleClickListener {
            override fun onArticleClick(cloth: Cloth, position: Int) {
                val intent = Intent(this@SecondActivity,DetailsActivity::class.java)
                intent.putExtra("article", cloth)
                startActivity(intent)
            }

        })

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        recyclerViewAdapter.notifyItemRangeRemoved(0,recyclerViewAdapter.itemCount)
        recyclerViewAdapter.updateAdapter(articles)
        recyclerViewAdapter.notifyDataSetChanged()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}