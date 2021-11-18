package com.shunli.receiveallbroadcast.broadlist

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import android.os.Bundle
import java.lang.Exception


class NetworkReceiver : BaseBroadcastReceiver() {

    override fun registerActions(filter: IntentFilter) {
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)

    }
}