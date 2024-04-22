import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TickerMergeSort {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV
        int columnToSort = 1; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            mergeSort(data, 0, data.length - 1);
            writeCSV("b3stocks_ticker_mergeSort_medioCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_ticker_mergeSort_medioCaso.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Método para ler uma coluna específica de um arquivo CSV
    private static String[] readColumn(String csvFile, int columnIndex) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csvFile));
        String line;
        String[] column = new String[2000000]; // tamanho inicial arbitrário, ajuste conforme necessário
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
    
    // Método para ordenar uma matriz usando o algoritmo Merge Sort
    private static void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] L = new String[n1];
        String[] R = new String[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
