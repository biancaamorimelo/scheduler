/**
 *
 * @author Bianca Amorim
 */

public class Processos {

    public int chegada;
    public int duracao;
    public int restante;
    public int espera;
    public int resposta;
    public int retorno;
    public int clock;

    public Processos (int chegada, int duracao){
            this.chegada = chegada;
            this.duracao = duracao;
            this.restante = duracao;
            this.clock = chegada;
    }
    public int getDuracao() {
            // TODO Auto-generated method stub
            return duracao;
    }
}