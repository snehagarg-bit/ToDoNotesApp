package com.example.notesapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.Adapter.NotesAdapter
import com.example.notesapp.R
import com.example.notesapp.ViewModel.NotesViewModel
import com.example.notesapp.databinding.FragmentHomeFrgmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFrgment : Fragment() {

      val viewModel: NotesViewModel by viewModels()
    lateinit var binding:FragmentHomeFrgmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding=FragmentHomeFrgmentBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
         viewModel.getNotes().observe(viewLifecycleOwner,{notesList ->

             binding.recycleView.layoutManager=GridLayoutManager(requireContext(),2)
                 binding.recycleView.adapter= NotesAdapter(requireContext(),notesList)

         })

        binding.fab1.setOnClickListener{
            val insertFragment = InsertFragment()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, insertFragment)
                .commit()

        }


        return binding.root

        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.log_out,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout from Notes app ?")
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
                val googleSignInClient =
                    GoogleSignIn.getClient(requireContext(), gso)
                googleSignInClient.signOut()
                Toast.makeText(context,"Log out successfully", Toast.LENGTH_SHORT).show()
                val loginFragment = LoginFragment()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, loginFragment)
                    .commit()

            }
            .show()
        return super.onOptionsItemSelected(item)
    }


    }



