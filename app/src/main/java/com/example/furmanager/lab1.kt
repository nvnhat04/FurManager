package com.example.furmanager
fun main (){
    println("hello java")
    println("------------ ")
//    var a: Float
//    a = 1f
//    var b: Float
//    b = 2f
    val a = 1f
    val b = 0f
    var c = a + b
    val mess : String = "Sum of $a and $b is $c "
    println(mess)
    println(divide(a,b))
    val arrX = intArrayOf(1,2,3,4)
    val arrY = arrayOf(1.5f,"hi", 1)


}
fun divide (num1: Float, num2: Float): String {
    if(num2 == 0f) {
        return "Error: num2 is zero\n"
    }
    val res = num1/num2

    return "Result of operation $num1 and $num2 is $res"
}
class lab1 {
}