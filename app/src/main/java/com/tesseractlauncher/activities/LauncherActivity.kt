package com.tesseractlauncher.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesseractlauncher.R
import com.tesseractlauncher.adapter.ApplicationListAdapter
import com.tesseractlauncher.interfaces.OnItemClickListener
import com.tesseractlauncher.models.ApplicationListModel
import com.tesseractlauncher.utility.Utility
import kotlinx.android.synthetic.main.activity_main.*

class LauncherActivity : AppCompatActivity(), OnItemClickListener {

    private var applicationList = mutableListOf<ApplicationListModel>()
    private var filterList = mutableListOf<ApplicationListModel>()
    var adapter: ApplicationListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init() {
        applicationList.clear()
        filterList.clear()
        applicationList = Utility.getApplicationList(this) as MutableList<ApplicationListModel>
        filterList.addAll(applicationList)

        recyclerApplicationList.layoutManager = LinearLayoutManager(this)
        recyclerApplicationList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = ApplicationListAdapter(filterList, this, this)
        recyclerApplicationList.adapter = adapter

        autoTextAppSearch.addTextChangedListener(object : TextWatcher {
            @SuppressLint("DefaultLocale")
            override fun afterTextChanged(editable: Editable?) {
                filterList = mutableListOf()

                for (model in applicationList) {
                    if (model.appName.toLowerCase().contains(editable.toString().toLowerCase())) {
                        filterList.add(model)
                    }
                }
                adapter = ApplicationListAdapter(filterList, this@LauncherActivity, this@LauncherActivity)
                recyclerApplicationList.adapter = adapter
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onItemClickListener(position: Int) {
        val intent = packageManager.getLaunchIntentForPackage(filterList[position].appPkgName)
        startActivity(intent)
    }
}