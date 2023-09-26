/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author huypd
 */
public class Algorithm {
    public void normalizeText(String inputFilePath, String outputFilePath) {
        try {
            // Đọc văn bản từ tệp đầu vào
            String inputText = readFile(inputFilePath);

            // Chuẩn hóa văn bản
            String normalizedText = normalizeText(inputText);

            // Ghi văn bản đã chuẩn hóa vào tệp đầu ra
            writeFile(outputFilePath, normalizedText);

            System.out.println("Text normalization completed. Result saved to " + outputFilePath);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private String readFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n");
            }
        }
        return text.toString();
    }

    private void writeFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
    //chuẩn hóa file
    public String normalizeText(String input) {
        // Chuẩn hóa văn bản bằng cách áp dụng các quy tắc đã chỉ định
        String[] lines = input.split("\n"); // Tách văn bản thành các dòng

        StringBuilder str = new StringBuilder();

        // Cờ để theo dõi xem dòng hiện tại có phải là dòng đầu tiên không
        boolean isFirstLine = true;

        for (String line : lines) {
            // Loại bỏ khoảng trắng ở đầu và cuối mỗi dòng
            line = line.trim();

            if (!line.isEmpty()) { // Bỏ qua các dòng trống
                if (!isFirstLine) {
                    str.append(" "); // Thêm khoảng trắng giữa các dòng không trống
                }

                // Chuẩn hóa dòng hiện tại
                line = normalizeLine(line);

                str.append(line);
                isFirstLine = false;
            }
        }

        // Thêm dấu chấm vào cuối văn bản đã chuẩn hóa
        str.append(".");

        return str.toString();
    }
    
    //chuẩn hóa từng dòng
    private String normalizeLine(String line) {
        // Loại bỏ các khoảng trắng thừa giữa các từ
        line = line.replaceAll("\\s+", " ");

        // Chuẩn hóa dấu câu: một khoảng trắng sau dấu phẩy, dấu chấm, và dấu hai chấm
        line = line.replaceAll("\\s*,\\s*", ", ");
        line = line.replaceAll("\\s*\\.\\s*", ". ");
        line = line.replaceAll("\\s*:\\s*", ": ");

        // Viết hoa ký tự đầu tiên của câu
        line = capitalizeFirstCharacter(line);

        // Loại bỏ khoảng trắng trước và sau dấu ngoặc kép
        line = line.replaceAll("\\s*\"\\s*", "\"");

        return line;
    }
    //in hoa chữ cái đầu
    private String capitalizeFirstCharacter(String line) {
        Pattern pattern = Pattern.compile("(^|\\.[\\s\"])([a-z])");
        Matcher matcher = pattern.matcher(line);

        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group(1) + matcher.group(2).toUpperCase());
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
