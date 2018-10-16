package com.rs.deliveries.presentation.arch

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.hannesdorfmann.mosby.mvp.MvpActivity
import com.hannesdorfmann.mosby.mvp.MvpPresenter
import com.hannesdorfmann.mosby.mvp.MvpView
import dagger.android.AndroidInjection

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    fun setupToolBarWithHomeEnabled(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}

