package com.hao.composedemo01.utils

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import android.view.Choreographer

object ThreadUtils {

    val handler : Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun runOnUiThread(run : Runnable){
        handler.post(run)
    }
    fun runOnUiThreadDelay(run : Runnable, delay : Long){
        handler.postDelayed(run, delay)
    }

    fun remove(run : Runnable){
        handler.removeCallbacks(run)
    }

    @JvmStatic
    fun isUiThread() = Looper.getMainLooper().thread == Thread.currentThread()

    fun runOnWorkThread(run : Runnable){
        AsyncTask.execute(run)
    }

    fun addIdleRunnable(run : Runnable) : MessageQueue.IdleHandler {
        return IdleRunnableImpl(run).also {
            Looper.myQueue().addIdleHandler(it)
        }
    }

    fun postFrameCallback(run : Choreographer.FrameCallback, delayMillis : Long = 0){
        Choreographer.getInstance().postFrameCallbackDelayed(run, delayMillis)
    }

    class IdleRunnableImpl(val run : Runnable) : MessageQueue.IdleHandler{
        override fun queueIdle(): Boolean {
            run.run()
            return false
        }
    }
}