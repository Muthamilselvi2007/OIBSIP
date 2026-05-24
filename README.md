# AICTE Oasis Infobyte Internship - Java Programming

This repository contains the Java projects completed during my Java Development Internship at Oasis Infobyte (May 2026 - June 2026).

## 👤 Intern Details
* **Intern Name:** Muthamilselvi S
* **Repository Name:** OIBSIP
* **Language Used:** Java (JDK 17+)
* **Internship Program:** AICTE Oasis Infobyte Internship Program

---

## 🚀 Completed Tasks & Features

### 1. Task 1: Online Reservation System
A comprehensive command-line terminal system simulating a train ticket booking engine.
* **New User Sign-Up & Login:** Dynamic credential registration and authentication logic.
* **Auto-fill Train Name:** Automatically fetches train names (e.g., Chennai Mail, Cheran Express) based on the input Train Number.
* **PNR Generation:** Automatically generates a unique, incremental 5-digit PNR number upon successful insertion.
* **Ticket Cancellation:** Validates data using PNR lookup and handles user-confirmed cancellations securely.

### 2. Task 2: Number Guessing Game
An interactive GUI-based game developed using Java Swing components.
* **GUI Layout:** Built using windows-native `JOptionPane` dialog boxes for an enhanced user experience.
* **Smart Scoring System:** Rewards users with higher scores (up to 100 points/round) for fast, early guesses.
* **Smart Hints:** Provides contextual "Higher!" or "Lower!" guiding directions after every incorrect guess.
* **Robust Error Handling:** Employs `try-catch` structures blockading non-integer entries from crashing the active runtime.

### 3. Task 4: Online Examination System
An automated online testing portal featuring background scheduling tasks.
* **Multi-threaded Live Timer Engine:** Implements a background `Thread` structure with `volatile` state markers ensuring a precise 120-second active test clock countdown.
* **Automated Force Exit:** Instantly halts exam progress, locks screen inputs, and processes final scores automatically when the timer reaches zero.
* **Secure Session Controllers:** Built-in modular capabilities allowing real-time profile password updates and secure session logouts.

### 4. Task 5: Digital Library Management System
An enterprise-grade console application dividing administration analytics from member operations.
* **Dual-Module Controls:** Dedicated dashboards separating structural Admin commands from self-service User actions.
* **Automated Late Return Fines:** Implements an algorithm tracking delay intervals, automatically levying a Rs. 5.00/day penalization rate.
* **Advance Booking Matrix:** Allows immediate reservation queueing on titles currently marked as issued.
* **Admin Revenue Reports:** Uses Java Streams filtering schemas to compute library inventory load, active distribution ratios, and total generated cash fine revenues.

---

## 🛠️ How to Compile & Run
1. Clone this repository or download the `.java` files:
   ```bash
   git clone https://github.com
   ```
2. Open your terminal or Command Prompt in the directory containing the source files.
3. Compile any specific task using:
   ```bash
   javac MuthamilselviS_Task1.java
   ```
4. Run the compiled class file using:
   ```bash
   java MuthamilselviS_Task1
   ```
