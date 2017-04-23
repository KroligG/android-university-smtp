package com.example.maksim.stmp.kr1

import android.content.Context
import com.example.maksim.stmp.R
import com.example.maksim.stmp.util.json
import com.google.gson.internal.`$Gson$Types`.newParameterizedTypeWithOwner

class GroupMemberService(val context: Context) {
    fun getGroupMemberInfo(): List<GroupMemberInfo> {
        val type = newParameterizedTypeWithOwner(null, List::class.java, GroupMemberInfo::class.java)
        return json(context.resources, R.raw.group_members_data, type)
//        return listOf(GroupMemberInfo("a", "b", "Male", null), GroupMemberInfo("c", "d", "Female", "me"))
    }
}

