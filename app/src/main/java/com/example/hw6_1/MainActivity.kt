package com.example.hw6_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.hw6_1.core.base.BaseActivity
import com.example.hw6_1.databinding.ActivityMainBinding
import com.example.hw6_1.presentation.main.MainFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflaterViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController(R.id.nav_host_fragment).navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}