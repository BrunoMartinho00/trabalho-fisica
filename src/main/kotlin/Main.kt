/*
Num recinto de jogos radicais um canhão lança um jogador que tem de atingir uma rede
vertical de segurança que se encontra à distância horizontal de 40m do canhão.
A rede tem uma altura de 5m.

(a) Determine valores de velocidade inicial e de ângulo de lançamento que permitem que o jogador atinja
    a rede de segurança;

(b) Trace a trajetória do jogador durante o voo. O programa deve permitir alterar as condições
    iniciais do lançamento para poder simular diferentes cenários de voo
*/

import kotlin.math.*

const val G = 9.80665 // Aceleração da gravidade em m/s²


/*********************************************/
const val alturaRede: Double = 5.0
const val distanciaRede: Int = 40
/*********************************************/


// Função para converter graus para radianos (necessária para as funções trigonométricas)
fun toRadians(degrees: Double): Double = degrees * PI / 180.0


fun main() {
    println("Bem-vindo ao meu Trabalho de Fisica")
    println("Foi-me atribuído o Cenário 8")
    printOptions()
}//

fun printOptions() {
    println("\n\n")
    println("Escolhe o que pretendes fazer:")
    println("(1) Determinar valores de velocidade inicial e de ângulo de lançamento que permitem que o jogador atinja a rede de segurança")
    println("(2) Desenhar trajetória do jogador durante o voo")
    println("(3) Sair")

    print("Opção: ")

    val input = readLine()
    val opcao = input?.toIntOrNull()

    when (opcao) {
        1 -> lerParametrosLancamento()
        2 -> println("oi")
        3 -> {
            println("A sair do programa.")
            return
        }
        else -> {
            println("Opção inválida. Por favor, insira 1, 2 ou 3.")
            printOptions()
        }
    }
}

fun lerParametrosLancamento(): Pair<Double, Double>? {
    // --- Leitura da Velocidade Inicial ---
    var v0: Double? = null
    while (v0 == null || v0 <= 0) {
        print("Insira a velocidade inicial (v₀ em m/s): ")
        v0 = readLine()?.toDoubleOrNull()
        if (v0 == null || v0 <= 0) {
            println("❌ Velocidade inválida. Tente novamente.")
        }
    }

    // --- Leitura do Ângulo de Lançamento ---
    var angulo: Double? = null
    while (angulo == null || angulo < 0 || angulo > 90) {
        print("Insira o ângulo de lançamento (θ em graus, entre 0 e 90): ")
        angulo = readLine()?.toDoubleOrNull()
        if (angulo == null || angulo < 0 || angulo > 90) {
            println("❌ Ângulo inválido. Tente novamente.")
        }
    }

    lancamento(v0, angulo)
    // Assumimos que v0 e angulo não são nulos neste ponto
    return null
}

fun lancamento(v0: Double, anguloGraus: Double) {
    val anguloRadianos = toRadians(anguloGraus)

    val tempoVooTotal = (distanciaRede / (v0 * cos(anguloRadianos))) // Tempo de voo até chegar ao x final

    val alturaFinal = v0 * sin(anguloRadianos) * tempoVooTotal - (0.5 * G * (tempoVooTotal.pow(2))) // Altura Final Total

    val tempoVoo = (2 * v0 * sin(anguloRadianos)) / G // Tempo de voo (Tempo total até atingir o chão)

    val distanciaImpacto = v0 * cos(anguloRadianos) * tempoVoo // Distância de impacto (Alcance máximo)

    println("Resultados ===============")
    println("Tempo de voo até X=40m: ${"%.2f".format(tempoVooTotal)} s")
    println("Altura em X=40m: ${"%.2f".format(alturaFinal)} m")
    println("Tempo total de voo (Alcance Máximo): ${"%.2f".format(tempoVoo)} s")
    println("Distância de impacto (Alcance Máximo): ${"%.2f".format(distanciaImpacto)} m")
    println("===========================")

    return
}