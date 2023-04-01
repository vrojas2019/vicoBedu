package Clases

import Interfaces.IApi
import Models.DatosAPI
import constantes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Receta {


    val opciones: List<String> = listOf("Pasta", "Carne", "Pescado", "Sopa", "Pollo")

    internal fun mostrarOpciones() {
        println("¿No sabes que comer? No te preocupes te damos algunas sugerencias:")
        opciones.forEach() {
            println(it?.let { "- $it" })
        }
    }

    internal fun mostrarRecetas(cadenaBusqueda: String): Unit {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(constantes.BASE_URL_API)
            .build()
            .create(IApi::class.java)

        val retrofitData = retrofitBuilder.obtenerDatos(constantes.API_KEY, cadenaBusqueda, constantes.MAX_RESULT)

        retrofitData.enqueue(object : Callback<DatosAPI?> {
            override fun onResponse(call: Call<DatosAPI?>, response: Response<DatosAPI?>) {
                val responseBody = response.body()

                responseBody?.let {
                    if (it.results.count()> 0){
                        println("***RECOMENDACIONES***")
                        it.results.forEach(){

                            println(it.title)
                            println("Tiempo de preparación [min]: ${it.readyInMinutes}")
                            println("Comensales: ${it.servings}")

                            it.cuisines?.let {
                                val cocinas = it.joinToString()
                                if (cocinas.trimEnd().isEmpty())
                                    println("Tipo de cocina: sin clasificación ")
                                else
                                    println("Tipo de cocina: ${cocinas} ")
                            }

                            it.dishTypes?.let {
                                val platillos = it.joinToString()
                                if (platillos.trimEnd().isEmpty())
                                    println("Sugerencia de horario: sin clasificación ")
                                else
                                    println("Sugerencia de horario: ${platillos} ")
                            }

                            it.diets?.let {
                                println("Dietas: ${it.joinToString()} ")
                            }

                            println("Preparación...")

                            it.analyzedInstructions?.let {
                                if (it.count()>0){
                                    it[0].steps.forEach {
                                        print("${it.number} - ${it.step} \n")
                                    }
                                }
                                else
                                {
                                    println("Sin información...")
                                }


                            }

                            println("\n")
                            println("Ver más detalles en ${it.spoonacularSourceUrl} \n")
                            println("-----------------------------------------------------------------------------")
                        }

                    }
                    else{
                        println("Ups, no hemos encontrado opciones, intenta escribiendo la palabra en ingles")
                    }

                }



            }

            override fun onFailure(call: Call<DatosAPI?>, t: Throwable) {
                println("Por el momento el servicio no esta disponible, intente más tarde")
            }


        })
    }
}