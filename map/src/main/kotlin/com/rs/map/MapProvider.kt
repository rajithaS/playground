package com.rs.map

interface MapProvider<T> {
    fun getView(): T
}
