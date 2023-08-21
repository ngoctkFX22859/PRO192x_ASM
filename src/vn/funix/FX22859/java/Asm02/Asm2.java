package vn.funix.FX22859.java.Asm02;

import java.time.chrono.MinguoChronology;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Asm2 {


    private static final Bank bank = new Bank();
    public static String AUTHOR = "FX22859";
    public static String VERSION = "V2.0.0";

    public static void main(String[] args) {
        menu();
        chucNang();
    }

    private static void menu() {
        System.out.println("+----------+----------------------+--------+");
        System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@" + VERSION + "            |");
        System.out.println("+----------+----------------------+--------+");
        System.out.println(" 1. Thêm khách hàng");
        System.out.println(" 2. Thêm tài khoản cho khách hàng");
        System.out.println(" 3. Hiển thị danh sách khách hàng");
        System.out.println(" 4. Tìm theo CCCD");
        System.out.println(" 5. Tìm theo tên khách hàng");
        System.out.println(" 0. Thoát");
        System.out.println("+----------+----------------------+--------+");
        System.out.println("Chức năng: ");
        chucNang();
    }

    public static void chucNang() {
        Scanner sc = new Scanner(System.in);
        int input;
        try {
            while (true) {
                input = sc.nextInt(); // Người dùng nhập giá trị từ bàn phím và lưu vào biến input
                if (input == 1) {
                    addCustomers();
                    menu();
                }
                if (input == 2) {
                    addAccountForCustomer();
                    menu();
                }
                if (input == 3) {
                    showCustomersList();
                    menu();
                }
                if (input == 4) {
                    findByCCCD();
                    menu();
                }
                if (input == 5) {
                    findByName();
                    menu();
                }
                if (input == 0) {
                    System.out.println("Exit!");
                    System.exit(0); // Kết thúc chương trình
                }
                System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
            }
        } catch (Exception e) {
            e.printStackTrace(); //phương thức printStackTrace() sẽ in ra thông báo lỗi vào console, giúp xác định nguyên nhân và vị trí của lỗi trong chương trình.
            sc.nextLine(); // Đọc và loại bỏ giá trị không hợp lệ từ bàn phím
            System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");
        }
    }

    public static void addCustomers() {
        Scanner sc = new Scanner(System.in);
        String name, customerId;
        System.out.println("Nhập tên khách hàng: ");
        name = sc.next();
        System.out.println("Vui lòng nhập CCCD (12 ký tự): ");
        customerId = sc.next();
        while (customerId.length() != 12 || !User.isFirstThreeDigitsValid(customerId)
                || customerId.matches(".*[a-zA-Z].*")) {
            System.out.println(
                    "CCCD không hợp lệ. Vui lòng kiểm tra và nhập lại hoặc 'no' để thoát.");
            customerId = sc.next();
            if (customerId.equals("no") || customerId.equals("No")) {
                System.out.println("Thoát!");
                System.exit(0); // Kết thúc chương trình
            }
        }
        boolean isExisted = bank.isCustomerExisted(customerId);
        while (isExisted) {
            System.out.println("Số CCCD bị trùng");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = sc.next();
            isExisted = bank.isCustomerExisted(customerId);
        }
        bank.addCustomer(name, customerId);
        System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách.");
    }

    public static void addAccountForCustomer() {
        Scanner sc = new Scanner(System.in);
        String cusId;
        String accNumbers;
        double balance;
        System.out.println("Nhập số CCCD của khách hàng: ");
        cusId = sc.nextLine();
        boolean isExisted = bank.isCustomerExisted(cusId);
        while (!isExisted) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
//            addAccountForCustomer();
            cusId = sc.nextLine();
            isExisted = bank.isCustomerExisted(cusId);
//            break;
        }
        System.out.println("Nhập số TK gồm 6 chữ số: ");
        accNumbers = sc.nextLine();
        Pattern pattern = Pattern.compile("^\\d{6}$");
        while (!(pattern.matcher(accNumbers).find())) {
            System.out.println("Số TK không đúng. Vui lòng nhập lại: ");
            accNumbers = sc.nextLine();
        }
        System.out.println("Nhập số dư: ");
        balance = sc.nextDouble();
        while (!Account.minBalance(balance)) {
            System.out.println("Số dư tối thiểu là 50.000: ");
            System.out.println("Vui lòng nhập lại: ");
            balance = sc.nextDouble();
        }

        Customer currentCustomer = null;

        for (Customer customer : bank.getCustomers()) {
            boolean isExistedAccount = customer.isAccountExisted(accNumbers);
            while (isExistedAccount) {
                System.out.println("Số TK bị trùng");
                System.out.println("Vui lòng nhập lại số TK: ");
                accNumbers = sc.next();
                isExistedAccount = customer.isAccountExisted(accNumbers);
            }
            if (customer.getCustomerID().equals(cusId)) {
                currentCustomer = customer;
            }
        }

        Account account = new Account(accNumbers, balance);
        if (currentCustomer != null) {
            currentCustomer.addAccount(account);
        }
        System.out.println("Thêm tài khoản thành công!");
    }

    public static void showCustomersList() {
        for (Customer customer : bank.getCustomers()) {
            customer.displayInformation();
        }
    }

    public static void findByCCCD() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vui lòng nhập số CCCD cần tìm");
        String cusId = sc.nextLine();
        boolean found = false;
        for (Customer customer : bank.getCustomers()) {
            if (customer.getCustomerID().equals(cusId)) {
                customer.displayInformation();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy tài khoản. Vui lòng kiểm tra lại CCCD đã nhập");
        }
    }

    public static void findByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vui lòng nhập tên cần tìm");
        String name = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Customer customer : bank.getCustomers()) {
            if (customer.getName().toLowerCase().contains(name)) {
                customer.displayInformation();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy tài khoản. Vui lòng kiểm tra lại tên đã nhập");
        }
    }
}


