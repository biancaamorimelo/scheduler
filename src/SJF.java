import java.util.LinkedList;
import java.util.Collections;

/**
 *
 * @author Bianca Amorim
 */

/* Segundo Algoritmo: SJF (Shortest Job First):
    - O escalonador sempre escolhe o processo cujo o tempo de execução restante seja o menor.
    - Da o menor tempo médio de espera para um dado conjunto de processos
*/
public class SJF implements Escalonador {
	
    private int clock ;

    public LinkedList<Processos> prontos = new LinkedList<>();
    public LinkedList<Processos> processos = new LinkedList<>();
    public LinkedList<Processos> finalizados = new LinkedList<>();;

    public SJF(LinkedList<Processos> processos) {
        this.processos = processos;        
    }

    @Override
    public String escalonar() {

        int quantidade = processos.size();

        while(finalizados.size() < quantidade){
            // Pra cada processo verifica se ele ja chegou e o coloca na lista de prontos
            while (!processos.isEmpty() && processos.get(0).chegada <= clock)
                 prontos.add(processos.remove(0));

            // Pega o processo de menor duracao
             if (prontos.isEmpty()){
                    clock = prontos.get(0).chegada;
            }else{
                Collections.sort(prontos, new Ordenar()); // Compara os tempos entre os processos
            }

            // Pega o primeiro processo da lista e remove ja que ele vai ser executado
            Processos processo = prontos.remove(0);

            processo.espera = clock - processo.chegada;
            processo.resposta = processo.espera;
            processo.retorno = clock + processo.duracao - processo.chegada;

            // Atualiza o clock
            clock += processo.duracao;

            finalizados.add(processo);
        }
        
    return calcularMetricas();
    
    }
    
    public String calcularMetricas() {
        double tempoResposta = 0;
        double tempoEspera = 0;
        double tempoRetorno = 0;
        int quantidade = finalizados.size();

        for (Processos p : finalizados) {
            tempoResposta += p.resposta;
            tempoEspera += p.espera;
            tempoRetorno += p.retorno;
        }
        return "SJF " + (tempoRetorno / quantidade) + " " + (tempoEspera / quantidade) + " " + (tempoResposta / quantidade);   	 
    }
}

