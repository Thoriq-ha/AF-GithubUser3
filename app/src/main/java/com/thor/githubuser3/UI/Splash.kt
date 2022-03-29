package com.thor.githubuser3.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thor.githubuser3.databinding.ActivitySplashBinding


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        animateSplash()
    }

    private fun animateSplash() {
        binding.imageView.alpha = 0f
        binding.imageView.animate().setDuration(3000).alpha(1f)

        binding.title.alpha = 0f
        binding.title.animate().setDuration(3000).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}