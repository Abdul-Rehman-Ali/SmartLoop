package com.example.smartloop.admin

import com.google.firebase.Timestamp

class ModelCategorey {

    var id: String = ""
    var category: String = ""
    var timestamp: Long = 0
    var uid : String = ""

    constructor()

    constructor(id:String, category: String, timestamp: Long, uInt: String){
        this.id = id
        this.category = category
        this.timestamp = timestamp
        this.uid = uid

    }
}