package com.example.customadapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import de.hdodenhof.circleimageview.CircleImageView

data class MyAdapter(private val context: Context, private val arrayList: ArrayList<User>): ArrayAdapter<User>(context,
    R.layout.eachrow, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.eachrow, null)

        val viewname = view.findViewById<TextView>(R.id.user_name)
        val viewcontact = view.findViewById<TextView>(R.id.user_number)
        val viewimage = view.findViewById<CircleImageView>(R.id.profile_image)

        viewname.text = arrayList[position].name
        viewcontact.text = arrayList[position].contact
        viewimage.setImageResource(arrayList[position].imageId)

        view.setOnClickListener {
            Toast.makeText(context, "Clicked: ${arrayList[position].name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("name", arrayList[position].name)
            intent.putExtra("contact", arrayList[position].contact)
            context.startActivity(intent)
        }

        return view
    }
}

