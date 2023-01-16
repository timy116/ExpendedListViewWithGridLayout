package com.concourse.mobile.expandedlistviewwithgridlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val expandableListView = findViewById<ExpandableListView>(R.id.expanded_listview)

        val groups = listOf("僅顯示以下單號：", "僅顯示以下儲位：", "僅顯示以下箱號：")
        val items = listOf(
            listOf(
                "HUOD123456",
                "HUOD123457",
                "HUOD123458",
                "HUOD123459",
                "HUOD123460",
                "HUOD123451"
            ),
            listOf("K5ACA01", "K5ACA01", "K5ACA01", "K5ACA01", "K5ACA01", "K5ACA01"),
            listOf("13", "6315", "7582", "1", "15", "103"),
        )

        val adapter = ExpandableListViewAdapter(this, groups, items)

        expandableListView.setAdapter(adapter)
        expandableListView.expandGroup(0)
        expandableListView.setOnGroupClickListener { parent, v, groupPosition, id ->
            false
        }
    }
}