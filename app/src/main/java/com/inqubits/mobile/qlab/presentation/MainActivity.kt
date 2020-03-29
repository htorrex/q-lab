package com.inqubits.mobile.qlab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.inqubits.mobile.qlab.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToFragment(MoviesFragment.newInstance(Bundle()))
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_content, fragment).commit()
    }
}
