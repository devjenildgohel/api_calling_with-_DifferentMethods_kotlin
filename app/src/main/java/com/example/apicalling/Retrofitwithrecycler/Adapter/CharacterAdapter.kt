package com.example.apicalling.Retrofitwithrecycler.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalling.R
import com.example.apicalling.Retrofitwithrecycler.CharacterModel

class CharacterAdapter(private val context : Context) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var characterList : MutableList<CharacterModel> = mutableListOf<CharacterModel>()
    var mCharacters : MutableList<CharacterModel> = mutableListOf<CharacterModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = mCharacters[position]

        Glide.with(context).load(currentCharacter.images!!.get("lg").toString()).into(holder.characterImage)
        holder.characterID.text = currentCharacter.id.toString()
        holder.characterName.text = currentCharacter.name.toString()
        holder.characterSlug.text = currentCharacter.slug.toString()

    }

    override fun getItemCount(): Int {
        return mCharacters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setValuteListItems(characterItemList: MutableList<CharacterModel>){
        characterList.clear()
        characterList.addAll(characterItemList)
        mCharacters.addAll(characterList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val characterImage = itemView.findViewById<AppCompatImageView>(R.id.characterImageView)
        val characterID = itemView.findViewById<AppCompatTextView>(R.id.characterIDTextView)
        val characterName = itemView.findViewById<AppCompatTextView>(R.id.characterNameTextView)
        val characterSlug = itemView.findViewById<AppCompatTextView>(R.id.characterSlugTextView)
    }

}