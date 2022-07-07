package com.sariaydinalparslan.talkthealiens.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sariaydinalparslan.talkthealiens.Entity.Messages

class MessageRepository {
    var messagelist = MutableLiveData<List<Messages>>()
    var refLokasyonlar : DatabaseReference



    init {

        val user = Firebase.auth.currentUser
        val db = FirebaseDatabase.getInstance()
        refLokasyonlar=db.getReference("message${user?.uid.toString()}")
        messagelist = MutableLiveData()
    }


    fun mesajlarıGetir(): MutableLiveData<List<Messages>> {
        return messagelist
    }
    fun tummesajlarıAl(){
        refLokasyonlar.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste =ArrayList<Messages>()
                for (d in snapshot.children){
                    val loka = d.getValue(Messages::class.java)
                    if (loka != null){
                        liste.add(loka)
                    }
                }

                messagelist.value=liste
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }


}