package com.shunli.receiveallbroadcast.broadlist

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.widget.Toast
import android.os.Bundle
import com.shunli.receiveallbroadcast.MainActivity
import java.lang.Exception

/**
 * a base receiver
 */
abstract class BaseBroadcastReceiver : BroadcastReceiver() {
    protected val TAG = "XReceiver"

    private var printer: Printer? = null

    private val intentFilter = IntentFilter()

    abstract fun registerActions(filter: IntentFilter)

    fun registerReceiver(context: Context) {
        registerActions(intentFilter)
        context.registerReceiver(this, intentFilter);
    }

    fun setPrinter(printer: Printer) {
        this.printer = printer
    }


    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val action: String = intent.action ?: "null action"
        val extrasString: String = getExtrasString(intent)
        Toast.makeText(context, "receive $action", Toast.LENGTH_SHORT).show();
        val print = "<font color='#FF0000'> Action: $action </font> , Extras: $extrasString "
        Log.e(TAG, print);
        printer?.log(print)
    }


    private fun getExtrasString(pIntent: Intent): String {
        var extrasString = ""
        val extras = pIntent.extras
        try {
            if (extras != null) {
                val keySet = extras.keySet()
                for (key in keySet) {
                    extrasString += try {
                        val extraValue = pIntent.extras!![key].toString()
                        "$key: $extraValue\n"
                    } catch (e: Exception) {
                        Log.d(TAG, "Exception 2 in getExtrasString(): $e")
                        """
     $key: Exception:${e.message}
     
     """.trimIndent()
                    }
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Exception in getExtrasString(): $e")
            extrasString += """
            Exception:${e.message}
            
            """.trimIndent()
        }
        Log.d(TAG, "extras=$extrasString")
        return extrasString
    }
}

interface Printer {
    fun log(str: String)
}