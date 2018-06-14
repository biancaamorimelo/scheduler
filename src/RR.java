import java.util.LinkedList;

/**
 *
 * @author Bianca Amorim
 */

/* Terceiro Algoritmo: RR (Round Robin):

    - Cada processo recebe uma pequena quantidade de tempo de CPU (quantum), entre 10 e 100ms.
    - Depois que esse tempo se esgota o processo é interrompido e inserido no fim da fila de prontos.
    - Se no final do quantum o processo não terminou, a CPU sofre uma preempção e outro processo entra para executar.
*/

public class RR implements Escalonador {
	
    private final int quantum;
    public LinkedList<Processos> processos = new LinkedList<>();
    public LinkedList<Processos> finalizados = new LinkedList<>();;
 
    public RR(LinkedList<Processos> processos, int quantum)
    {
        this.processos = processos;
        this.quantum = quantum;
    }
 
    @Override
    public String escalonar()
    {
        int clock = 0;
        int quantidade = processos.size();
 
        LinkedList<Processos> prontos = new LinkedList<>();
       
        while(finalizados.size() < quantidade){
            Processos processo = null;
           
            if (!prontos.isEmpty()){
                // Pega o primeiro processo da lista e remove ja que ele vai ser executado
                processo = prontos.remove(0);
               
                // Se o tempo restante para execução for menor que o quantum, executa somente o restante
                // Se não, executa o quantum
                int tempo = quantum > processo.restante ? processo.restante : quantum;
   
                // Define o tempo de resposta apenas uma vez, quando o processo é executado pela primeira vez
                if (processo.restante == processo.duracao)
                    processo.resposta = clock - processo.clock;
               
                // A espera desde o clock da ultima execucao até o clock atual
                processo.espera += clock - processo.clock;
               
                // Retira o tempo do restante
                processo.restante -= tempo;
               
                // Atualiza o clock
                clock += tempo;
               
                // Atualiza o clock da ultima execução com o clock atualizado
                processo.clock = clock;
            }
           
            // Pra cada processo verifica se ele ja chegou e o coloca na lista de prontos
            while (!processos.isEmpty() && processos.get(0).chegada <= clock){
                prontos.add(processos.remove(0));
                //System.out.println(clock + ", ADD " + prontos.peekLast());
            }
               
            // Se nenhum chegou, adianta o clock para o primeiro que chegou
            if (prontos.isEmpty() && !processos.isEmpty()){
                prontos.add(processos.remove(0));
                clock = prontos.get(0).chegada;
                
            }
           
            // Se havia algum processo sendo executado
            if (processo != null){
                // Se ele terminou
                if (processo.restante == 0){
                    // Define o tempo de retorno
                    processo.retorno = clock - processo.chegada;
                    finalizados.add(processo);
                }
                // Se não terminou, coloca de volta na fila de prontos
                else prontos.add(processo);
               
            }
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
   
        return "RR " + (tempoRetorno / quantidade) + " " + (tempoResposta / quantidade) + " " + (tempoEspera / quantidade);
    }

}

