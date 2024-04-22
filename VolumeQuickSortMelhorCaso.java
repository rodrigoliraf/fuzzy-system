import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VolumeQuickSortMelhorCaso {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_volume_quickSortFloat_medioCaso.csv"; // caminho para o arquivo CSV
        int columnToSort = 6; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            quickSort(data, 0, data.length - 1);
            writeCSV("b3stocks_volume_quickSortFloat_melhorCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_volume_quickSortFloat_melhorCaso.csv'.");
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
    
    // Método para ordenar uma matriz de strings numéricas usando o algoritmo Quick Sort
    private static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(String[] arr, int low, int high) {
        float pivot = Float.parseFloat(arr[high]);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (Float.parseFloat(arr[j]) < pivot) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
