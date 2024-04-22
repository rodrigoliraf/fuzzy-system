import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class T1 {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\rodri\\Desktop\\Estudos\\Projeto LEDA\\b3_stocks_1994_2020.csv";
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            StringBuilder convertedData = new StringBuilder();
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    convertedData.append(line).append("\n"); // Salva o cabeçalho original
                    headerSkipped = true;
                    continue; // Ignora o cabeçalho
                }
                String[] dados = line.split(","); // Assumindo que o separador seja a vírgula
                String[] data = dados[0].split("-");
                if (data.length == 3) { // Verifica se a data foi dividida corretamente
                    String dataConvertida = data[2] + "/" + data[1] + "/" + data[0];
                    convertedData.append(dataConvertida).append(",").append(dados[1]); // Adiciona dados convertidos
                    for (int i = 2; i < dados.length; i++) {
                        convertedData.append(",").append(dados[i]); // Adiciona os dados restantes
                    }
                    convertedData.append("\n");
                } else {
                    System.out.println("Erro ao dividir a data: " + line);
                }
            }

            FileWriter csvWriter = new FileWriter("b3stocks_T1.csv");
            csvWriter.write(convertedData.toString());
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}