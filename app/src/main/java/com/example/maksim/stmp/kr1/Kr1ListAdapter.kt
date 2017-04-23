package com.example.maksim.stmp.kr1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.maksim.stmp.R


class Kr1ListAdapter(val items: List<GroupMemberInfo>, val context: Context) : BaseAdapter() {
    private class ViewHolder {
        internal var firstname: TextView? = null
        internal var lastname: TextView? = null
        internal var sex: TextView? = null
        internal var photo: ImageView? = null
    }

    override fun getItem(index: Int) = items[index]

    override fun getItemId(index: Int) = index.toLong()

    override fun getCount() = items.size

    override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(index)

        val view = convertView ?: {
            val view = LayoutInflater.from(context).inflate(R.layout.kr1_list_item, parent, false)
            val viewHolder = ViewHolder()
            viewHolder.firstname = view.findViewById(R.id.kr1_list_item_firstname) as TextView
            viewHolder.lastname = view.findViewById(R.id.kr1_list_item_lastname) as TextView
            viewHolder.sex = view.findViewById(R.id.kr1_list_item_sex) as TextView
            viewHolder.photo = view.findViewById(R.id.kr1_list_item_photo) as ImageView
            view.setTag(viewHolder)
            view
        }()

        val viewHolder = view.tag as? ViewHolder

        viewHolder?.firstname?.setText(item.firstname)
        viewHolder?.lastname?.setText(item.lastname)
        viewHolder?.sex?.setText(item.sex)

        setPhoto(item.photoId, viewHolder?.photo)

        return view
    }

    private fun setPhoto(photoId: String?, photoView: ImageView?) {
        val foundId = photoId?.let { context.resources.getIdentifier(photoId, "drawable", context.packageName) } ?: 0
        val id = if (foundId != 0) foundId else R.drawable.default_avatar
        photoView?.setImageResource(id)
    }
}