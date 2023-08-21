package vn.funix.FX22859.java.Asm04.dao;

import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

public class CustomerDao {

  private final static String FILE_PATH = "/src/vn/funix/FX22859/java/Asm04/store/customers.dat";

  private static String getFilePath() {
    String projectPath = System.getProperty("user.dir");
    return projectPath + FILE_PATH;
  }

  //function lưu danh sách khách hàng vào file
  public static void save(List<Customer> customers) throws IOException {
    BinaryFileService.writeFile(getFilePath(), customers);
  }

  //function lấy ra danh sách khách hàng từ file
  public static List<Customer> list() {
    return BinaryFileService.readFile(getFilePath());
  }

  public static Customer getCustomer(String customerId) {
    return list().stream()
        .filter(customer -> customer.getCustomerID().equals(customerId))
        .findFirst().orElse(null);
  }
}
