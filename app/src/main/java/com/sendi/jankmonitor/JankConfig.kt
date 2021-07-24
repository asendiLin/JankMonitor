package com.sendi.jankmonitor

class JankConfig {
    var jankGap: Long = 0L
    var maxSize = 0
    var collectDelayTime = 0L
    val jankListenerList = ArrayList<OnJankListener>()


    class Builder {
        private val config = JankConfig()

        fun jankGap(jankGap: Long): Builder {
            config.jankGap = jankGap
            return this
        }

        fun maxStackInfoSize(maxSize: Int): Builder {
            config.maxSize = maxSize
            return this
        }

        fun collectDelayTime(delayTime: Long): Builder {
            config.collectDelayTime = delayTime
            return this
        }

        fun addJankListener(listener: OnJankListener): Builder {
            config.jankListenerList.add(listener)
            return this
        }

        fun build(): JankConfig{
            return config
        }
    }
}