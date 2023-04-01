
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.io.File
import java.io.FileNotFoundException
import java.io.FileWriter
import java.util.*

internal class Utils {

    companion object {
        internal fun cargando(mensaje: String,caracter: String, longitud: Int) {
            println(mensaje)
            for (i in 1..longitud) {
                runBlocking {
                    delay(constantes.TIMESLEEPCARGANDO)
                    print(caracter)
                }
            }
            print("\n")
        }

        internal fun procesandoDatos() {
            runBlocking {
                delay(constantes.TIMESLEEP)
                 Utils.cargando("\n" +
                        "Un momento estamos procesando tu solicitud...","=", 30)
            }

        }

        internal fun procesandoDatosCoroutines () = runBlocking {
            print("Procesando")
            withTimeout(10_0000L) {
                repeat(101) { i ->
                    print(".")
                    delay(10L)
                }
            }
        }

        internal  fun WriteToFile(cadena: String) {
            val path = constantes.PATH_BD
            try {
                val file = File(path)
                if (!file.exists()) {
                    file.createNewFile()
                }
                val fileWriter = FileWriter(file, true)
                fileWriter.write(cadena + "\n")
                fileWriter.close()
            } catch (e: FileNotFoundException) {
                print("El archivo $path no existe")
            } catch (e: Exception) {
                print(e.message)
            }
        }

        internal fun generarContrasena(): String {
            //Basado del código Ascii
            val charPool: List<Int> = (32..38) + (48..57) + (63..90) + (97..122)
            val caracterRandom: () -> Char = { charPool.random().toChar() }
            var contrasena = ""
            for (i in 1..constantes.LONGITUD_CONTRASENA) {
                contrasena += caracterRandom()
            }
            return contrasena
        }

        private fun isEntero(dato: String = ""): Boolean {
            try {
                var datoTMP = dato ?: ""
                datoTMP.toInt()
                return true
            } catch (ex: NumberFormatException) {
                println("Debe capturar solo numeros enteros")
                return false
            }
        }

        internal fun inputDatoEntero(texto: String = ""): String {
            val read = Scanner(System.`in`)
            var dato = ""
            do {
                print(texto)
                dato = read.next()
            } while (!isEntero(dato))
            return dato
        }

        val isEmailValido = { correo: String -> constantes.EMAIL_REGEX.toRegex().matches(correo) }
        private fun ValidaFormatoCorreo(correo: String = ""): Boolean {
            if (isEmailValido(correo)) {
                return true
            }
            println("El formato de correo debe ser ejemplo@dominio.com")
            return false
        }

        internal fun inputDatoCorreo(texto: String = ""): String {
            val read = Scanner(System.`in`)
            var dato = ""
            do {
                print(texto)
                dato = read.next()
            } while (!ValidaFormatoCorreo(dato))
            return dato
        }

        private fun ValidaGenero(dato: String = ""): Boolean {
            Persona.Genero.values().forEach {
                if (it.name == dato) {
                    return true
                }
            }
            println("Debe capturar MASCULINO, FEMENINO u OTRO")
            return false
        }

        internal fun inputDatoGenero(texto: String = ""): String {
            val read = Scanner(System.`in`)
            var dato = ""
            do {
                print(texto)
                dato = read.next()
            } while (!ValidaGenero(dato))
            return dato
        }


    }
}