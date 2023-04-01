package Interfaces

import Persona

interface IPersonaCRUD {
    fun Agregar(persona : Persona)

    fun Listar() : List<Persona>

    fun ObtenerPersona(id : Int) : Persona

    fun Actualizar(persona : Persona)

    fun Eliminar(id : Int)
}