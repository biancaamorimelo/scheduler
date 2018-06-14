import java.util.LinkedList;

/**
 *
 * @author Bianca Amorim
 */

/* Primeiro Algoritmo: FCFS (First Come, First Served):
    - Primeiro a entrar, primeiro a ser atendido;
    - Com esse algoritmo, a CPU é atribuída aos processos na ordem em que
    eles a requisitam. Basicamente há uma fila única de processos prontos;
    - Quando o processo requisita a CPU ele é executado. Quando chega mais
    processos, estes são inseridos em uma fila.
*/


public class FCFS implements Escalonador {
	
    public LinkedList<Processos> processos = new LinkedList<>();
    private final LinkedList<Processos> finalizados = new LinkedList<>();
 
    public FCFS(LinkedList<Processos> processos) {
        this.processos = processos;
    }
     
    @Override
    public String escalonar()
    {
        int clock = 0;
        int quantidade = processos.size();
 
        LinkedList<Processos> prontos = new LinkedList<>();
       
        while(finalizados.size() < quantidade){
            // Pra cada processo verifica se ele ja chegou e o coloca na lista de prontos
        	while (!processos.isEmpty() && processos.get(0).chegada <= clock)
                prontos.add(processos.remove(0));
 
            // Se nenhum chegou, adianta o clock para o primeiro que chegou
            if (prontos.isEmpty()){
                prontos.addFirst(processos.remove(0));
                clock = prontos.get(0).chegada;
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
 
        return "FCFS " + (tempoRetorno / quantidade) + " " + (tempoEspera / quantidade) + " " + (tempoResposta / quantidade);
    }
	
	

}

