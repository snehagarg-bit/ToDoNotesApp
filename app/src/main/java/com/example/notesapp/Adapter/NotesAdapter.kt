package com.example.notesapp.Adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemViewBinding
import com.example.notesapp.ui.EditFragment


class NotesAdapter(val requireContext: Context, val notesList:List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {


    class notesViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data = notesList[position]
        holder.binding.title.text = data.title
        holder.binding.note.text = data.note


        holder.binding.root.setOnClickListener {

            val bundle = Bundle()
            bundle.putLong("id",data.id.toLong())
            bundle.putString("title",data.title.toString())
            bundle.putString("note",data.note.toString())
            val activity=it!!.context as AppCompatActivity
            val editFragment= EditFragment()
            editFragment.arguments=bundle
            Log.d("check the block", bundle.toString())
            activity.supportFragmentManager?.beginTransaction()?.
            replace(R.id.fragmentContainerView, editFragment)?.commit()



        }
    }


    override fun getItemCount() = notesList.size

}










