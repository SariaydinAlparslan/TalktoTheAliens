package com.sariaydinalparslan.talkthealiens.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sariaydinalparslan.talkthealiens.Entity.Messages
import com.sariaydinalparslan.talkthealiens.repository.CommunityMessageRepository

class CommunityViewModel : ViewModel() {
    var communitymessagelist = MutableLiveData<List<Messages>>()
    var communitymessagerepo = CommunityMessageRepository()

    init {
        communtyrepo()
        communitymessagelist = communitymessagerepo.lokasyonlarıGetir()
    }

    fun communtyrepo(){

        communitymessagerepo.communtyrepo()
    }
}