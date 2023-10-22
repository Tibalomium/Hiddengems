package me.megaaweso.hiddengems

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

object DataManager {
    val gems = mutableListOf<Gem>()
    val db = Firebase.firestore
    lateinit var adapterUpdate: GemsRecylerAdapter

    init {
        //createMockData()
    }

    fun createMockData() {
        gems.add(Gem(documentId = "", title = "1", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "2", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "3", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "4", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "5", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "6", body = "fdjkasjfasjfslala"))
        gems.add(Gem(documentId = "", title = "7", body = "fdjkasjfasjfslala"))

    }

    fun deleteGem(documentId : String) {
        val id = Firebase.auth.currentUser?.uid.toString()
        if(id == null) {
            Log.d("!!!", "buuuuu")
            return
        }

        val docRef = db.collection("users").document(id).collection("gems")

        docRef.document(documentId).delete()
    }

    fun setAdapter(adap : GemsRecylerAdapter) {
        this.adapterUpdate = adap;
    }

    fun getGem(id: Int) : Gem {
        return if (id < gems.size) gems[id] else Gem()
    }

    fun saveGem(gem : Gem) {
        val id = Firebase.auth.currentUser?.uid.toString()
        if(id == null) {
            Log.d("!!!", "buuuuu")
            return
        }

        val docRef = db.collection("users").document(id).collection("gems")

        if(gem.documentId == null) {
            docRef.add(gem).addOnSuccessListener {
                Log.d("!!!", "wee")

            }
        }
    }

    fun startListener(id : String) {
        val docRef = db.collection("users").document(id).collection("gems")

        docRef.addSnapshotListener { snapshot, e ->
            if(snapshot != null) {
                gems.clear()
                for(document in snapshot.documents) {
                    val gem = document.toObject(Gem::class.java)
                    if(gem != null) {
                        gems.add(gem)
                    }
                }
                adapterUpdate?.notifyDataSetChanged()
            }
        }
    }
}