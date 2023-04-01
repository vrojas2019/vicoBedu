package Clases

import java.math.RoundingMode
import java.text.DecimalFormat

class PlanNutricional {
    var nombre: String = ""
    var altura: Double = 0.0
    var peso: Double = 0.0


    constructor(nombre: String, altura: Double, peso: Double) {
        this.nombre = nombre
        this.altura = altura
        this.peso = peso
    }


    private fun obtenerIMC(): Double {
        val imc = peso / ((altura / 100) * (altura / 100))
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(imc).toDouble()

    }

    private fun obtenerTipoCondicion(): String {
        val imc = obtenerIMC()
        return when {
            imc <= 18.5 -> "Peso inferior"
            imc > 18.5 && imc <= 24.9 -> "Peso normal"
            imc > 24.9 && imc <= 29.9 -> "Sobrepeso"
            imc > 29.9 && imc <= 34.9 -> "Obesidad moderada"
            imc > 34.9 -> "Obesidad severa"
            else -> "[No hay datos]"
        }
    }

    private fun DietaRecomendada(): String {
        val imc = obtenerIMC()
        return when {
            imc <= 18.5 -> {
                val vegetariana = DietaVegetariana("Dieta vegetariana")
                vegetariana.MostrarDieta()
            }
            imc > 18.5 && imc <= 24.9 -> {
                val proteica = DietaProteica("Dieta proteica")
                proteica.MostrarDieta()
            }
            imc > 24.9 && imc <= 29.9 -> {
                val paleo = DietaPaleo("Dieta paleo")
                paleo.MostrarDieta()
            }
            imc > 29.9 && imc <= 34.9 -> {
                val mediterraneo = DietaMediterranea("Dieta mediterránea")
                mediterraneo.MostrarDieta()
            }
            imc > 34.9 -> {
                val hipocalorica = DietaHipocalorica("Dieta hipocalórica")
                hipocalorica.MostrarDieta()
            }
            else -> "[No hay datos]"
        }
    }


    private fun GymCercanos(): String {
        val coordenadas = "" //Obtener latitude y longitud posición actual
        val imc = obtenerIMC()
        return when {
            imc <= 18.5 -> "https://www.google.com.mx/maps/place/Gimnasio+Smart+Fit+-+Diagonal+Cuitl%C3%A1huac+%F0%9F%92%AA%F0%9F%8F%BC/@19.4599843,-99.1866283,16z/data=!4m10!1m2!2m1!1ssmart+fit!3m6!1s0x85d1f9df90d9aabd:0xdff62685eeaa1068!8m2!3d19.4599843!4d-99.1778736!15sCglzbWFydCBmaXQiA4gBAZIBA2d5beABAA!16s%2Fg%2F11fq7zqm02"
            imc > 18.5 && imc <= 24.9 -> "https://www.google.com.mx/maps/place/Gimnasio+Smart+Fit+-+Portal+Quer%C3%A9taro+%F0%9F%92%AA%F0%9F%8F%BC/@20.6142897,-100.4102358,14z/data=!4m10!1m2!2m1!1ssmart+fit!3m6!1s0x85d35bd091f2ee43:0xc6f30b5b99d06716!8m2!3d20.6404821!4d-100.4161565!15sCglzbWFydCBmaXQiA4gBAZIBA2d5beABAA!16s%2Fg%2F11shkp5b11"
            imc > 24.9 && imc <= 29.9 -> "https://www.google.com.mx/maps/place/Gimnasio+Smart+Fit+-+Paseo+San+Francisco+%F0%9F%92%AA%F0%9F%8F%BC/@19.0321054,-98.2241223,14z/data=!4m10!1m2!2m1!1ssmart+fit!3m6!1s0x85cfc0e59b833673:0xa12967a4f0d22bb9!8m2!3d19.042674!4d-98.191195!15sCglzbWFydCBmaXQiA4gBAZIBA2d5beABAA!16s%2Fg%2F11b6mr4k08"
            imc > 29.9 && imc <= 34.9 -> "https://www.google.com.mx/maps/place/Smart+Fit+-+Forum+Cuernavaca+%F0%9F%92%AA%F0%9F%8F%BC/@18.8974731,-99.2039211,13.75z/data=!4m10!1m2!2m1!1ssmart+fit!3m6!1s0x85cddf5ccd6fd61b:0x2ccc5af54520d142!8m2!3d18.9256941!4d-99.199294!15sCglzbWFydCBmaXQiA4gBAZIBA2d5beABAA!16s%2Fg%2F11j_2jw4nd"
            imc > 34.9 -> "https://www.google.com.mx/maps/place/Gimnasio+Smart+Fit+-+Forum+Tlaquepaque+%F0%9F%92%AA%F0%9F%8F%BC/@20.6424316,-103.3976585,13z/data=!3m1!5s0x8428b2371dc9d1b3:0xd2a738d82c6c1f98!4m10!1m2!2m1!1ssmart+fit!3m6!1s0x8428b236e66d4099:0x2db52018edba040e!8m2!3d20.648385!4d-103.319723!15sCglzbWFydCBmaXQiA4gBAZIBA2d5beABAA!16s%2Fg%2F11bw2_hqmz"
            else -> "[No hay datos]"
        }
    }

    internal fun MostrarPlanNutricional() {
        println(
            """
            
            Hola $nombre, tu IMC es ${obtenerIMC()} estas clasificado(a) como ${obtenerTipoCondicion()}. 
            ${DietaRecomendada()}.
            Da clic en el siguiente link para ubicar el GYM mas cercano ${GymCercanos()}
            
        """.trimIndent()
        )
    }


}