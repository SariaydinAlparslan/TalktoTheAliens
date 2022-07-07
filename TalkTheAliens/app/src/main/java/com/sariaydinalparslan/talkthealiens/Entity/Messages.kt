package com.sariaydinalparslan.talkthealiens.Entity

import com.google.firebase.database.IgnoreExtraProperties



@IgnoreExtraProperties
data class Messages(var gelenmesaj:String?="", var gidenmesaj:String?="",var kullaniciadi:String?="",var whichplanet:String?="") {
}