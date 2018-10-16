package com.rs.app.launcher

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.rs.app.R
import com.rs.deliveries.presentation.screens.list.DeliveryListNavigator
import dagger.android.AndroidInjection
import javax.inject.Inject


class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var mainActivityNavigator: DeliveryListNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setupView()
        afterSimulatingLoad {
            mainActivityNavigator.navigateTo()
            finish()
        }
    }

    private fun setupView() {
        setContentView(R.layout.activity_launcher)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun afterSimulatingLoad(action: () -> Unit) = Handler(mainLooper).postDelayed(action, 600)
}
