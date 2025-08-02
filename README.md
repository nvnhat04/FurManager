# Furniture Manager App

Ứng dụng Android giúp quản lý và bán sản phẩm nội thất, hỗ trợ phân quyền người dùng (admin/user) và kết nối với Firebase.

## Tính năng

### Người dùng thông thường (User)
- Đăng ký / Đăng nhập
- Xem danh sách sản phẩm
- Thêm sản phẩm vào giỏ hàng
- Thanh toán

### Quản trị viên (Admin)
- Thêm sản phẩm mới (tên, giá, hình ảnh)
- Xóa sản phẩm khỏi danh sách
- Quản lý danh sách sản phẩm
- Phân quyền người dùng

##  Công nghệ sử dụng

- [Kotlin](https://kotlinlang.org/) + [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Firebase Authentication
- Firebase Firestore
- Firebase Storage (cho ảnh sản phẩm)
- MVVM (Model-View-ViewModel) pattern
- Navigation Component (Compose Navigation)


## Thiết lập & chạy project

1. Clone repository:
```bash
git clone https://github.com/your-username/furniture-manager.git

```
2. Mở project bằng Android Studio

3. Thiết lập Firebase:

- Tạo project Firebase

- Thêm google-services.json vào thư mục app/

- Bật Authentication (Email/Password)

- Tạo Firestore Database

4. Chạy ứng dụng
##  Ảnh màn hình
- **Intro**
  
  <img width="346" height="766" alt="image" src="https://github.com/user-attachments/assets/18f3c4c4-c33a-470b-9dbf-b71bf24a9ede" />

- **Home**
  
  <img width="394" height="854" alt="image" src="https://github.com/user-attachments/assets/fd4cdb08-b9ce-44d7-b916-71c41067c29e" />

- **Cart**
  
  <img width="376" height="836" alt="image" src="https://github.com/user-attachments/assets/519695b8-a98c-4fe1-bd28-f68218618a4b" />

- **Checkout**
  
 <img width="379" height="798" alt="image" src="https://github.com/user-attachments/assets/2017e916-7eea-4391-9fec-b3527dac2e20" />

##  Phân quyền

- role = "user": mặc định

- role = "admin": cấp bởi Admin trong Firestore (collection users, trường role)
## TODO

-  Thêm search sản phẩm

-  Hỗ trợ upload ảnh từ thư viện

-  Thêm xác nhận khi xóa

- Lưu trạng thái giỏ hàng theo người dùng
  
## Góp ý / Liên hệ
Mọi góp ý vui lòng tạo issue hoặc liên hệ qua email: nvnhat04324@gmail.com



