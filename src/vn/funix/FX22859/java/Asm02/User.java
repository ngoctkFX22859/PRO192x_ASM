package vn.funix.FX22859.java.Asm02;

import java.io.Serializable;
import java.util.HashMap;


public class User implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String name;
    private String customerID;
    private static HashMap<String, String> danhSachDonViMap = new HashMap<>();

    static {
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
    }

    public User(String name, String customerID) {
        this.name = name;
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public static boolean isFirstThreeDigitsValid(String inputCCCD) {
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


}
