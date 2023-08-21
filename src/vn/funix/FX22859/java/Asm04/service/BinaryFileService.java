package vn.funix.FX22859.java.Asm04.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/*
File BinaryFileService định nghĩa lớp có chức năng cung cấp các dịch vụ đọc/ghi file nhị phân. Bao gồm 2 phương thức static chính:

Phương thức readFile(filePath). Đầu vào là đường dẫn đến thư mục, đầu ra là danh sách đối tượng.
 */
public class BinaryFileService {
    public static <T> List<T> readFile(String fileName) {
        // Chuyển đổi đối tượng sang JSON bằng Gson
        Gson gson = new Gson();
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
//                    String json = gson.toJson(object);
//                    System.out.println(json);

                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException io) {
            System.out.println("IO Exception" + io.getMessage());

        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException :" + e.getMessage());
        }
        return objects;
    }

    public static <T> void writeFile(String fileName, List<T> objects) {
        try (ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {
                file.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
