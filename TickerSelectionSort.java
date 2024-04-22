import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TickerSelectionSort {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV de entrada
        String sortedCsvFile = "b3stocks_ticker_selectionSort_medioCaso.csv"; // caminho para o arquivo CSV de saída ordenado
        int columnToSort = 1; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            selectionSort(data);
            writeCSV(sortedCsvFile, data);
            System.out.println("Dados ordenados foram gravados em " + sortedCsvFile);
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
        return Arrays.copyOf(column, index);
    }
    
    // Método para ordenar uma matriz usando o algoritmo Selection Sort
    private static void selectionSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
    
    // Método para escrever os dados ordenados em um novo arquivo CSV
    private static void writeCSV(String filePath, String[] data) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (String item : data) {
            bw.write(item);
            bw.newLine();
        }
        bw.close();
    }
}
