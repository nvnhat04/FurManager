package com.example.furmanager

class Student (var tenSv: String, var mssv: String, var diem: Float){
    var daTotNghiep : Boolean? = null
    var tuoi: Int? = null
    constructor(tenSv: String,  mssv: String,  diem: Float, datotnghiep: Boolean, age: Int): this(tenSv, mssv, diem){
        this.daTotNghiep = datotnghiep
        this.tuoi = age
    }
    fun getInfo() :String{
        var res :String
        res = "Name: $tenSv, Mssv: $mssv, Diem: $diem"
        if(daTotNghiep != null && tuoi != null){
            res += ",$daTotNghiep, Tuoi: $tuoi"
        } else {
            return res
        }
        return res
    }
}