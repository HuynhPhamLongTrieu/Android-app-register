# 🔐 Android Login & Register App

Ứng dụng Android đơn giản cho phép người dùng đăng ký tài khoản mới và đăng nhập vào hệ thống với giao diện thân thiện và quản lý phiên đăng nhập tự động.

## 📋 Tính năng chính

- ✅ **Đăng ký tài khoản mới**: Người dùng có thể tạo tài khoản với username và password
- 🔐 **Đăng nhập**: Xác thực người dùng với thông tin đã đăng ký
- 💾 **Lưu trữ cục bộ**: Sử dụng SQLite database để lưu trữ thông tin người dùng
- 🔄 **Quản lý phiên**: Tự động duy trì trạng thái đăng nhập
- 🏠 **Màn hình chính**: Giao diện home sau khi đăng nhập thành công
- 🛡️ **Bảo mật**: Kiểm tra tính duy nhất của username

## 🏗️ Kiến trúc ứng dụng

### Activities
- **MainActivity**: Màn hình đăng nhập chính
- **RegisterActivity**: Màn hình đăng ký tài khoản
- **HomeActivity**: Màn hình chính sau khi đăng nhập

### Components chính
- **UserDatabaseHelper**: Quản lý SQLite database cho người dùng
- **SessionManager**: Quản lý phiên đăng nhập và SharedPreferences

## 🛠️ Công nghệ sử dụng

- **Ngôn ngữ**: Kotlin
- **Platform**: Android (API 24+)
- **Database**: SQLite
- **Architecture**: Native Android Activities
- **UI**: Material Design Components
- **Storage**: SharedPreferences cho session management


## 📂 Cấu trúc dự án

```
Login/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/login/
│   │   │   │   ├── MainActivity.kt          # Màn hình đăng nhập
│   │   │   │   ├── RegisterActivity.kt      # Màn hình đăng ký
│   │   │   │   ├── HomeActivity.kt          # Màn hình chính
│   │   │   │   ├── UserDatabaseHelper.kt    # Quản lý database
│   │   │   │   └── SessionManager.kt        # Quản lý phiên
│   │   │   ├── res/
│   │   │   │   ├── layout/                  # XML layout files
│   │   │   │   ├── values/                  # Strings, colors, themes
│   │   │   │   └── drawable/                # Icons và images
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/                     # Integration tests
│   │   └── test/                            # Unit tests
│   ├── build.gradle.kts                     # App-level build config
│   └── proguard-rules.pro
├── gradle/
│   └── libs.versions.toml                   # Version catalog
├── build.gradle.kts                         # Project-level build config
└── settings.gradle.kts
```

## 🔗 Dependencies chính

```kotlin
// Core Android libraries
androidx-core-ktx = "1.10.1"
androidx-appcompat = "1.6.1"
androidx-activity = "1.8.0"
androidx-constraintlayout = "2.1.4"

// Material Design
material = "1.10.0"

// Testing
junit = "4.13.2"
androidx-junit = "1.1.5"
androidx-espresso-core = "3.5.1"
```

## 💡 Tính năng chi tiết

### 🔐 Hệ thống đăng nhập
- Xác thực thông qua SQLite database
- Kiểm tra input validation
- Hiển thị thông báo lỗi phù hợp
- Chuyển hướng tự động sau đăng nhập thành công

### 📝 Đăng ký tài khoản
- Kiểm tra username trùng lặp
- Validation dữ liệu đầu vào
- Lưu trữ an toàn trong SQLite
- Thông báo kết quả đăng ký

### 💾 Quản lý dữ liệu
- **SQLite Database**: Lưu trữ thông tin user
- **SharedPreferences**: Quản lý session và trạng thái đăng nhập
- **Auto-login**: Tự động đăng nhập khi mở lại app

## 🛡️ Bảo mật

- Password được lưu trữ dưới dạng plain text (chỉ phù hợp cho demo)
- Username có tính unique trong database
- Session management thông qua SharedPreferences
- Input validation để tránh SQL injection cơ bản


