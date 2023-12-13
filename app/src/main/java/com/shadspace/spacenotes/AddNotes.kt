package com.shadspace.spacenotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shadspace.spacenotes.databinding.ActivityAddNotesBinding
import com.shadspace.spacenotes.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddNotes : AppCompatActivity() {
    private lateinit var binding : ActivityAddNotesBinding

    private lateinit var note : Note
    private lateinit var old_note : Note
    var isUpdate = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("current_note") as Note
            binding.edtTitle.setText(old_note.title)
            binding.edtNote.setText(old_note.note)
            isUpdate = true
        } catch (e: Exception){
            e.printStackTrace()
        }
        binding.imgCheck.setOnClickListener{
            val title = binding.edtTitle.text.toString()
            val note_desc = binding.edtNote.text.toString()

            if (title.isNotEmpty() || note_desc.isNotEmpty()){
                val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

                if (isUpdate) {
                    note = Note(
                        old_note.id, title, note_desc, currentDate
                    )
                } else {
                    note = Note(
                        null, title, note_desc, currentDate
                    )
                }

                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }else{
                Toast.makeText(this@AddNotes,"Please enter your notes", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        binding.imgBack.setOnClickListener{
            onBackPressed()
        }
    }
}