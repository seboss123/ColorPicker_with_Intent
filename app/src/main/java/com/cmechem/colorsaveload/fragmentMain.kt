package com.cmechem.colorsaveload

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.File
import java.io.Serializable


data class Color(val colorName: String, val red: String, val green: String, val blue: String) :
    Serializable


class fragmentMain : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var colorData = mutableListOf<Color>()


    fun log(value: String) {
        Log.e("Test", value)
    }

    fun createDate(colors: List<String>) {
        colorData = mutableListOf()

        for (data in colors) {
            var splitData = data.split(",")
            colorData.add(Color(splitData[0], splitData[1], splitData[2], splitData[3]))

        }
        Log.e("Color list", colorData.toString())


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
            log(inputAsString.size.toString())
            if (inputAsString.isNotEmpty()) {
                Log.e("Text File", inputAsString[inputAsString.size - 1])
            }
            Log.e("This is a test", "test")

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

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

    fun testFun(view: View, color: Color) {
        Log.e("ZZZZZZZZZZZ", color.toString())
        val intent = Intent(view.context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        val bundle = bundleOf(
            "colorName" to color.colorName,
            "red" to color.red,
            "green" to color.green,
            "blue" to color.blue

        )
        intent.putExtras(bundle)
        Log.e("ZZZZZZZZZZZ", bundle.toString())

        view.context.startActivity(intent)
    }


    companion object {
        fun newInstance(): fragmentMain = fragmentMain()
    }

}