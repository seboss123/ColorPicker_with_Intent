package com.cmechem.colorsaveload

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_intent.*
import java.io.File


class IntentActivity : AppCompatActivity() {
    var redVal = 127
    var greenVal = 127
    var blueVal = 127
    var alpha = 255
    private var colorName = ""

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

            R.id.saveButton -> {
                redVal = redSeekBar.progress
                greenVal = greenSeekBar.progress
                blueVal = blueSeekBar.progress
                val colorTextView = findViewById<TextView>(R.id.colorName2)
                colorName = colorTextView.text.toString()
                saveText("$colorName,$redVal,$greenVal,$blueVal\n")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveText(text: String) {
        val path = filesDir
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "Colors.txt")

        file.appendText(text)

    }

    private fun addfragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = FragmentMain()

        fragmentTransaction.replace(R.id.fragmentView, fragment)
        fragmentTransaction.commit()

    }

    fun finishIntent(view: View) {
        val intent = intent
        intent.putExtra("Red", redVal)
        intent.putExtra("Green", greenVal)
        intent.putExtra("Blue", blueVal)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
