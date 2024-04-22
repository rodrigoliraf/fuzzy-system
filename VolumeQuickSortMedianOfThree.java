import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VolumeQuickSortMedianOfThree {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV
        int columnToSort = 1; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            quickSortMedianOfThree(data, 0, data.length - 1);
            writeCSV("b3stocks_volume_quickSortMedianOfThree_medioCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_volume_quickSortMedianOfThree_medioCaso.csv'.");
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
    
    // Método para ordenar uma matriz usando o algoritmo Quick Sort com mediana de três
    private static void quickSortMedianOfThree(String[] arr, int low, int high) {
        if (low < high) {
            if (high - low + 1 > 3) {
                int mid = low + (high - low) / 2;
                int pivotIndex = medianOfThree(arr, low, mid, high);
                swap(arr, pivotIndex, high);
            }
            int pivotIndex = partition(arr, low, high);
            quickSortMedianOfThree(arr, low, pivotIndex - 1);
            quickSortMedianOfThree(arr, pivotIndex + 1, high);
        }
    }
    
    private static int medianOfThree(String[] arr, int low, int mid, int high) {
        String a = arr[low];
        String b = arr[mid];
        String c = arr[high];
        if ((a.compareTo(b) < 0 && b.compareTo(c) < 0) || (c.compareTo(b) < 0 && b.compareTo(a) < 0))
            return mid;
        else if ((b.compareTo(a) < 0 && a.compareTo(c) < 0) || (c.compareTo(a) < 0 && a.compareTo(b) < 0))
            return low;
        else
            return high;
    }
    
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
