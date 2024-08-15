// Definición de la interfaz base para los números
interface IBaseNumber {
    val value: Int
    fun printValue()
}

class PrimeNumber(override val value: Int) : IBaseNumber {   // Clase que representa un nUmero primo, implementando la interfaz IBaseNumber
    init {
        require(isPrime(value)) { "$value no es un numero primo" }
    }

    override fun printValue() {     // Implementacion de la funcion para imprimir el valor del numero primo
        println("Numero primo: $value")
    }
// Función para determinar si un numero es primo
    private fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        if (num == 2) return true
        if (num % 2 == 0) return false
        for (i in 3..Math.sqrt(num.toDouble()).toInt() step 2) {
            if (num % i == 0) return false
        }
        return true
    }
}
// Clase que representa un numero impar, implementando la interfaz IBaseNumber
class OddNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = (1 until value).filter { value % it == 0 }

    override fun printValue() {
        println("Numero impar: $value")
    }

    fun printDivisors() {
        println("Divisores de $value: ${divisors.joinToString(", ")}")
    }
}

class EvenNumber(override val value: Int) : IBaseNumber {
    val divisors: List<Int> = (1 until value).filter { value % it == 0 }

    override fun printValue() {
        println("Numero par: $value")
    }

    fun printDivisors() {
        println("Divisores de $value: ${divisors.joinToString(", ")}")
    }
}

class PrimeNumberProcessor(val range: IntRange) {
    fun processNumbers(): EvaluationResult {
        val primes = mutableListOf<PrimeNumber>()
        val odds = mutableListOf<OddNumber>()
        val evens = mutableListOf<EvenNumber>()

        for (num in range) {
            if (num > 1 && isPrime(num)) {
                primes.add(PrimeNumber(num))
            } else if (num % 2 == 0) {
                evens.add(EvenNumber(num))
            } else if (num % 2 != 0) {
                odds.add(OddNumber(num))
            }
        }

        return EvaluationResult(primes, odds, evens)
    }

    private fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        if (num == 2) return true
        if (num % 2 == 0) return false
        for (i in 3..Math.sqrt(num.toDouble()).toInt() step 2) {
            if (num % i == 0) return false
        }
        return true
    }
}

sealed class NumberType {
    object Prime : NumberType()
    object Odd : NumberType()
    object Even : NumberType()
}
// Data class que almacena los resultados de la evaluacion de numeros
data class EvaluationResult(
    val primes: List<PrimeNumber>,
    val odds: List<OddNumber>,
    val evens: List<EvenNumber>
)

// Función main que ejecuta el programa
fun main() {
    val processor = PrimeNumberProcessor(1..20)
    val result = processor.processNumbers()
    
// Imprime los numeros primos encontrados
    println("Numeros primos:")
    result.primes.forEach { it.printValue() }
    
// Imprime los numeros impares y sus divisores
    println("\nNumeros impares:")
    result.odds.forEach {
        it.printValue()
        it.printDivisors()
    }
// Imprime los numeros pares y sus divisores
    println("\nNumeros pares:")
    result.evens.forEach {
        it.printValue()
        it.printDivisors()
    }
}  // ESTUDIANTE JASON ALEXIS GARCIA DIAZ
	//CARRERA INGENIERIA EN DESARROLLO DE SOFTWARE 