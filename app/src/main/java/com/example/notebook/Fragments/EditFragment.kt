package com.example.notebook.Fragments

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.fontResource
import androidx.core.view.MenuProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notebook.AppConstants
import com.example.notebook.Dailog
import com.example.notebook.R
import com.example.notebook.ViewModel.NotesViewModel
import com.example.notebook.databinding.BottomsheetDailogBinding
import com.example.notebook.databinding.FragmentEditBinding
import com.example.notebook.model.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class EditFragment : Fragment(),MenuProvider{
    lateinit var binding: FragmentEditBinding
    val dataTransfer by navArgs<EditFragmentArgs>()
    private val notesViewModel: NotesViewModel by viewModels()
    lateinit var toolbar: Toolbar



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        activity?.addMenuProvider(this,viewLifecycleOwner,Lifecycle.State.RESUMED)

        binding = FragmentEditBinding.inflate(layoutInflater, container, false)

        //setting up toolbar
        toolbar = binding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)


        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val date_time = LocalDateTime.now().format(formatter)

        binding.editTitle.setText(dataTransfer.newData.title)
        binding.editNote.setText(dataTransfer.newData.notes)
        binding.dateTime.setText(date_time)
        binding.priority.setText(dataTransfer.newData.category)

      binding.editCategory.setOnClickListener {
          val dailog = Dailog()
          dailog.show(parentFragmentManager, "dialog")
          setFragmentResultListener(AppConstants.REQUEST_KEY) { requestKey, bundle ->
              val inputResult = bundle.getString(AppConstants.BUNDLE_KEY)
              binding.priority.text = inputResult
          }

      }
        
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
 menuInflater.inflate(R.menu.menu,menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
      when(menuItem.itemId)
      {
          R.id.update-> {
              updateNotes()
              Toast.makeText(requireContext(), "Note Updated Successfully", Toast.LENGTH_SHORT).show()
          }
          R.id.delete->{
              bottomSheetDisplay()
              Toast.makeText(requireContext(), "Note Deleted Successfully", Toast.LENGTH_SHORT).show()

          }


      }
        return  true
    }

    private fun bottomSheetDisplay() {
        val botomSheet :BottomSheetDialog = BottomSheetDialog(requireContext())
        botomSheet.setContentView(R.layout.bottomsheet_dailog)
        botomSheet.findViewById<TextView>(R.id.yes)?.setOnClickListener {
            dataTransfer.newData.id?.let { it1 -> notesViewModel.deleteNote(it1.toInt()) }
            botomSheet.dismiss()
            Navigation.findNavController(requireView()).navigate(R.id.action_editFragment_to_homeFragment)
        }
        botomSheet.findViewById<TextView>(R.id.no)?.setOnClickListener {
            botomSheet.dismiss()

        }
        botomSheet.show()


    }

    private fun updateNotes() {
        val title = binding.editTitle.text.toString()
        val dateTime= binding.dateTime.text.toString()
        val notes = binding.editNote.text.toString()
        val category = binding.priority.text.toString()
        val curretNotes = Notes(dataTransfer.newData.id,title,notes,dateTime,category)
        notesViewModel.updateNote(curretNotes)
        Navigation.findNavController(requireView()).navigate(R.id.action_editFragment_to_homeFragment)

    }


}