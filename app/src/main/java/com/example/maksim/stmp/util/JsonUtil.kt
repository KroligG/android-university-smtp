package com.example.maksim.stmp.util

import android.content.res.Resources
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

fun <T> json(resources: Resources, id: Int, type: Type): T {
    val reader = BufferedReader(InputStreamReader(resources.openRawResource(id), "UTF-8"))
    return GsonBuilder().create().fromJson(reader, type)
}