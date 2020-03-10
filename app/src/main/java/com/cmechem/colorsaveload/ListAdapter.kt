package com.cmechem.colorsaveload

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    private val list: List<Color>, private val onClickListener: (View, Color) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val color: Color = list[position]
        holder.itemView.setOnClickListener { view ->
            onClickListener.invoke(view, color)
        }
        holder.bind(color)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)) {
    private var mTitleView: TextView? = null
    private var mYearView: TextView? = null
    private var colorView: View? = null


    init {


        mTitleView = itemView.findViewById(R.id.list_title)
        mYearView = itemView.findViewById(R.id.list_description)
        colorView = itemView.findViewById(R.id.colorView)

    }

    fun bind(movie: Color) {
        mTitleView?.text = movie.colorName
        mYearView?.text = movie.red
        colorView?.setBackgroundColor(
            android.graphics.Color.rgb(
                movie.red.toInt(),
                movie.green.toInt(),
                movie.blue.toInt()
            )
        )

    }


}