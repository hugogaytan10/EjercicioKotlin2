import kotlin.math.*

fun main(args: Array<String>) {
    //ejercicio 1
    println("Ingresa un numero: ")
    val num = readLine()!!.toDouble()
    calculator(num)
    //ejercicio 2
    val words = listOf("alfombra", "ana", "comida", "genial", "luz azul", "Ojo rojo", "estoicismo", "Yo soy")
    println(params(words) { a -> validate(a) })
    //EJERCICIO 3
    val califications: ArrayList<Double> = arrayListOf(95.6, 87.5, 75.2, 70.0)
    val ansCalificationsList = calificationList(califications)
    println(ansCalificationsList)
    //ejercicio 4
    //creamos las calificaiones y asignaturas en minusculas
    val dictionarySchool : Map<String, Double> = mapOf(
        "Movil" to 99.0,
        "WEB" to 82.5,
        "Taller de investigacion" to 72.20,
        "Logica funcional" to 69.9
    )
    val ansCalificationMap = calificationsMap(dictionarySchool)
    //imprimos el resultado
    println(ansCalificationMap)
    //ejercicio 5
    val houses = listOf(
        House(2000, 100, 3, true, 'A'),
        House(2011, 60, 2, true, 'B'),
        House(1980, 120, 4, false, 'A'),
        House(2005, 75, 3, true, 'B'),
        House(2015, 90, 2, false, 'A')
    )
    println(price(houses, 260000000.0))
}
//EJERCICIO 1
private fun calculator(num: Double){
    println("ELIGE UN TIPO DE OPERACION: \n"
    + " 1.- Seno \n 2.- Coseno\n 3.- Tangente\n 4.-Exponencial\n 5.- Logaritmico neperiano"  )
    when(readLine()!!.toInt()){
        1 -> for(i in 0..num.toInt()) println(sin(i.toDouble()))
        2 ->  for(i in 0..num.toInt()) println(cos(i.toDouble()))
        3 ->  for(i in 0..num.toInt()) println(tan(i.toDouble()))
        4 ->  for(i in 0..num.toInt()) println(exp(i.toDouble()))
        5 ->  for(i in 0..num.toInt()) println(log(i.toDouble(), 10.0))
    }

}
//ejercicio 2
fun params(values: List<String>, validate: (String) -> Boolean): List<Pair<String, Boolean>> {
    val palindrome: MutableList<Pair<String, Boolean>> = mutableListOf()
    for (i in values.indices) {
        palindrome.add(Pair(values[i], validate(values[i])))
    }
    return palindrome
}


fun validate(word: String): Boolean {
    //transformamos a mayusculas para comparar y no tener errores
    var auxWord = word.uppercase().replace(" ", "")
    //creamos el reverso de la palabra en mayuscula tambien
    val reverseWord = word.reversed().uppercase().trim().replace(" ", "")
    return reverseWord == auxWord
}
//ejercicio 3
private fun calificationList(califications: ArrayList<Double>): ArrayList<String> {
    val listAns = arrayListOf<String>()
    //recorrido de las calificaciones
    for (i in califications.indices) {
        //DEPENDIENDO SU CALIFICACION RETORNAMOS UNA CADENA
        when (califications[i]) {
            in 95.0..100.0 -> listAns.add("Excelente")
            in 85.0..94.0 -> listAns.add("Notable")
            in 75.0..84.0 -> listAns.add("Bueno")
            in 70.0..74.0 -> listAns.add("Suficiente")
            else -> listAns.add("Desempeño insuficiente")
        }
    }

    return listAns
}
//ejercicio 4
private fun calificationsMap (map: Map<String, Double>): MutableMap<String, String> {
    val ans: MutableMap<String, String> = mutableMapOf()
    for ((key, value) in map){
        when(value){
            in 95.0..100.0 -> ans[key.toString().uppercase()] = "Excelente"
            in 85.0..94.0 -> ans[key.toString().uppercase()] = "Notable"
            in 75.0..84.0 -> ans[key.toString().uppercase()] = "Bueno"
            in 70.0..74.0 ->  ans[key.toString().uppercase()] = "Suficiente"
            else -> ans[key.toString().uppercase()] = "Desempeño insuficiente"
        }

    }
    return ans;
}

//EJERCICIO 5
class House(var y: Int, var m: Int, var h: Int, var g: Boolean, var z: Char)

fun price(houses: List<House>, price: Double): List<Pair<String, Double>> {
    val priceHouses: MutableList<Pair<String, Double>> = mutableListOf()
    var p = 0.0
    for (i in houses.indices) {
        if (houses[i].z == 'A') {
            p = if (houses[i].g) {
                ((houses[i].m * 1000 + houses[i].h * 5000 + 15000) * (2022 - houses[i].y / 100)).toDouble()
            } else {
                ((houses[i].m * 1000 + houses[i].h * 5000) * (2022 - houses[i].y / 100)).toDouble()
            }
        } else if (houses[i].z == 'B') {
            p = if (houses[i].g) {
                (houses[i].m * 1000 + houses[i].h * 5000 + 15000) * (2022 - houses[i].y / 100) * 1.5
            } else {
                (houses[i].m * 1000 + houses[i].h * 5000) * (2022 - houses[i].y / 100) * 1.5
            }
        }
        if (p <= price) {
            val cad= "Habitaciones: "+houses[i].h.toString()+", "+"Año: "+
                    houses[i].y.toString()+", "+"Garage: "+houses[i].g.toString()+
                    ", "+"Zona: "+houses[i].z.toString()+", "+"Metros: "+
                    houses[i].m.toString()+", Precio Total:"
            priceHouses.add(Pair(cad, p))
        }
    }
    return priceHouses
}