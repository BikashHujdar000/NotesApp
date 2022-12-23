package com.example.notebook.Fragments

import android.os.Build
import android.os.Bundle

import android.view.*
import android.view.View.VISIBLE

import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation

import com.example.notebook.AppConstants
import com.example.notebook.Dailog
import com.example.notebook.R
import com.example.notebook.ViewModel.NotesViewModel
import com.example.notebook.databinding.FragmentCreteBinding
import com.example.notebook.model.Notes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CreteFragment : Fragment(), MenuProvider {
    lateinit var binding: FragmentCreteBinding
    lateinit var toolbar: Toolbar
    private val noteViewModel: NotesViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // setting up menu
        activity?.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding = FragmentCreteBinding.inflate(layoutInflater, container, false)

        toolbar = binding.toolbar
        // way to declare toolbar in fragment
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

       // setting the date and time initialization
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date_time = LocalDateTime.now().format(formatter)
        binding.dateTime.text= date_time



        // work from here
        binding.editCategory.setOnClickListener {
            var dailog = Dailog()
            dailog.show(parentFragmentManager, "dialog")
            setFragmentResultListener(AppConstants.REQUEST_KEY) { requestKey, bundle ->
                val inputResult = bundle.getString(AppConstants.BUNDLE_KEY)
                binding.priority.text = inputResult
                binding.priority.visibility = VISIBLE

            }
        }



        return binding.root
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.create_menu, menu)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.save) {
            createNotes()
           // Toast.makeText(requireContext(), "Holy shit it is saved", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(requireView()).navigate(R.id.action_creteFragment_to_homeFragment)
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotes() {
        val dateTime= binding.dateTime.text.toString()
        val title = binding.editTitle.text.toString()
        val notes = binding.editNote.text.toString()
        val category = binding.priority.text.toString()
        val currentNotes= Notes(null,title,notes,dateTime,category)
        noteViewModel.insertNote(currentNotes)
    }


}