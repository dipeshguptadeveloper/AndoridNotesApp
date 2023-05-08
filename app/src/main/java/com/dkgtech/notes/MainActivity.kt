package com.dkgtech.notes


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import at.markushi.ui.CircleButton
import com.dkgtech.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var noteAdapter: RecyclerNoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrNotes = ArrayList<NoteModel>().apply {
            add(NoteModel("Meeting", "WSR meet at 10:30 AM"))
            add(NoteModel("Face to Face Interview", "Interview to be held at 12:30 PM"))
            add(NoteModel("Face to Face Interview", "Interview to be held at 12:30 PM"))
            add(NoteModel("Meeting", "WSR meet at 10:30 AM"))
        }

        binding.rcNoteView.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        noteAdapter = RecyclerNoteAdapter(this@MainActivity, arrNotes)

        binding.rcNoteView.adapter = noteAdapter

        binding.btnFab.setOnClickListener {
            val addNoteDialog = Dialog(this@MainActivity)
            addNoteDialog.setContentView(R.layout.add_note)
            addNoteDialog.setCancelable(false)

            val edtNoteTitle = addNoteDialog.findViewById<EditText>(R.id.edtNoteTitle)
            val edtNoteDescription = addNoteDialog.findViewById<EditText>(R.id.edtNoteDescription)
            val btnNoteAdd = addNoteDialog.findViewById<Button>(R.id.btnNoteAdd)
            val btnNoteCancel = addNoteDialog.findViewById<Button>(R.id.btnNoteCancel)
            val btnNoteDelete = addNoteDialog.findViewById<CircleButton>(R.id.btnNoteDelete)

            btnNoteAdd.setOnClickListener {
                val noteTitle = edtNoteTitle.text.toString()
                val noteDescription = edtNoteDescription.text.toString()

                if (noteTitle.isNotBlank() && noteDescription.isNotBlank()) {
                    arrNotes.add(NoteModel(noteTitle, noteDescription))
                    noteAdapter.notifyItemInserted(arrNotes.size - 1)
                    binding.rcNoteView.scrollToPosition(arrNotes.size - 1)
                    addNoteDialog.dismiss()
                } else {
                    Toast.makeText(this@MainActivity, "Enter valid details", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            btnNoteCancel.setOnClickListener {
                addNoteDialog.dismiss()
            }


            addNoteDialog.show()
        }


    }
}