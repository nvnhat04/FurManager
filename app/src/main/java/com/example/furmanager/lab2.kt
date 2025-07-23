package com.example.furmanager
fun main(){
//    // null safety
//    val name: String? = "Nha"
//    println(getInfo(name!!)) //  compiler knows name not null
    val sv1 : Student = Student(tenSv = "nhat", mssv = "22028249", diem = 9.5f)
    val sv2 : Student = Student(tenSv = "nhat02", mssv = "22028244", diem = 8.5f)
    val sv3 : Student = Student(tenSv = "nhat03", mssv = "22028241", diem = 9.5f, datotnghiep = true, age = 21)
    sv2.daTotNghiep = false
    sv2.tuoi = 20
    println(sv1.getInfo())
    println(sv2.getInfo())
    println(sv3.getInfo())
    // create and handle list
    val listStu = mutableListOf<Student>()
    listStu.add(sv1)
    listStu.add(sv2)
    listStu.add(sv3)
    // duyet list
    // cos index
    for( i in listStu.indices){
        println("Thong tin $i: ${listStu.get(i).getInfo()}")
    }
    for (stu in listStu){
        println(stu.getInfo())
    }

}
fun getInfo(name: String) : String? {
    when(name){
        "Nhat" ->{
            return "2004"
        }
        "Nha" -> {
            return "2003"
        }
        else -> return null
    }
    return ""
}
class lab2 {
}