package com.dima.financeapp.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.dima.financeapp.R
import com.dima.financeapp.ui.main.communication.MainFragmentCommunicationInterface
import com.dima.financeapp.ui.main.communication.NavFragmentCallback
import com.dima.financeapp.ui.main.nav.NavFragment

class MainActivity : AppCompatActivity(), MainFragmentCommunicationInterface {

    private val navFragment = NavFragment()

    private var navFragmentCallback: NavFragmentCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavFragmentCallback()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_main_container, navFragment)
                .commit()
        }
    }

    private fun initNavFragmentCallback() {
        navFragmentCallback = navFragment
    }

    override fun displayAddNewBillFragment() {
        navFragmentCallback?.displayAddNewBillFragment()
    }

    override fun onBackPressed() {
        val fragmentManager: FragmentManager = supportFragmentManager
        for (frag in fragmentManager.fragments) {
            if (frag.isVisible) {
                val childFm: FragmentManager = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                    return
                }
            }
        }
        super.onBackPressed()
    }
}