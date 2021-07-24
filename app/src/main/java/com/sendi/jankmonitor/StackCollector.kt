package com.sendi.jankmonitor

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper


// todo:抽象一下
class StackCollector {

    companion object {

    }

    private val collectHandler: Handler
    private val collectHandlerThread: HandlerThread = HandlerThread("StackCollectorThread")
    private var hasStart = false
    private val stackElementsMap = HashMap<Long, String>()
    private val mMainThread: Thread = Looper.getMainLooper().thread
    private val collectTask: Runnable = Runnable {
        collect()
        postDelayCollectTask()
    }
    constructor() {
        collectHandlerThread.start()
        collectHandler = Handler(collectHandlerThread.looper)
    }

    private fun collect() {
        val stringBuilder = StringBuilder()

        for (stackTraceElement in mMainThread.stackTrace) {
            stringBuilder
                    .append(stackTraceElement.toString())
                    .append("\n")
        }
        //todo:stackElementsMap清除最大的限制
        stackElementsMap[System.currentTimeMillis()] = stringBuilder.toString()
    }

    fun startCollect() {
        if (hasStart) {
            return
        }
        hasStart = true
        collectHandler.removeCallbacks(collectTask)
        postDelayCollectTask()
    }

    private fun postDelayCollectTask() {
        collectHandler.postDelayed(collectTask, JankMonitor.collectDelayTime())
    }

    fun stop() {
        hasStart = false
        collectHandler.removeCallbacks(collectTask)
    }

    fun getStacks(start: Long, end: Long): List<String> {
        val stackInfoList = ArrayList<String>()
        val keys = stackElementsMap.keys
        for (key in keys) {
            if (key in start..end) {
                stackInfoList.add(stackElementsMap[key] ?: "")
            }
        }
        return stackInfoList
    }
}