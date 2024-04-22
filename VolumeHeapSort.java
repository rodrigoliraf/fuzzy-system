import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VolumeHeapSort {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV
        int columnToSort = 6; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            heapSort(data);
            writeCSV("b3stocks_ticker_heapSortFloat_medioCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_ticker_heapSortFloat_medioCaso.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para ler uma coluna específica de um arquivo CSV
    private static String[] readColumn(String csvFile, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        String[] column = new String[100]; // tamanho inicial arbitrário, ajuste conforme necessário
        int index = 0;
        while ((line = br.readLine()) != null) {
            String[] row = line.split(",");
            if (row.length > columnIndex) {
                column[index++] = row[columnIndex];
            }
        }
        br.close();
        // Redimensiona o array para remover espaços vazios
        String[] result = new String[index];
        System.arraycopy(column, 0, result, 0, index);
        return result;
    }
    
    // Método para escrever os dados ordenados em um novo arquivo CSV
    private static void writeCSV(String fileName, String[] data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (String item : data) {
            bw.write(item);
            bw.newLine();
        }
        bw.close();
    }
    
    // Método para ordenar uma matriz de strings numéricas usando o algoritmo Heap Sort
    private static void heapSort(String[] arr) {
        int n = arr.length;
        
        // Construindo o heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extraindo elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Movendo a raiz atual para o final
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // Chamando o heapify no heap reduzido
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(String[] arr, int n, int i) {
        int largest = i; // Inicializando o maior como a raiz
        int left = 2 * i + 1; // Índice do filho da esquerda
        int right = 2 * i + 2; // Índice do filho da direita
        
        // Se o filho da esquerda for maior que a raiz
        if (left < n && Float.parseFloat(arr[left]) > Float.parseFloat(arr[largest])) {
            largest = left;
        }
        
        // Se o filho da direita for maior que o maior até agora
        if (right < n && Float.parseFloat(arr[right]) > Float.parseFloat(arr[largest])) {
            largest = right;
        }
        
        // Se o maior não for a raiz
        if (largest != i) {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            
            // Recursivamente heapify a subárvore afetada
            heapify(arr, n, largest);
        }
    }
}
