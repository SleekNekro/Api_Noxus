package com.example.api_noxus

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ChampAdapter(context: Context, resource: Int, objects: ArrayList<Champ>) :
    ArrayAdapter<Champ>(
        context,
        resource,
        objects
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val champ = getItem(position)
        Log.w("CHAMP", champ.toString())

        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.lst_item,parent,false)

        val champName = view.findViewById<TextView>(R.id.name_role)
        val champTitle = view.findViewById<TextView>(R.id.title)
        val champDiff = view.findViewById<TextView>(R.id.diff)
        val champImg = view.findViewById<ImageView>(R.id.img)

        champName.text=champ?.name?.replaceFirst(champ.name[0].toString(),
            champ.name[0].toString().uppercase())
        champTitle.text=champ?.title?.replaceFirst(champ.title[0].toString(),
            champ.title[0].toString().uppercase())
        champDiff.text=champ?.diff?.replaceFirst(champ.diff[0].toString(),
            champ.diff[0].toString().uppercase())

        Glide.with(context)
            .load(champ?.img)
            .into(champImg)

        return view
    }
}