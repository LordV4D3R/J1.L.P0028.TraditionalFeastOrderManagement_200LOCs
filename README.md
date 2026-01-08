# Traditional Feast Order Management (J1.L.P0028)

## 📝 Giới thiệu
Ứng dụng Console quản lý đặt tiệc truyền thống (Traditional Feast), cho phép quản lý khách hàng, thực đơn và đơn đặt hàng. Dự án được xây dựng theo mô hình **n-Layer** (MVC) tuân thủ các nguyên lý OOP (Encapsulation, Inheritance, Polymorphism).

## 🚀 Tính năng chính
1.  **Register Customer**: Đăng ký khách hàng mới (Validate ID, Tên, SĐT, Email).
2.  **Update Customer**: Cập nhật thông tin khách hàng.
3.  **Search Customer**: Tìm kiếm khách hàng theo tên (gần đúng) và sắp xếp kết quả.
4.  **Display Feast Menus**: Hiển thị thực đơn từ file CSV (Sắp xếp theo giá tăng dần, định dạng khối).
5.  **Place Order**: Đặt tiệc (Kiểm tra trùng lặp đơn hàng dựa trên Khách + Menu + Ngày).
6.  **Update Order**: Cập nhật đơn hàng (Chặn cập nhật ngày quá khứ, tự động tính lại tiền).
7.  **Save to File**: Lưu dữ liệu xuống file nhị phân (`.dat`).
8.  **Display Lists**: Hiển thị danh sách Khách hàng hoặc Đơn hàng (Sắp xếp theo yêu cầu).

## 🛠️ Cấu trúc dự án
Dự án được chia thành các gói (packages) để đảm bảo tính *Low Coupling - High Cohesion*:
* **`model`**: Chứa các thực thể (`Customer`, `Order`, `SetMenu`).
* **`business`**: Chứa logic nghiệp vụ và quản lý danh sách (`Customers`, `Orders`, `SetMenus`, `Workable`).
* **`tools`**: Các tiện ích hỗ trợ (`Inputter`, `Acceptable` - Validate, `FileUtils` - Đọc/Ghi file).
* **`dispatcher`**: Chứa hàm Main và Menu điều khiển (`Main`).

## 📋 Yêu cầu hệ thống
* **Java JDK**: 8 hoặc cao hơn.
* **Maven**: Để quản lý build (hoặc NetBeans Ant).
* **Bảng mã**: UTF-8 (Bắt buộc để hiển thị tiếng Việt).

## ⚙️ Cài đặt và Chạy

### 1. Chuẩn bị dữ liệu
Đảm bảo file **`FeastMenu.csv`** nằm cùng cấp với thư mục `src` (nếu chạy trong IDE) hoặc cùng cấp với file `.jar` (nếu chạy bằng Terminal).
* **Định dạng CSV:** `Code,Name,Price,Ingredients`
* **Lưu ý:** File CSV phải được lưu với bảng mã **UTF-8**.

### 2. Build dự án (Maven)
Tại thư mục gốc của dự án, chạy lệnh:
```bash
mvn clean package
File .jar sẽ được tạo ra trong thư mục target/.

3. Cách chạy (Quan trọng)
Cách 1: Chạy trên NetBeans
Thêm dòng sau vào Project Properties > Run > VM Options để tránh lỗi font tiếng Việt:

Plaintext

-Dfile.encoding=UTF-8
Cách 2: Chạy bằng Terminal (CMD)
Copy file FeastMenu.csv vào thư mục target/ (nơi chứa file .jar).

Mở CMD tại thư mục đó.

Gõ lệnh cấu hình font (nếu dùng Windows):

DOS

chcp 65001
Chạy file jar:

DOS

java -jar TraditionalFeastOrderManagement-1.0-SNAPSHOT.jar
⚠️ Các lỗi thường gặp (Troubleshooting)
1. Lỗi "List is empty" hoặc không hiện Menu:

Kiểm tra xem file FeastMenu.csv đã nằm đúng chỗ chưa.

Kiểm tra xem trong file CSV có dùng dấu chấm phẩy ; thay vì dấu phẩy , không.

2. Lỗi hiển thị tiếng Việt (dấu ? hoặc ô vuông):

Hãy chắc chắn file CSV đã được Save As... -> Encoding: UTF-8.

Khi chạy dòng lệnh, đừng quên chcp 65001.

3. Lỗi "no main manifest attribute":

Hãy chắc chắn file pom.xml đã cấu hình maven-jar-plugin trỏ đúng dispatcher.Main.