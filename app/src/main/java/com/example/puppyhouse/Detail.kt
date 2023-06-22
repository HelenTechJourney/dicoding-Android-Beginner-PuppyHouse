package com.example.puppyhouse

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.puppyhouse.databinding.ActivityDetailBinding

class Detail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar

        val puppy = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_NAME, Puppy::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_NAME)
        }
        actionbar!!.title = puppy?.name
        actionbar.setDisplayHomeAsUpEnabled(true)

        val imgPhoto = binding.imgItemPhoto
        val tvName = binding.tvItemName
        val tvDescription = binding.tvItemDescription
        val tvHeight = binding.puppiesHeight
        val tvWeight = binding.puppiesWeight
        val tvLife = binding.lifeExpectancy

        tvName.text = puppy?.name
        tvDescription.text = puppy?.description
        imgPhoto.setImageResource(puppy?.photo!!)
        tvHeight.text = puppy.puppies_height
        tvWeight.text = puppy.puppies_weight
        tvLife.text = puppy.life_expectancy
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
    }
}
