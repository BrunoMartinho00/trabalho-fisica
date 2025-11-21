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
import java.io.IOException

const val G = 9.81 // Aceleração da gravidade em m/s²

// Função para converter graus para radianos (necessária para as funções trigonométricas)
fun toRadians(degrees: Double): Double = degrees * PI / 180.0


fun main() {
    println("Bem-vindo ao meu Trabalho de Fisica");
    println("Foi-me atribuído o Cenário 8");
    printOptions()
}//

fun printOptions() {
    println("\n\n");
    println("Escolhe o que pretendes fazer:");
    println("(1) Determinar valores de velocidade inicial e de ângulo de lançamento que permitem que o jogador atinja a rede de segurança");
    println("(2) Desenhar trajetória do jogador durante o voo");
    println("(3) Sair");

    print("Opção: ");

    val input = readLine()
    val opcao = input?.toIntOrNull()

    when (opcao) {
        1 -> calculateNumbers();
        2 -> println("oi");
        3 -> {
            println("A sair do programa.")
            return;
        }
        else -> {
            println("Opção inválida. Por favor, insira 1, 2 ou 3.");
            printOptions()
        }
    }
}

fun calculateNumbers() {
    println("\n\n");

    // Variável para armazenar a distância horizontal (X)
    var distanciaHorizontal: Double? = null

    // Loop para garantir que um valor Double válido e positivo seja inserido
    while (distanciaHorizontal == null || distanciaHorizontal <= 0) {
        println("Indica-me a distância horizontal em metros:");
        print("Distância (m): ");

        val input = readLine()
        distanciaHorizontal = input?.toDoubleOrNull()

        if (distanciaHorizontal == null || distanciaHorizontal <= 0) {
            println("❌ Entrada inválida. Por favor, insira um número positivo.")
        }
    }

    // Variável para armazenar a altura da rede (Y)
    var alturaRede: Double? = null

    // Loop para garantir que um valor Double válido e positivo seja inserido
    while (alturaRede == null || alturaRede <= 0) {
        println("\nIndica-me a altura da rede em metros:");
        print("Altura (m): ");

        val input = readLine()
        alturaRede = input?.toDoubleOrNull()

        if (alturaRede == null || alturaRede <= 0) {
            println("❌ Entrada inválida. Por favor, insira um número positivo.")
        }
    }

    /*
    Valores lidos;
    println("Distância Horizontal (Xf): ${distanciaHorizontal} m")
    println("Altura da Rede (Y_max): ${alturaRede} m")
     */


}