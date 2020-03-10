package com.cmechem.colorsaveload

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_intent.*
import android.graphics.Color


class IntentActivity : AppCompatActivity() {
    var redVal = 127
    var greenVal = 127
    var blueVal = 127
    var alpha = 255
    var colorName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val redSeek = findViewById<SeekBar>(R.id.redSeekBar)
        val greenSeek = findViewById<SeekBar>(R.id.greenSeekBar)
        val blueSeek = findViewById<SeekBar>(R.id.blueSeekBar)
        redSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                redVal = progress
                redValue.text = redVal.toString()
                showColor.setBackgroundColor(Color.argb(alpha, redVal, greenVal, blueVal))

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        greenSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                greenVal = progress
                greenValue.text = greenVal.toString()
                showColor.setBackgroundColor(Color.argb(alpha, redVal, greenVal, blueVal))

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        blueSeek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blueVal = progress
                blueValue.text = blueVal.toString()
                showColor.setBackgroundColor(Color.argb(alpha, redVal, greenVal, blueVal))

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.intenttoolbarmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loadButton -> {
                addfragment()
            }
        }
            return super.onOptionsItemSelected(item)
    }

    fun addfragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = fragmentMain()

        fragmentTransaction.replace(R.id.fragmentView, fragment)
        fragmentTransaction.commit()

    }

    fun finishIntent(view:View){
        val intent= intent
        intent.putExtra("Color","Red")
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
