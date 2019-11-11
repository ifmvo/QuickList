package com.ifmvo.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = SampleListFragment()
        replaceFragment(R.id.flContainer, fragment)

//        mBtn.setOnClickListener {
//            fragment.godAction()
//        }
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
