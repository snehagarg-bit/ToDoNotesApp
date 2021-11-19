package com.example.notesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.notesapp.Model.Notes
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EditFragment : Fragment() {


    val viewModel: NotesViewModel by viewModels()
    var id: Long? = null
    var inputTitle:String? = null
    var inputNote:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_edit, container, false)
        setHasOptionsMenu(true)
        requireActivity().title = "Edit Notes"
        id = (arguments?.getLong("id"))
        inputTitle=arguments?.getString("title")
        inputNote=arguments?.getString("note")

        Log.d("check for data","title:$inputTitle note:$inputNote id:$id")

        val oldTitle:EditText=view.findViewById(R.id.update_title)
        oldTitle.setText(inputTitle)

        val oldNote:EditText=view.findViewById(R.id.update_note)
        oldNote.setText(inputNote)

        val btn:FloatingActionButton=view.findViewById(R.id.fab3)
        btn.setOnClickListener{

            updateNote(it,id as Long,view)
            val insertFragment = HomeFrgment()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, insertFragment)
                .commit()
        }




        return view
    }

    private fun updateNote(it: View?, id: Long, view: View) {

        val newTitle:EditText= view.findViewById(R.id.update_title)
        val newNote:EditText=view.findViewById(R.id.update_note)
        var data = Notes(id, newTitle.text.toString(), newNote.text.toString())
        Log.d("data", data.toString())
        viewModel.updateNote(data)
        Toast.makeText(context,"Notes Updated Successfully", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            val bottomSheet:BottomSheetDialog= BottomSheetDialog(requireContext(),R.style.BottonSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            val textYes: TextView? =bottomSheet.findViewById(R.id.dialog_yes)
            val textNo:TextView?=bottomSheet.findViewById(R.id.dialog_no)
            bottomSheet.show()

            textYes?.setOnClickListener{
                viewModel.deleteNote(id!!)
                bottomSheet.dismiss()
                Toast.makeText(context,"Notes deleted Successfully", Toast.LENGTH_SHORT).show()
                val insertFragment = HomeFrgment()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, insertFragment)
                    .commit()
            }


            textNo?.setOnClickListener{
                bottomSheet.dismiss()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}