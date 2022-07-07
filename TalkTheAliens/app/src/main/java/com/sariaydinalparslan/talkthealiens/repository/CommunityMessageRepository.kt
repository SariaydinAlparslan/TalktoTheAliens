package com.sariaydinalparslan.talkthealiens.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.sariaydinalparslan.talkthealiens.Entity.Messages

class CommunityMessageRepository {
    var communitymessagelist = MutableLiveData<List<Messages>>()
    var refcommunity : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        refcommunity=db.getReference("communitymessages")
        communitymessagelist = MutableLiveData()
    }

    fun lokasyonlarıGetir(): MutableLiveData<List<Messages>> {
        return communitymessagelist
    }
    fun communtyrepo(){
        refcommunity.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste =ArrayList<Messages>()
                for (d in snapshot.children){
                    val loka = d.getValue(Messages::class.java)
                    if (loka != null){
                        liste.add(loka)
                    }
                }

                communitymessagelist.value=liste
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}