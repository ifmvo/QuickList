package com.ifmvo.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(R.id.flContainer, SampleListFragment())
    }

    private fun replaceFragment(contentLayoutId: Int, fragment: Fragment?) {
        if (fragment == null) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(contentLayoutId, fragment, fragment.javaClass.name)
        transaction.commit()
    }
}
