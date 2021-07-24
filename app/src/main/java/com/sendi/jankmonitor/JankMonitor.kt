package com.sendi.jankmonitor

import android.os.Looper

object JankMonitor {
    private const val JANK_TIME_GAP = 500L //大于等于这个阀值，表示发生卡顿
    private const val COLLECT_DELAY_TIME = 128L // 收集任务的延迟时间
    private const val STACK_MAX_SIZE = 16 // 收集任务的延迟时间
    private var config: JankConfig? = null

    fun config(config: JankConfig): JankMonitor {
        this.config = config
        return this
    }

    fun install() {
        Looper.getMainLooper().setMessageLogging(JankPrinter())
    }

    fun gap(): Long {
        return config?.jankGap ?: JANK_TIME_GAP
    }

    fun maxStackInfoSize(): Int {
        return config?.maxSize ?: STACK_MAX_SIZE
    }

    fun collectDelayTime(): Long {
        return config?.collectDelayTime ?: COLLECT_DELAY_TIME
    }

    fun jankListeners(): List<OnJankListener> {
        return config?.jankListenerList ?: emptyList()
    }
}