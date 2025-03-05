package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.Product;

public class Main {
    public static void main(String[] args) {

        String path = "C:\\Users\\samue\\OneDrive\\Documentos\\Java Projects\\files";

        List<Product> products = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + "\\source-file.csv"))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    String name = data[0].trim();
                    Double price = Double.parseDouble(data[1].trim());
                    Integer quantity = Integer.parseInt(data[2].trim());

                    Product product = new Product(name, price, quantity);
                    products.add(product);
                }
            }

            List<String> data2 = new ArrayList<>();
            for (Product product : products) {
                String data = (product.getName() + "," + product.totalPrice(product.getPrice(), product.getQuantity()));

                data2.add(data);

            }

            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "\\out.csv"))) {

                for (String d : data2) {
                    bufferedWriter.write(d);
                    bufferedWriter.newLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
