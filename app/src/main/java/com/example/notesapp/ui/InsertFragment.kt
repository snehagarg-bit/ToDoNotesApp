package com.example.notesapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentInsertBinding


class
InsertFragment : Fragment() {


    lateinit var binding: FragmentInsertBinding
    val viewModel:NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInsertBinding.inflate(layoutInflater, container, false)
        requireActivity().title = "Add new notes"

        binding.fab2.setOnClickListener {

            createNotes(it)
            val insertFragment = HomeFrgment()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, insertFragment)
                .commit()
        }

        return binding.root
    }

    private fun createNotes(it: View) {

        val title=binding.insertTitle.text.toString()
        val note=binding.insertNote.text.toString()
        val data= Notes(0,title,note)

        viewModel.insertNote(data)
        Toast.makeText(context,"Notes Created Successfully",Toast.LENGTH_SHORT).show()

    }



}