package vn.funix.FX22859.java.Asm03.models;

import vn.funix.FX22859.java.Asm02.Account;
import vn.funix.FX22859.java.Asm02.Bank;
import vn.funix.FX22859.java.Asm02.Customer;
import vn.funix.FX22859.java.Asm03.Utils;
import vn.funix.FX22859.java.Asm04.dao.AccountDao;
import vn.funix.FX22859.java.Asm04.dao.CustomerDao;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class DigitalBank extends Bank {
    public DigitalCustomer getCustomerById(String cusId) {
        for (Customer customer : getCustomers())
            if (cusId.equals(customer.getCustomerID())) {
                if (customer instanceof DigitalCustomer) {
                    return (DigitalCustomer) customer;
                }
            }
        return null;
    }

    //Kiểm tra account number có 6 số
    public static boolean checkAccNumber(String accNumber) {
        Pattern pattern = Pattern.compile("^\\d{6}$");
        Matcher matcher = pattern.matcher(accNumber);
        boolean isValid = matcher.matches();

        if (!isValid) {
            System.out.println("Số TK không đúng. Vui lòng nhập lại:");
        }

        return isValid;
    }

    public boolean isAccountExisted(String accNumber) {
        for (Customer customer : customers) {
            if (customer.isAccountExisted(accNumber)) {
                return true;
            }
        }
        return false;
    }

    public Account getAccount(String accNumber) {
        for (Customer customer : customers) {
            Account acc = customer.getAccount(accNumber);
            if (acc != null) {
                return acc;
            }
        }
        return null;
    }

    public void withdraw(String customerId, String accNumber, double amount) {
        DigitalCustomer cus = this.getCustomerById(customerId);
        if (cus != null) {
            cus.withdraw(accNumber, amount);
        }
    }

    @Override
    public void addCustomer(String customerName, String customerId) {
        // Kiểm tra xem customerId đã tồn tại trong danh sách hay chưa
        boolean customerExists = customers.stream().anyMatch(customer -> customer.getCustomerID().equals(customerId));

        if (customerExists) {
            System.out.println("Khách hàng " + customerId + " đã tồn tại trong danh sách.");
        } else {
            Customer customer = new Customer(customerName, customerId);
            customers.add(customer);
            System.out.println("Đã thêm khách hàng " + customerId + " vào danh sách.");
        }
    }

    //Method xem thông tin KH
    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        List<Account> accounts = AccountDao.list();
        if (customers.isEmpty()) {
            System.out.println("Chưa có khách hàng nào trong danh sách!");
        } else {
            for (Customer customer : customers) {
                List<Account> filteredAccounts = accounts.stream()
                        .filter(account -> account.getCustomerId().equals(customer.getCustomerID()))
                        .collect(Collectors.toList());
                customer.setAccounts(filteredAccounts);
                customer.displayInformation();
            }
        }
    }

    //Method nhập KH vào CustomerDao
    public void inputCustomers() throws IOException {
        CustomerDao.save(getCustomers());
    }

    public void showCustomer(Customer customer) {
        List<Account> accounts = AccountDao.list();
        List<Account> filteredAccounts = accounts.stream()
                .filter(account -> account.getCustomerId().equals(customer.getCustomerID()))
                .collect(Collectors.toList());
        customer.setAccounts(filteredAccounts);
        customer.displayInformation();
    }

    public void showTransactions(Customer customer) {
        customer.showTransactions();
    }

    public Customer getCustomer(String customerId) {
        return CustomerDao.getCustomer(customerId);
    }

    public Account getAccountBy(String accNumber) {
        return AccountDao.getAccount(accNumber);
    }

    //Method tạo tài khoản
    public void addAccount(Scanner scanner, String customerId) {
        //Kiểm tra KH đã tồn tại chưa
        Customer customer = CustomerDao.getCustomer(customerId);
        while (customer == null) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.next();
            customer = CustomerDao.getCustomer(customerId);
        }

        //Nhập tk
        System.out.println("Nhập số TK gồm 6 chữ số: ");
        String accountNumber = scanner.next();
        boolean isValidSendAcc = checkAccNumber(accountNumber);
        while (!isValidSendAcc) {
            accountNumber = scanner.next();
            isValidSendAcc = checkAccNumber(accountNumber);
        }

        Account existedAccount = AccountDao.getAccountById(customerId, accountNumber);
        while (existedAccount != null) {
            System.out.println("Số TK đã tồn tại");
            System.out.println("Vui lòng nhập lại số TK: ");
            accountNumber = scanner.next();
            existedAccount = AccountDao.getAccountById(customerId, accountNumber);
        }
        System.out.println("Nhập số dư: ");
        double balance = scanner.nextDouble();
        boolean isMinBalance = Account.minBalance(balance);

        while (!isMinBalance) {
            System.out.println("Số dư tối thiểu là 50.000: ");
            System.out.println("Vui lòng nhập lại: ");
            balance = scanner.nextDouble();
            isMinBalance = Account.minBalance(balance);
        }
        //add Account
        SavingsAccount account = new SavingsAccount(customerId, accountNumber, balance);
        AccountDao.addAccount(account);
    }


    //Method chuyển tiền
    public void transfers(Scanner scanner, String customerId) {
        //Kiểm tra KH đã tồn tại chưa
        Customer customer = CustomerDao.getCustomer(customerId);
        while (customer == null) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.next();
            customer = CustomerDao.getCustomer(customerId);
        }

        // Hiển thị thông tin tài khoản của khách hàng
        this.showCustomer(customer);

        //Tài khoản gửi tiền
        System.out.println("Nhập số tài khoản gửi: ");
        String sendAccountNumber = scanner.next();
        boolean isValidSendAcc = checkAccNumber(sendAccountNumber);
        while (!isValidSendAcc) {
            sendAccountNumber = scanner.next();
            isValidSendAcc = checkAccNumber(sendAccountNumber);
        }

        Account existedSendAccount = AccountDao.getAccountById(customerId, sendAccountNumber);
        while (existedSendAccount == null) {
            System.out.println("Số TK không tồn tại");
            System.out.println("Vui lòng nhập lại số TK: ");
            sendAccountNumber = scanner.next();
            existedSendAccount = AccountDao.getAccountById(customerId, sendAccountNumber);
        }

        //Tài khoản nhận
        System.out.println("Nhập số tài khoản nhận: ");
        String receiveAccountNumber = scanner.next();
        boolean isValidReceiveAcc = checkAccNumber(receiveAccountNumber);
        while (!isValidReceiveAcc) {
            receiveAccountNumber = scanner.next();
        }

        Account existedReceiveAccount = AccountDao.getAccount(receiveAccountNumber);
        while (existedReceiveAccount == null || receiveAccountNumber.equals(sendAccountNumber)) {
            System.out.println("Số TK không tồn tại hoặc trùng với tk gửi");
            System.out.println("Vui lòng nhập lại số TK: ");
            receiveAccountNumber = scanner.next();
            existedReceiveAccount = AccountDao.getAccount(receiveAccountNumber);
        }

        //Số tiền chuyển
        System.out.println("Nhập số tiền cần chuyển: ");
        double amount = scanner.nextDouble();
        while (amount <= 50000) {
            System.out.println("Số tiền không hợp lệ.");
            System.out.println("Vui lòng nhập lại số tiền cần chuyển: ");
            amount = scanner.nextDouble();
        }

        //Xác thực
        DecimalFormat decimalFormat = new DecimalFormat("#,##0đ");
        System.out.print("Xác nhận thực hiện chuyển " + decimalFormat.format(amount) + "đ từ tài khoản [" + sendAccountNumber + "] đến tài khoản [" + receiveAccountNumber + "] (Y/N): ");
        String confirmation = scanner.next();

        if (!confirmation.toLowerCase().contains("y")) {
            System.out.println("Giao dịch đã bị hủy.");
        } else {
            //Thực hiện chuyển tiền và cập nhật
            SavingsAccount senderAcc = (SavingsAccount) existedSendAccount;
            SavingsAccount receiveAcc = (SavingsAccount) existedReceiveAccount;
            senderAcc.transfers(receiveAcc, amount);
            AccountDao.update(senderAcc);
            AccountDao.update(receiveAcc);
        }
    }

    //Method rút tiền
    public void withDraw(Scanner scanner, String customerId) {
        //Kiểm tra KH đã tồn tại chưa
        Customer customer = CustomerDao.getCustomer(customerId);
        while (customer == null) {
            System.out.println("Số CCCD không tồn tại.");
            System.out.println("Vui lòng nhập lại CCCD: ");
            customerId = scanner.next();
            customer = CustomerDao.getCustomer(customerId);
        }

        // Hiển thị thông tin tài khoản của khách hàng
        this.showCustomer(customer);

        //Tk rút tiền
        System.out.println("Nhập số tài khoản cần rút tiền: ");
        String accountNumber = scanner.next();
        boolean isValidSendAcc = checkAccNumber(accountNumber);
        while (!isValidSendAcc) {
            accountNumber = scanner.next();
            isValidSendAcc = checkAccNumber(accountNumber);
        }

        Account existedAccount = AccountDao.getAccountById(customerId, accountNumber);
        while (existedAccount == null) {
            System.out.println("Số TK không tồn tại");
            System.out.println("Vui lòng nhập lại số TK: ");
            accountNumber = scanner.next();
            existedAccount = AccountDao.getAccountById(customerId, accountNumber);
        }

        //Số tiền rút
        System.out.println("Nhập số tiền cần rút: ");
        double amount = scanner.nextDouble();
        SavingsAccount acc = (SavingsAccount) existedAccount;
        if (acc.withDraw(amount)) {
            AccountDao.update(acc);
            acc.log(amount);
            //add Transaction
            try {
                acc.createTransaction(amount, true, TransactionType.WITHDRAW);
                System.out.println("Giao dịch thành công");
            } catch (IOException io) {
                System.out.println("IO Exception" + io.getMessage());
            }
        }
    }

}
