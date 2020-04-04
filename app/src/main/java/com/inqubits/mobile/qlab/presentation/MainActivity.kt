package com.inqubits.mobile.qlab.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.inqubits.mobile.qlab.R

class MainActivity : AppCompatActivity() {

    private val movieFragmentTag = MovieFragment.javaClass.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main) // set the base view

        // TODO: add this to a utility function
        var fragment = supportFragmentManager.findFragmentByTag(movieFragmentTag)
        fragment = (if (fragment == null) {
            MovieFragment.newInstance(Bundle())
        } else {
            fragment as MovieFragment
        })

        if (!fragment.isAdded) {
            navigateToFragment(fragment)
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.main_content, fragment, movieFragmentTag).commit()
    }
}
