package com.sendi.jankmonitor

import android.os.Looper
import android.util.Printer

class JankPrinter() : Printer {
    companion object {
        const val TAG = "JankPrinter"
        const val START_PREFIX = ">"
        const val END_PREFIX = "<"
    }
    private val jankHandler = JankHandler(Looper.getMainLooper())

    override fun println(x: String?) {
        when {
            x?.startsWith(START_PREFIX) == true -> {
                val msg = jankHandler.obtainMessage(JankHandler.MSG_LOG_START)
                jankHandler.sendMessage(msg)
            }
            x?.startsWith(END_PREFIX) == true -> {
                val msg = jankHandler.obtainMessage(JankHandler.MSG_LOG_START)
                jankHandler.sendMessage(msg)
            }
            else -> {

            }
        }
    }
}