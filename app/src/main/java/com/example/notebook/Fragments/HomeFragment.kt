package com.example.notebook.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notebook.Adapters.NoteAdapter
import com.example.notebook.R
import com.example.notebook.ViewModel.NotesViewModel
import com.example.notebook.databinding.FragmentHomeBinding
import com.example.notebook.model.Notes


class HomeFragment : Fragment(),MenuProvider{

    lateinit var  binding:FragmentHomeBinding
    lateinit var  toolbar: Toolbar
    lateinit var  adapter: NoteAdapter
    var  oldnoteList = ArrayList<Notes>()
    private  val notesViewModel :NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
         // new way to show menu
       //Activity()?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)

        // making common layput manager
        // Inflate the layout for this fragment
        // we do work under here for home Fragment
        toolbar = binding.toolbar2
       // way to declare toolbar in fragment
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)

       notesViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {

          binding.homeRecyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
          adapter = NoteAdapter(requireContext(), it as ArrayList<Notes>)
           binding.homeRecyclerview.adapter = adapter


})


        binding.filterFinance.setOnClickListener {
            val resultData = notesViewModel.getFinanceNotes("Finance")
          resultData.observe(viewLifecycleOwner, Observer {
              adapter = NoteAdapter(requireContext(), it as ArrayList<Notes>)
              binding.homeRecyclerview.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
              binding.homeRecyclerview.adapter= adapter
          })
        }
        binding.filterSchedule.setOnClickListener {
            val resultData= notesViewModel.getScheduleNotes("Schedule")
            resultData.observe(viewLifecycleOwner, Observer {
                adapter = NoteAdapter(requireContext(),it as ArrayList<Notes>)
                binding.homeRecyclerview.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.homeRecyclerview.adapter= adapter
            })

        }
        binding.filterTodoList.setOnClickListener {

            val resultData= notesViewModel.getScheduleNotes("To-Do")
            resultData.observe(viewLifecycleOwner, Observer {
                adapter = NoteAdapter(requireContext(),it as ArrayList<Notes>)
                binding.homeRecyclerview.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.homeRecyclerview.adapter= adapter

            })
        }
        binding.filterQuotes.setOnClickListener {
            val resultData = notesViewModel.getQuotesNotes("Quotes")
            resultData.observe(viewLifecycleOwner, Observer {
                adapter = NoteAdapter(requireContext(),it as ArrayList<Notes>)
                binding.homeRecyclerview.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.homeRecyclerview.adapter= adapter

            })

        }
        binding.filterBasic.setOnClickListener {
            val resultData = notesViewModel.getBasicNotes("Basic")
            resultData.observe(viewLifecycleOwner, Observer {
                adapter = NoteAdapter(requireContext(),it as ArrayList<Notes>)
                binding.homeRecyclerview.layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.homeRecyclerview.adapter= adapter

            })


        }
        binding.filterAll.setOnClickListener{

            notesViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {

                binding.homeRecyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                adapter = NoteAdapter(requireContext(), it as ArrayList<Notes>)
                binding.homeRecyclerview.adapter = adapter
            })


        }


        binding.addNote.setOnClickListener {
          Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_creteFragment)
            //Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_editFragment)
        }


        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.home_menu_items,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {




        return  true

    }


}