import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VolumeCountingSort {
    
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3stocks_T1.csv"; // caminho para o arquivo CSV
        int columnToSort = 6; // índice da coluna a ser ordenada (começando de 0)
        
        try {
            String[] data = readColumn(csvFile, columnToSort);
            countingSort(data);
            writeCSV("b3stocks_volume_countingSortFloat_medioCaso.csv", data);
            System.out.println("Valores ordenados foram escritos no arquivo 'b3stocks_volume_countingSortFloat_medioCaso.csv'.");
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
    
    // Método para ordenar uma matriz de strings numéricas usando o algoritmo Counting Sort
    private static void countingSort(String[] arr) {
        int n = arr.length;
        
        // Encontrando o valor máximo na matriz
        float max = Float.parseFloat(arr[0]);
        for (int i = 1; i < n; i++) {
            float num = Float.parseFloat(arr[i]);
            if (num > max) {
                max = num;
            }
        }
        
        // Criando um array de contagem com base no valor máximo
        int[] count = new int[(int) (max + 1)];
        
        // Contando a frequência de cada elemento
        for (int i = 0; i < n; i++) {
            count[(int) Float.parseFloat(arr[i])]++;
        }
        
        // Modificando o array de contagem para armazenar a posição correta de cada elemento na saída
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        
        // Construindo a matriz de saída
        String[] output = new String[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(int) Float.parseFloat(arr[i])] - 1] = arr[i];
            count[(int) Float.parseFloat(arr[i])]--;
        }
        
        // Copiando a matriz de saída ordenada para a matriz original
        System.arraycopy(output, 0, arr, 0, n);
    }
}
