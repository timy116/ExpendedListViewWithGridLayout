package com.concourse.mobile.expandedlistviewwithgridlayoutdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.BaseExpandableListAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class ExpandableListViewAdapter(
    private val context: Context,
    private val groups: List<String>,
    private val items: List<List<String>>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return groups.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return 1
    }

    override fun getGroup(position: Int): Any {
        return groups[position]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return items[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return (groupPosition * 100 + childPosition).toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        p2: View?,
        p3: ViewGroup?
    ): View {
        val view = p2 ?: LayoutInflater
            .from(context)
            .inflate(R.layout.group_item, null)

        val imageView = view.findViewById<ImageView>(R.id.image)
        val textView = view.findViewById<TextView>(R.id.group_name)

        if (isExpanded) {
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
        }

        textView.text = groups[groupPosition]

        return view
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val list = items[p0]
        val view = p3 ?: LayoutInflater
            .from(context)
            .inflate(R.layout.gridview_layout, null)

        val gridView = view.findViewById<CustomGridView>(R.id.gridview)
        gridView.numColumns = 3
        gridView.verticalSpacing = 10
        gridView.horizontalSpacing = 10

        val adapter = GridViewAdapter(context, list)
        gridView.adapter = adapter

        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

}

class GridViewAdapter(
    private val context: Context,
    private val list: List<String>
) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(R.layout.gridview_item, null)

        val checkBox = view.findViewById<CheckBox>(R.id.checkbox)
        checkBox.text = list[position]

        return view
    }

}