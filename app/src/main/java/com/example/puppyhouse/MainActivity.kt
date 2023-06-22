package com.example.puppyhouse

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyhouse.Detail.Companion.EXTRA_NAME
import com.example.puppyhouse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvPuppies: RecyclerView
    private val list = ArrayList<Puppy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        rvPuppies = binding.rvPuppies
        rvPuppies.layoutManager = layoutManager
        rvPuppies.setHasFixedSize(true)

        list.addAll(getListPuppy())
        showRecyclerList()
    }

    private fun getListPuppy(): ArrayList<Puppy> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_description)
        val dataImg = resources.obtainTypedArray(R.array.data_photo)
        val dataHeight = resources.getStringArray(R.array.data_height)
        val dataWeight = resources.getStringArray(R.array.data_weight)
        val dataLife = resources.getStringArray(R.array.data_life)
        val listData = ArrayList<Puppy>()

        for (i in dataName.indices) {
            val listPuppy = Puppy(
                dataName[i],
                dataDesc[i],
                dataImg.getResourceId(i, -1),
                dataHeight[i],
                dataWeight[i],
                dataLife[i]
            )
            listData.add(listPuppy)
        }
        return listData
    }

    private fun showRecyclerList() {
        val listPuppyAdapter = ListPuppyAdapter(list)
        binding.rvPuppies.adapter = listPuppyAdapter
        listPuppyAdapter.setOnItemClickCallback(object : ListPuppyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Puppy) {
                showSelectedPuppy(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_more -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSelectedPuppy(puppy: Puppy) {
        val intent = Intent(this, Detail::class.java)

        intent.putExtra(EXTRA_NAME, puppy)
        startActivity(intent)
    }
}
