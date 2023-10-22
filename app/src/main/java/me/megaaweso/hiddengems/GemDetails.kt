package me.megaaweso.hiddengems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class GemDetails : AppCompatActivity() {

    lateinit var gem: Gem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gem_details)

        var gemId = intent.getIntExtra("id", 0)

        gem = DataManager.getGem(gemId)

        val header = findViewById<TextView>(R.id.gemDetailsHeader)
        val body = findViewById<TextView>(R.id.gemDetailsBody)
        val button = findViewById<Button>(R.id.gemDetailsButton)

        header.text = gem.title
        body.text = gem.body

        button.setOnClickListener {
            super.finish()
        }
    }
}
