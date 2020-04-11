package com.cmechem.colorsaveload

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.io.Serializable


data class Color(val colorName: String, val red: String, val green: String, val blue: String) :
    Serializable


class FragmentMain : Fragment() {
    private var colorData = mutableListOf<Color>()


    private fun createDate(colors: List<String>) {
        colorData = mutableListOf()

        for (data in colors) {
            val splitData = data.split(",")
            colorData.add(Color(splitData[0], splitData[1], splitData[2], splitData[3]))

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val path = context!!.filesDir
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val file = File(letDirectory, "Colors.txt")
        if (file.exists()) {
            val inputAsString = file.readLines()
            createDate(inputAsString)

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerView node initialized here
        list_recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView

            adapter = ListAdapter(colorData, onClickListener = { view, Color ->
                testFun(view, Color)
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun testFun(view: View, color: Color) {
        if (activity != null) {
            val showColor = activity?.findViewById<View>(R.id.showColor)
            val redSeek = activity?.findViewById<SeekBar>(R.id.redSeekBar)
            val greenSeek = activity?.findViewById<SeekBar>(R.id.greenSeekBar)
            val blueSeek = activity?.findViewById<SeekBar>(R.id.blueSeekBar)
            showColor?.setBackgroundColor(
                android.graphics.Color.rgb(
                    color.red.toInt(),
                    color.green.toInt(),
                    color.blue.toInt()
                )
            )
            redSeek?.setProgress(color.red.toInt(), true)
            greenSeek?.setProgress(color.green.toInt(), true)
            blueSeek?.setProgress(color.blue.toInt(), true)

        }

    }


    companion object {
        fun newInstance(): FragmentMain = FragmentMain()
    }

}