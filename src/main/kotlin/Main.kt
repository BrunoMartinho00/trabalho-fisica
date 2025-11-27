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

const val G = 9.81 // Aceleração da gravidade em m/s²


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

    verificarAcerto(v0, angulo)
    // Assumimos que v0 e angulo não são nulos neste ponto
    return null
}


fun verificarAcerto(v0: Double, anguloGraus: Double): Pair<Boolean, Double> {

    // --- 1. PREPARAÇÃO DO CÁLCULO ---
    val anguloRadianos = toRadians(anguloGraus)
    val v0x = v0 * cos(anguloRadianos)
    val v0y = v0 * sin(anguloRadianos)

    // --- 2. VERIFICAÇÃO INICIAL ---
    if (v0x <= 0) {
        println("❌ FALHA! Motivo: Velocidade horizontal nula (Ângulo de 90° ou V₀ = 0).")
        return Pair(false, Double.NaN)
    }

    // --- 3. CÁLCULO DA ALTURA EM X_REDE ---
    val tempoFinal = distanciaRede / v0x
    val alturaFinal = v0y * tempoFinal - 0.5 * G * tempoFinal.pow(2)

    // --- 4. VERIFICAÇÃO DE ACERTO ---
    val acerto = alturaFinal in 0.0..alturaRede

    // LINHA DE DEBUG
    //println("Conseguiu?: ${acerto}     Altura Final: ${alturaFinal}")

    // --- 5. ANÁLISE DO RESULTADO E IMPRESSÃO DETALHADA ---

    if (acerto) {
        println("✅ SUCESSO! O jogador atinge a rede com altura de: ${"%.2f".format(alturaFinal)} m.")
    } else {
        println("❌ FALHA! O jogador não atinge a rede.")

        if (alturaFinal > alturaRede) {
            // Caso A: Passou por cima
            println("   Motivo: Passou por cima da rede (Altura: ${"%.2f".format(alturaFinal)} m).")

        } else {
            // Caso B: Caiu no chão (alturaFinal <= Y_MIN_REDE)

            // 5a. Cálculo da Distância Horizontal de Impacto (Alcance Total)
            val tempoVooTotal = 2.0 * v0y / G
            val X_impacto = v0x * tempoVooTotal

            val distanciaFaltante = distanciaRede - X_impacto

            if (distanciaFaltante > 0) {
                // Caiu antes de 40m
                println("   Motivo: Caiu antes da rede.")
                println("   Distância que faltou: ${"%.2f".format(distanciaFaltante)} m.")
            } else {
                // Caiu além de 40m, mas a altura em X=40m era negativa (passou rasteiro)
                println("   Motivo: O alcance era suficiente, mas caiu no chão após o ponto de lançamento. (Alcance total: ${"%.2f".format(X_impacto)} m).")
            }
        }
    }

    // Retorno do resultado principal
    return Pair(acerto, alturaFinal)
}