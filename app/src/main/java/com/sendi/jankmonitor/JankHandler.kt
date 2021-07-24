package com.sendi.jankmonitor

import android.os.Handler
import android.os.Looper
import android.os.Message

class JankHandler(looper: Looper) : Handler(looper){

    companion object{
        const val MSG_LOG_START = 1
        const val MSG_LOG_END = 2

    }
    private var lastStartTime = 0L
    private val stackCollector = StackCollector()

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when(msg.what) {
            MSG_LOG_START -> {
                lastStartTime = System.currentTimeMillis()
                stackCollector.startCollect()
            }
            MSG_LOG_END -> {
                val current = System.currentTimeMillis()
                val gap = current - lastStartTime
                stackCollector.stop()
                if (gap >= JankMonitor.gap()) {
                    onJank(lastStartTime, current)
                }
            }
        }
    }

    private fun onJank(startTime: Long, endTime: Long) {
        val stackList = stackCollector.getStacks(startTime, endTime)
        //todo: 1.回调出去
        // 2.启动一个线程，进行保存/上报
    }
}