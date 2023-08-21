package vn.funix.FX22859.java.Asm01;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

public class Asm1 {

    static Scanner sc;
    static HashMap<String, String> danhSachDonViMap = new HashMap<>();

    public static void main(String[] args) {

        danhSachDonViMap.put("Hà Nội", "001");
        danhSachDonViMap.put("Hà Giang", "002");
        danhSachDonViMap.put("Cao Bằng", "004");
        danhSachDonViMap.put("Bắc Kạn", "006");
        danhSachDonViMap.put("Tuyên Quang", "008");
        danhSachDonViMap.put("Lào Cai", "010");
        danhSachDonViMap.put("Điện Biên", "011");
        danhSachDonViMap.put("Lai Châu", "012");
        danhSachDonViMap.put("Sơn La", "014");
        danhSachDonViMap.put("Yên Bái", "015");
        danhSachDonViMap.put("Hòa Bình", "017");
        danhSachDonViMap.put("Thái Nguyên", "019");
        danhSachDonViMap.put("Lạng Sơn", "020");
        danhSachDonViMap.put("Quảng Ninh", "022");
        danhSachDonViMap.put("Bắc Giang", "024");
        danhSachDonViMap.put("Phú Thọ", "025");
        danhSachDonViMap.put("Vĩnh Phúc", "026");
        danhSachDonViMap.put("Bắc Ninh", "027");
        danhSachDonViMap.put("Hải Dương", "030");
        danhSachDonViMap.put("Hải Phòng", "031");
        danhSachDonViMap.put("Hưng Yên", "033");
        danhSachDonViMap.put("Thái Bình", "034");
        danhSachDonViMap.put("Hà Nam", "035");
        danhSachDonViMap.put("Nam Định", "036");
        danhSachDonViMap.put("Ninh Bình", "037");
        danhSachDonViMap.put("Thanh Hóa", "038");
        danhSachDonViMap.put("Nghệ An", "040");
        danhSachDonViMap.put("Hà Tĩnh", "042");
        danhSachDonViMap.put("Quảng Bình", "044");
        danhSachDonViMap.put("Quảng Trị", "045");
        danhSachDonViMap.put("Thừa Thiên Huế", "046");
        danhSachDonViMap.put("Đà Nẵng", "048");
        danhSachDonViMap.put("Quảng Nam", "049");
        danhSachDonViMap.put("Quảng Ngãi", "051");
        danhSachDonViMap.put("Bình Định", "052");
        danhSachDonViMap.put("Phú Yên", "054");
        danhSachDonViMap.put("Khánh Hòa", "056");
        danhSachDonViMap.put("Ninh Thuận", "058");
        danhSachDonViMap.put("Bình Thuận", "060");
        danhSachDonViMap.put("Kon Tum", "062");
        danhSachDonViMap.put("Gia Lai", "064");
        danhSachDonViMap.put("Đắk Lắk", "066");
        danhSachDonViMap.put("Đắk Nông", "067");
        danhSachDonViMap.put("Lâm Đồng", "068");
        danhSachDonViMap.put("Bình Phước", "070");
        danhSachDonViMap.put("Tây Ninh", "072");
        danhSachDonViMap.put("Bình Dương", "074");
        danhSachDonViMap.put("Đồng Nai", "075");
        danhSachDonViMap.put("Bà Rịa - Vũng Tàu", "077");
        danhSachDonViMap.put("Hồ Chí Minh", "079");
        danhSachDonViMap.put("Long An", "080");
        danhSachDonViMap.put("Tiền Giang", "082");
        danhSachDonViMap.put("Bến Tre", "083");
        danhSachDonViMap.put("Trà Vinh", "084");
        danhSachDonViMap.put("Vĩnh Long", "086");
        danhSachDonViMap.put("Đồng Tháp", "087");
        danhSachDonViMap.put("An Giang", "089");
        danhSachDonViMap.put("Kiên Giang", "091");
        danhSachDonViMap.put("Cần Thơ", "092");
        danhSachDonViMap.put("Hậu Giang", "093");
        danhSachDonViMap.put("Sóc Trăng", "094");
        danhSachDonViMap.put("Bạc Liêu", "095");
        danhSachDonViMap.put("Cà Mau", "096");

        sc = new Scanner(System.in);
        System.out.println("+-------+-----------------+----------+");
        System.out.println("|  NGÂN HÀNG SỐ  |  FX22859@v1.0.0   |");
        System.out.println("+-------+-----------------+----------+");
        System.out.println("|  1. Nhập CCCD                      |");
        System.out.println("|  0. Thoát                          |");
        System.out.println("+-------+-----------------+----------+");
        System.out.println("Chức năng: ");
        chucNang();
    }

