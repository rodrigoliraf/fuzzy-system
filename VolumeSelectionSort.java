import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VolumeSelectionSort {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV
        int columnToSort = 6; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            selectionSort(data);
            writeCSV("b3stocks_volume_selectionSortFloat_medioCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_volume_selectionSortFloat_medioCaso.csv'.");
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
    
    // Método para ordenar uma matriz de strings numéricas usando o algoritmo Selection Sort
    private static void selectionSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (Float.parseFloat(arr[j]) < Float.parseFloat(arr[minIndex])) {
                    minIndex = j;
                }
            }
            // Troca o elemento mínimo encontrado com o elemento na posição atual
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}
