package me.megaaweso.hiddengems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListgemsActivity : AppCompatActivity(), GemsRecylerAdapter.OnClickListener {

    lateinit var db : FirebaseFirestore
    lateinit var recyclerView : RecyclerView
    lateinit var fabAdd : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listgems)

        recyclerView = findViewById(R.id.recyclerView)
        db = Firebase.firestore
        fabAdd = findViewById(R.id.fabAdd)

        fabAdd.setOnClickListener {
            val intent = Intent(this, AddGemActivity::class.java)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = GemsRecylerAdapter(this, DataManager.gems, this)
        recyclerView.adapter = adapter
        DataManager.setAdapter(adapter)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun OnClick(position: Int) {
        val intent = Intent(this, GemDetails::class.java)
        intent.putExtra("id", position)
        startActivity(intent)

    }
}