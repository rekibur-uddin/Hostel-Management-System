# 🏨 Hostel Management System 🏫

A **Java-based Hostel Management System** built using **NetBeans IDE** and **MySQL**, designed to simplify and automate hostel-related tasks for educational institutions.
This project is part of the **BCA Final Year Project** and includes the **complete ER diagram**, **database structure**, and a **detailed project report**.

---

## 📌 Project Overview

The **Hostel Management System** provides a secure and user-friendly platform where:

* 🧑‍💼 **Admins** can manage students, hostel rooms, attendance, payments, and help requests.
* 🧑‍🎓 **Students** can register, log in, mark attendance, pay hostel fees, and submit help requests.

The project uses **Java Swing** for the GUI and **MySQL** for backend data storage, ensuring reliability and fast performance.

---

## ✨ Features

### 🧑‍💼 Admin Panel

* Add / Update / Delete Students & Admins
* Manage Hostel Rooms
* Track Attendance & Payments
* View and Respond to Help Requests

### 🧑‍🎓 Student Module

* Register & Login
* Mark Attendance
* Pay Hostel Fees
* Submit Help Requests
* View Personal Details

### 🧠 Database Integration

* 🔐 Secure MySQL Database
* 📝 Real-time Data Management
* 📊 ER Diagrams + Full Project Documentation

---

## 🛠️ Tech Stack

| Component        | Technology            |
| ---------------- | --------------------- |
| 💻 Frontend      | Java Swing (NetBeans) |
| 🗄️ Backend      | MySQL                 |
| 🧠 IDE           | NetBeans              |
| 📝 Language      | Java (JDK 8+)         |
| 🔐 DB Connection | JDBC                  |

---

## 📂 Project Structure

```
Hostel-Management-System/
├─ nbproject/
├─ src/
│  └─ hostalmanagement/ # Java source files (Admin & Student Modules)
├─ dist/
├─ database/
│  └─ hostalmanagementsystem.sql
├─ report/
│  ├─ Project-Report.pdf
│  └─ ER-Diagram.pdf
├─ screenshots/
├─ README.md
└─ .gitignore
```

---

## 📊 ER Diagram

📌 **[View ER Diagram (PDF)](./report/ER-Diagram.pdf)**

---

## 📘 Project Report

📄 **[Download Full Project Report (PDF)](./report/Project-Report.pdf)**

The report includes:

* Problem Definition
* Objective
* Existing & Proposed System
* System Design (DFD, ERD, Schema)
* Implementation Details
* Output Screenshots
* Conclusion

---

## 🧰 Database

### **Database Name:** `hostalmanagementsystem`

#### **Tables and Sample Structure:**

```sql
CREATE DATABASE hostalmanagementsystem;
USE hostalmanagementsystem;

-- Users Table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'student') NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mobile_no VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Admins Table
CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    mobile_no VARCHAR(15),
    designation VARCHAR(255),
    role ENUM('admin', 'student') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Students Table
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    full_name VARCHAR(255) NOT NULL,
    mobile_no VARCHAR(15),
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    class VARCHAR(255),
    father_name VARCHAR(255),
    mother_name VARCHAR(255),
    email VARCHAR(255),
    room VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Attendance Table
CREATE TABLE attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (username) REFERENCES students(username)
);

-- Payments Table
CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (username) REFERENCES students(username)
);

-- Help Requests Table
CREATE TABLE help_requests (
    request_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (username) REFERENCES students(username)
);
```

💡 Admin Login Example:
username: admin
password: admin123

---

## 🧰 How to Run Locally

1️⃣ Clone the Repository

```bash
git clone https://github.com/rekibur-uddin/Hostel-Management-System.git
cd Hostel-Management-System
```

2️⃣ Open in NetBeans

* Open NetBeans IDE
* Go to File → Open Project
* Select the project folder

3️⃣ Import Database

* Open phpMyAdmin or MySQL Workbench
* Create schema `hostalmanagementsystem`
* Import `database/hostalmanagementsystem.sql`

4️⃣ Update DB Credentials

* In source code, set JDBC URL, username, password as per local MySQL

5️⃣ Run Project

* Click ▶️ Run Project in NetBeans

---

## 🖼️ Screenshots

| Login Page                        | Student Dashboard                               |
| --------------------------------- | ----------------------------------------------- |
| ![Login](./screenshots/login.png) | ![Student Dashboard](./screenshots/student.png) |

| Admin Dashboard                   | Help Requests                                     |
| --------------------------------- | ------------------------------------------------- |
| ![Admin](./screenshots/admin.png) | ![Help Requests](./screenshots/help_requests.png) |

---

## ✍️ Author

👤 Rekibur Uddin
📧 [Visit My Portfolio](https://rekiburuddin.blogspot.com)

---

## ⭐ Support

If you like this project, consider giving it a ⭐ on [GitHub](https://github.com/rekibur-uddin/Hostel-Management-System) 🙌.
