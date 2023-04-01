package Clases

import Persona
import Utils
import Utils.Companion.procesandoDatos
import Utils.Companion.procesandoDatosCoroutines
import constantes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*

internal class Menu {


    internal fun mostrarBienvenida() {
        Utils.cargando("Cargando sistema...","=", 40)
        println("Bienvenido(a) a VICO")
        println("**************************************")

        val instrucciones = """
        Aquí te apoyaremos a obtener un plan alimenticio, adecuado a tus necesidades,
        así como sugerencias de recetas.
        
        Teclee ENTER para mostrar el menú de opciones
    """.trimIndent()
        print(instrucciones)
        val enter = readLine()!!

    }

    internal fun mostrarMenu() {
        val menu = """
            1 = Plan alimenticio 
            2 = Sugerencia de recetas
            3 = Salir
    """.trimIndent()
        println(menu)

    }

    internal fun cargarOpcion() {
        var opcion = 0
        val reader = Scanner(System.`in`)
        while (opcion != 3) {
            try {

                print("Indica la opción deseada: ")
                opcion = reader.nextInt()

                when (opcion) {
                    1 -> planNutricional()
                    2 -> Recetas() //https://spoonacular.com/food-api/console#Profile
                    3 -> {
                        Salir()
                        break
                    }
                    else -> {
                        println("La opcion $opcion no es correcto.")
                        continue
                    }
                }

            } catch (e: InputMismatchException) {
                println(constantes.ANSI_RED + "*** LOS DATOS INGRESADOS NO SON CORRECTOS ***" + constantes.ANSI_RESET)
                println("\n")
                mostrarMenu()
                cargarOpcion()
                break
            }


        }
    }

    private fun Recetas()    {
        val receta = Receta()
        receta.mostrarOpciones()
        val read = Scanner(System.`in`)
        print("¿Que quieres comer? captura la palabra principal: ")
        val opcionBusqueda = read.next()
        procesandoDatos()

        try {
            runBlocking(Dispatchers.IO) {
                receta.mostrarRecetas(opcionBusqueda)
                delay(1000L)
            }
        }
        catch (e: Exception){
            println("Ups, tuvimos problemas al mostrar los datos.")
        }
        finally {
            mostrarMenu()
        }


    }

    private fun Salir() {
        println("¡Gracias por utilizar el sistema!")
        System.exit(0)
    }

    private fun planNutricional() {
        val read = Scanner(System.`in`)
        val personaCRUD = PersonaCRUD()
        val correo = Utils.inputDatoCorreo("Ingresa tu correo: ")
        var nombre = ""
        var altura = 0.0
        var peso = 0.0

        val datosPersona = personaCRUD.ObtenerPersona(correo)
        if (!datosPersona.nombre.isNullOrEmpty()) {
            val datos = """
            Excelente, ya tenemos tus datos registrados.
            Id: ${datosPersona.id}
            Nombre: ${datosPersona.nombre}
            edad: ${datosPersona.edad}
            genéro: ${datosPersona.genero}
            peso: ${datosPersona.peso}
            altura: ${datosPersona.altura}
           
        """.trimIndent()
            print(datos)
            nombre = datosPersona.nombre
            altura = datosPersona.altura.toDouble()
            peso = datosPersona.peso.toDouble()
        } else {
            print("Ingresa tu nombre: ")
            nombre = read.next()

            val persona = Persona(nombre)
            persona.correo = correo
            persona.edad = Utils.inputDatoEntero("Ingresa tu edad: ").toInt()
            val genero = Utils.inputDatoGenero("Ingresa tu genéro (MASCULINO, FEMENINO, OTRO): ")
            persona.genero = Persona.Genero.valueOf(genero)
            persona.peso = Utils.inputDatoEntero("Ingresa tu peso [kg]: ").toInt()
            persona.altura = Utils.inputDatoEntero("Ingresa tu altura [cm]: ").toInt()
            personaCRUD.Agregar(persona)

            nombre = nombre
            altura = persona.altura.toDouble()
            peso = persona.peso.toDouble()

        }

        procesandoDatosCoroutines()

        val planNutricional = PlanNutricional(nombre,altura,peso)
        planNutricional.MostrarPlanNutricional()

        mostrarMenu()
        println("\n")

    }

}