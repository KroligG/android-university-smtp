package com.example.maksim.stmp.kr1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.maksim.stmp.R


class Kr1Fragment : Fragment() {
    private lateinit var listView: ListView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.kr1_fragment, container, false)

        listView = view.findViewById(R.id.kr1_list) as ListView

        initListView()

        return view
    }

    private fun initListView() {
        val groupMemberInfo = GroupMemberService(context).getGroupMemberInfo()
        val adapter = Kr1ListAdapter(groupMemberInfo, context)
        listView.adapter = adapter
    }

}