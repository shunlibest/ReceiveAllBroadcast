package com.shunli.receiveallbroadcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.shunli.receiveallbroadcast.broadlist.*

class MainActivity : AppCompatActivity(), Printer {
    private val receiverList: ArrayList<BaseBroadcastReceiver> = ArrayList()

    private val powerReceiver: BaseBroadcastReceiver = PowerReceiver()
    private val networkReceiver: BaseBroadcastReceiver = NetworkReceiver()
    private val screenReceiver: BaseBroadcastReceiver = ScreenReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiverList.add(powerReceiver)
        receiverList.add(networkReceiver)
        receiverList.add(screenReceiver)
        registerAllReceiver()

        findViewById<TextView>(R.id.tv_print).movementMethod =
            ScrollingMovementMethod.getInstance();
    }


    private fun registerAllReceiver() {
        receiverList.forEach {
            it.setPrinter(this@MainActivity)
            it.registerReceiver(this@MainActivity)
        }
    }

    override fun log(str: String) {
        findViewById<TextView>(R.id.tv_print).append(Html.fromHtml(str))
        findViewById<TextView>(R.id.tv_print).append("\n")
    }


}

