import java.util.Comparator;

/**
 *
 * @author Bianca Amorim
 */

// Faz a comparação dos tempos entre os processos e ordena na lista

public class Ordenar implements Comparator<Processos>{
    
    @Override
    public int compare(Processos p1, Processos p2) {
        return (p1.getDuracao()-p2.getDuracao());
    }    
}
