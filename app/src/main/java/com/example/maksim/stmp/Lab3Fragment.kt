package com.example.maksim.stmp

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import java.util.concurrent.TimeUnit


class Lab3Fragment : Fragment() {
    private lateinit var log: TextView
    private lateinit var logScroll: ScrollView
    private lateinit var progress: ProgressBar
    private lateinit var btn: Button
    private var runningTask: MyTask? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lab3_fragment, container, false)

        log = view.findViewById(R.id.lab3_log) as TextView
        logScroll = view.findViewById(R.id.lab3_log_scroll) as ScrollView
        progress = view.findViewById(R.id.lab3_progress) as ProgressBar
        btn = view.findViewById(R.id.lab3_start_button) as Button

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn.setOnClickListener(this::startAsyncTask)

        log.setMovementMethod(ScrollingMovementMethod())
    }

    fun startAsyncTask(view: View?) {
        runningTask?.cancel(true)
        runningTask = MyTask(10)
        runningTask?.execute()
    }

    fun appendLog(text: String) {
        log.setText("${log.text}\n$text")
        logScroll.scrollTo(0, log.bottom)
    }

    inner class MyTask(val length: Int) : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            super.onPreExecute()
            appendLog("Begin")
            progress.max = this.length
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val i = values[0]!!
            appendLog("Current value: $i/$length")
            progress.progress = i
        }

        override fun onCancelled() {
            appendLog("Cancelled")
        }

        override fun doInBackground(vararg params: Unit): Unit {
            (0..length).forEach {
                if (isCancelled) {
                    return
                }
                publishProgress(it)
                TimeUnit.MILLISECONDS.sleep(1000)
            }
        }

        override fun onPostExecute(result: Unit) {
            super.onPostExecute(result)
            appendLog("End")
            progress.progress = length
        }
    }
}