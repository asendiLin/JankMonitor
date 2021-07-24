package com.sendi.jankmonitor

interface OnJankListener {

    fun onJank(stackList: List<String>)

}