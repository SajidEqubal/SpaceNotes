package com.shadspace.spacenotes.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.shadspace.spacenotes.R
import com.shadspace.spacenotes.model.Note
import kotlin.random.Random

class NotesAdapter(private val context : Context,val listener : NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {


    private val NotesList = ArrayList<Note>()
    private val fullList = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
    return NoteViewHolder(
        LayoutInflater.from(context).inflate(R.layout.list_item, parent ,false)
    )
    
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.noteTv.text = currentNote.note
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notesLayout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))

        holder.notesLayout.setOnClickListener{
            listener.onItemClicked(NotesList[holder.adapterPosition])
        }
        holder.notesLayout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.adapterPosition],holder.notesLayout)
            true
        }
    }

    override fun getItemCount(): Int {
       return NotesList.size
    }

    fun updateList (newList :List<Note>){
        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()
    }

    fun filterList(search: String) {
        NotesList.clear()

        for (item in fullList) {
            val titleContains = item.title?.lowercase()?.contains(search.lowercase()) ?: false
            val noteContains = item.note?.lowercase()?.contains(search.lowercase()) ?: false

            if (titleContains || noteContains) {
                NotesList.add(item)
            }
        }
        notifyDataSetChanged()
    }


    fun randomColor() : Int{
        val list = ArrayList<Int>()
        list.add(R.color.NotesColor1)
        list.add(R.color.NotesColor2)
        list.add(R.color.NotesColor3)
        list.add(R.color.NotesColor4)
        list.add(R.color.NotesColor5)
        list.add(R.color.NotesColor6)


        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notesLayout = itemView.findViewById<CardView>(R.id.lay_card)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val noteTv= itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)
    }

    interface NotesClickListener{
        fun onItemClicked (note: Note)
        fun onLongItemClicked(note: Note, cardView: CardView)
    }

}