    private static void chucNang() {
        int input;
        do {
            try {
                input = sc.nextInt(); // Người dùng nhập giá trị từ bàn phím và lưu vào biến input
                if (input == 1) {
                    maXacThuc();
                    break; // Thoát khỏi vòng lặp do-while
                }
                if (input == 0) {
                    System.out.println("Exit!");
                    break;
                }
                System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
            } catch (Exception e) {
                sc.nextLine(); // Đọc và loại bỏ giá trị không hợp lệ từ bàn phím
                System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");

            }
        } while (true);
    }

    private static void maXacThuc() {
        System.out.println("Vui lòng chọn chế độ mã bảo mật:");
        System.out.println("1. EASY (3 ký tự số)");
        System.out.println("2. HARD (6 ký tự số và chữ)");
        int securityMode;
        do {
            try {
                securityMode = sc.nextInt();
                if (securityMode == 1) {
                    maXacThucEasy();
                    break;
                }
                if (securityMode == 2) {
                    maXacThucHard();
                    break;
                }
                System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");
                sc.nextLine();
            }
        } while (true);


    }

    private static void maXacThucEasy() {
        Random random = new Random();
        int code = random.nextInt(900) + 100;
        System.out.println("Nhap ma xac thuc: " + code);
        int inputCode = -1;
        do {
            if (sc.hasNextInt()) {
                inputCode = sc.nextInt();
                if (inputCode != code) {
                    System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
                }
            } else {
                sc.next();
                System.out.println("Ma xac thuc khong hop le. Vui long nhap lai.");
            }
        } while (inputCode != code);
        checkCCCD();
    }

    private static void maXacThucHard() {
        Random random = new Random();
        String code = generateHardCode();
        System.out.println("Nhập mã xác thực: " + code);
        String inputCode = "";

        do {
            inputCode = sc.nextLine();

            if (!inputCode.equals(code) && !inputCode.isEmpty()) {
                System.out.println("Mã xác thực không đúng. Vui lòng thử lại.");
            }
        } while (!inputCode.equals(code));

        checkCCCD();
    }

    private static String generateHardCode() {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Chuỗi chứa các ký tự hợp lệ cho mã xác thực
        StringBuilder sb = new StringBuilder(); // StringBuilder để xây dựng mã xác thực
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(
                    validChars.length()); // Sinh một số ngẫu nhiên trong khoảng từ 0 đến độ dài của chuỗi ký tự hợp lệ
            char randomChar = validChars.charAt(
                    index); //Lấy ký tự tại vị trí ngẫu nhiên trong chuỗi ký tự hợp lệ
            sb.append(randomChar); // Thêm ký tự vào StringBuilder
        }

