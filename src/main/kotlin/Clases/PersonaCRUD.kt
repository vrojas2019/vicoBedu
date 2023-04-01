package Clases

import Interfaces.IPersonaCRUD
import Persona
import Utils
import constantes
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class PersonaCRUD() : IPersonaCRUD {

    override fun Agregar(persona: Persona) {
        val personaJSON: String = Json.encodeToString(persona)
            persona.id = obtenerUltimoId() + 1
            val registro = "${persona.id}|$personaJSON"
            Utils.WriteToFile(registro)
            println("Se han registrado tus datos con el Id de cliente: ${persona.id}")

    }

    override fun Listar(): MutableList<Persona> {
        val registros = obtenerRegistros()
        var ListPersonas : MutableList<Persona> = arrayListOf()
        registros.forEach {
            var persona = Persona("")
            persona = Json.decodeFromString<Persona>(it.toString().split("|")[1].toString())
            ListPersonas.add(persona)
           }
       return ListPersonas

    }

    override fun ObtenerPersona(id: Int): Persona {
        val registros = obtenerRegistros()
        var persona = Persona("")
        registros.forEach {
            val idBD: Int = it.toString().split("|")[0].toInt()
            if (idBD == id) {
                persona = Json.decodeFromString<Persona>(it.toString().split("|")[1].toString())
            }
        }
        return persona
    }

    override fun Actualizar(persona: Persona) {
        TODO("Not yet implemented")
    }

    override fun Eliminar(id: Int) {
        TODO("Not yet implemented")
    }

    private fun obtenerRegistros(): MutableList<String> {
        val inputStream: InputStream = File(constantes.PATH_BD).inputStream()
        val registros = mutableListOf<String>()
        inputStream.bufferedReader().useLines { lines -> lines.forEach { registros.add(it) } }
        return registros
    }

    private fun obtenerUltimoId(): Int {
        //Leer linea por linea
        try {
            val registros = obtenerRegistros()
            if (registros.count() == 0)
                return 0
            else {
                val id: Int = registros.last().toString().split("|")[0].toInt()
                return id
            }
        } catch (e: FileNotFoundException) {
            return 0
        }

    }

    internal fun ObtenerPersona(correo: String): Persona {
        var persona = Persona("")
        try {
            val inputStream: InputStream = File(constantes.PATH_BD).inputStream()
            val registros = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { registros.add(it) } }
            registros.forEach {
                if (it.toString().uppercase().contains(correo.uppercase())) {
                    persona = Json.decodeFromString<Persona>(it.toString().split("|")[1].toString())
                    return persona
                }
            }
            return persona
        } catch (e: FileNotFoundException) {
            return persona
        }


    }
}