/*
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * Programa de Pós-Graduação em Ciências da Computação - PROPG
 * Disciplinas: Projeto e Análise de Algoritmos
 * Prof Alexandre Gonçalves da Silva 
 *
 * Baseado nos slides 28 da aula do dia 22/09/2017  
 *
 * Problema de Seleção
 * Dado um conjunto A de n números inteiro e um inteiro i, 
 * determinar o i-ésimo menor elemento de A.
 * Executamos PARTICIONE e este rearranja o vetor e devolve um índice k tal que
 *    A[1...k−1] <= A[k] < A[k+1...n]
 * 
 * Eis a idéia do algoritmo:
 *   Se i = k, então o pivô A[k] é o i-ésimo menor! Achei!!
 *   Se i < k, então o i-ésimo menor está em A[1...k − 1];
 *   Se i > k, então o i-ésimo menor estáa em A[k+1...n].
 *
 * Atenção:
 * Vetor em java inicia em 0, os algoritmos consideram início em 1.
 * A subtraçào de -1 ocorre somente no local de acesso ao vetor ou matriz 
 * para manter a compatibilidade entre os algoritmos.
 * 
 */

/**
 * @author Osmar de Oliveira Braz Junior
 */
public class Principal {
        
    /**
     * Realiza a troca de posição de dois elementos do vetor.
     * 
     * @param A Vetor que contem os dados
     * @param i Primeira posição de troca
     * @param j Segunda posição de torca
     */
    public static void troca(int[] A, int i, int j) {
        int aux = A[i-1];
        A[i-1] = A[j-1];
        A[j-1] = aux;
    }

    /**
     * Particione encontra o pivo.
     * 
     * Complexidade de tempo Theta(n).
     * T(n) = Theta(2n + 4) + O(2n) = Theta(n) 
     * Slide 68.     
     * 
     * @param A Vetor com os dados
     * @param p Início do vetor
     * @param r Fim do vetor
     * @return o pivo da troca
     */
    public static int particione(int A[], int p, int r) {
        //x é o "pivô"
        int x = A[r-1];                       //Theta(1)
        int i = p - 1;                      //Theta(1)
        for (int j = p; j <= r - 1; j++) {  //Theta(n)
            if (A[j-1] <= x) {                //Theta(n)
                i = i + 1;                  //O(n)
                troca(A, i, j);             //O(n)
            }
        }
        troca(A, i + 1, r);                 //Theta(1)
        return i + 1;                       //Theta(1)
    }
        
    /**
     * Recebe um vetor A[1...n] e devolve o valor do i-ésimo menor elemento de A.
     * 
     * A complexidade de tempo no pior caso 
     * n = r - p + 1    
     * T(n) = max{T(k-1), T(n-k)} + Theta(n)
     * T(n) = Theta(n^2)
     * 
     * A complexidade para o caso médio é O(n)
     * 
     * @param A Vetor com os valores
     * @param p Posição inicial do vetor
     * @param r Posição final do vetor
     * @param i Posição desejada do vetor
     * @return Um valor do elemento da posição i do vetor
     */
    public static int selectNL(int A[], int p, int r, int i) {
        if (p==r){                                  //Theta(1)
            return A[p-1];                            //O(1)
        }   
        int q = particione(A, p, r);                //Theta(n)
        int k = q - p + 1;                          //Theta(n)
        if (i==k){ //Pivô é o i-ésimo menor!        //Theta(n)
            return A[q-1];                            //O(1)   
        } else {                                
            if (i < k){                             //O(1)   
                return selectNL(A, p, q-1, i);      //T(k-1)  
            } else {
                return selectNL(A, q+1, r, i-k);    //T(n-k)
            }
        }        
    }

    public static void main(String[] args) {
        
        //Vetor dos dados    
        int A[] = {50, 70, 60, 90, 10, 30, 20, 40};

        //Quantidade de elementos
        int r = A.length;

        //Posição do i-ésimo termo
        int i = 1;
        int menor = selectNL(A, 1, r, i);        
        System.out.println("1o menor:" + menor);        
        
        //Posição do i-ésimo termo
        i = 3;
        menor = selectNL(A, 1, r, i);        
        System.out.println("2o Menor:" + menor);

        //Posição do maior termo
        i = r;
        menor = selectNL(A, 1, r, i);
        System.out.println("no maior:" + menor);                 
    }    
}