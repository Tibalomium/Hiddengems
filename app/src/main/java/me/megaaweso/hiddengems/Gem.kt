package me.megaaweso.hiddengems

import com.google.firebase.firestore.DocumentId

class Gem(@DocumentId var documentId : String? = null, var title: String = "", var body :  String = "") {
}