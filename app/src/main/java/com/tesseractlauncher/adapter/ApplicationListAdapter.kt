package com.tesseractlauncher.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tesseractlauncher.R
import com.tesseractlauncher.interfaces.OnItemClickListener
import com.tesseractlauncher.models.ApplicationListModel


class ApplicationListAdapter(
    private val list: MutableList<ApplicationListModel>,
    private val context: Context, private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ApplicationListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.row_application_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        list.get(position)?.let { holder.bind(it) }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: ApplicationListModel) {
            val txtAppName = itemView.findViewById(R.id.txtAppName) as TextView
            val txtPkgName = itemView.findViewById(R.id.txtPkgName) as TextView
            val imgIcon = itemView.findViewById(R.id.imgIcon) as ImageView
            val rlMain = itemView.findViewById(R.id.rlMain) as RelativeLayout
            txtAppName.text = model.appName
            txtPkgName.text = model.appPkgName
            imgIcon.setImageDrawable(model.icon)
            rlMain.setOnClickListener {
                onItemClickListener.onItemClickListener(adapterPosition)
            }
        }
    }


}