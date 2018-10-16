package com.rs.deliveries.presentation.arch

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby.mvp.MvpView

fun <V : MvpView> MvpBasePresenter<V>.ifViewAttached(action: (view: V) -> Unit) = view?.let(action)
