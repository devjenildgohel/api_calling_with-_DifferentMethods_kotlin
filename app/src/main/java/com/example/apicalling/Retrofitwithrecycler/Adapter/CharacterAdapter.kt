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

    private var characterList : MutableList<CharacterModel> = mutableListOf()
    private var mCharacters : MutableList<CharacterModel> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent,false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCharacter = mCharacters[position]

        Glide.with(context).load(currentCharacter.images!!["lg"].toString()).into(holder.characterImage)
        holder.characterName.text = "Name : ${currentCharacter.name.toString()}"
        holder.characterSlug.text = "Slug : ${currentCharacter.slug.toString()}"
        holder.characterWork.text = "Work : ${currentCharacter.work!!["occupation"].toString()}"

    }

    override fun getItemCount(): Int {
        return mCharacters.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterList(characterItemList: MutableList<CharacterModel>){
        characterList.clear()
        characterList.addAll(characterItemList)
        mCharacters.addAll(characterList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: AppCompatImageView = itemView.findViewById(R.id.characterImageView)
        val characterWork: AppCompatTextView = itemView.findViewById(R.id.characterWorkTextView)!!
        val characterName: AppCompatTextView = itemView.findViewById(R.id.characterNameTextView)!!
        val characterSlug = itemView.findViewById<AppCompatTextView>(R.id.characterSlugTextView)!!
    }

}