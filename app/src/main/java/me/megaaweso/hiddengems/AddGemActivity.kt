package me.megaaweso.hiddengems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddGemActivity : AppCompatActivity() {

    lateinit var title : EditText
    lateinit var body : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_gem)

        title = findViewById(R.id.etTitleAdd)
        body = findViewById(R.id.etBodyAdd)

        val save = findViewById<Button>(R.id.btnSave)
        save.setOnClickListener {
            addGem()
        }
    }

    fun addGem() {
        val gem = Gem(title = title.text.toString(), body = body.text.toString())

        if(gem.title.isNotEmpty() && gem.body.isNotEmpty()) {
            DataManager.saveGem(gem)
        }

        val intent = Intent(this, ListgemsActivity::class.java)
        startActivity(intent)
    }
}