        return sb.toString(); // Trả về mã xác thực được xây dựng
    }

    private static void checkCCCD() {
        System.out.println("Vui lòng nhập CCCD (12 ký tự): ");
        String inputCCCD;
        do {
            inputCCCD = sc.nextLine();
            if (!inputCCCD.isEmpty()) {
                if (!inputCCCD.equals("no") && (inputCCCD.length() != 12 || !isFirstThreeDigitsValid(
                        inputCCCD) || !inputCCCD.matches("0-9"))) {
                    System.out.println(
                            "CCCD không hợp lệ. Vui lòng kiểm tra và nhập lại hoặc 'no' để thoát.");
                } else if (inputCCCD.equals("no")) {
                    System.out.println("Thoát!");
                    return; //// Kết thúc chương trình
                }
            }
        } while (inputCCCD.length() != 12 || !isFirstThreeDigitsValid(inputCCCD) || !inputCCCD.matches("0-9"));
        checkNoiSinhNamSinhGioiTinh(inputCCCD);
    }

    private static boolean isFirstThreeDigitsValid(String inputCCCD) {
        String firstThreeDigits = inputCCCD.substring(0, 3);
        for (String key : danhSachDonViMap.keySet()) { //// Duyệt qua mảng danhSachDonVi
            String value = danhSachDonViMap.get(key);
            if (value.equals(
                    firstThreeDigits)) { //// So sánh phần tử có index 1 của mỗi phần tử trong danhSachDonVi với firstThreeDigits
                return true;
            }
        }
        return false;
    }

    private static void checkNoiSinhNamSinhGioiTinh(String cccd) {
        System.out.println("| 1. Kiểm tra nơi sinh");
        System.out.println("| 2. Kiểm tra năm sinh, giới tính");
        System.out.println("| 3. Kiểm tra số ngẫu nhiên");
        System.out.println("| 0. Thoát");
        int input;
        do {
            try {
                input = sc.nextInt();
                if (input == 1) {
                    checkNoiSinh(cccd);
                    break;
                }
                if (input == 2) {
                    checkGioiTinhNamSinh(cccd);
                    break;
                }
                if (input == 3) {
                    checkSoNgauNhien(cccd);
                    break;
                }
                if (input == 0) {
                    System.out.println("Thoát!");
                    break;
                }
                System.out.println("Số bạn nhập không đúng, vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu hoặc số bạn nhập không hợp lệ. Vui lòng nhập lại.");
                sc.nextLine();
            }
        } while (true);
    }

    private static void checkNoiSinh(String cccd) {
        String maSo = cccd.substring(0, 3); // Lấy 3 ký tự đầu tiên của chuỗi CCCD

        for (String key : danhSachDonViMap.keySet()) {
            String value = danhSachDonViMap.get(key);
            String tenDonVi = key; // Lấy tên đơn vị từ phần tử hiện tại trong danhSachDonVi
            String maDonVi = value; // Lấy mã đơn vị từ phần tử hiện tại trong danhSachDonVi

            if (maDonVi.equals(maSo)) {
                System.out.println("Nơi sinh: " + tenDonVi);
                break;
            }
        }

        checkNoiSinhNamSinhGioiTinh(cccd);
    }


    static String[][] array = {
            {"1900", "Nam"},
            {"1900", "Nữ"},
            {"2000", "Nam"},
            {"2000", "Nữ"},
            {"2100", "Nam"},
            {"2100", "Nữ"},
            {"2200", "Nam"},
            {"2200", "Nữ"},
            {"2300", "Nam"},
            {"2300", "Nữ"},
    };

    private static void checkGioiTinhNamSinh(String cccd) {
        String maGioiTinh = cccd.substring(3, 4);
        String maNamSinh = cccd.substring(4, 6);

        String nam = array[Integer.parseInt(maGioiTinh)][0];
        String gioiTinh = array[Integer.parseInt(maGioiTinh)][1];
        int namNumber = Integer.parseInt(nam); // Chuyển đổi năm từ kiểu chuỗi sang kiểu số nguyên
        int namSinh =
                namNumber + Integer.parseInt(maNamSinh); //Năm sinh bằng cách cộng năm và maNamSinh

        System.out.println("Giới tính: " + gioiTinh + " | Năm sinh: " + namSinh);

        checkNoiSinhNamSinhGioiTinh(cccd);
    }

    private static void checkSoNgauNhien(String cccd) {
        String lastSixDigits = cccd.substring(6, 12);
        System.out.println("6 số cuối ngẫu nhiên: " + lastSixDigits);
        checkNoiSinhNamSinhGioiTinh(cccd);
    }

}





























