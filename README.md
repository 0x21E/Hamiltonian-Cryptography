# Hamiltonian Cryptography 🔐

An interactive Java application that combines **Graph Theory** with **Data Security**. Users generate cryptographic keys by drawing Hamiltonian paths on a grid, which are then used to encrypt or decrypt files using a transposition cipher.

## 🌟 Features

* **Interactive Graph UI:** Draw paths on a 4x4 grid. A path is only valid if it visits every node exactly once (Hamiltonian Path).
* **Auto-Solver (Hint):** Includes a backtracking algorithm to find a valid path automatically if you get stuck.
* **Lehmer Code Logic:** Converts visual permutations into mathematical Lehmer codes to serve as encryption keys.
* **File Encryption:** Secures any file type (text, images, PDFs) by shuffling data blocks.
* **Bilingual Support:** Full UI localization for both **English** and **Hebrew (עברית)**.

---

## 🚀 Getting Started

### Prerequisites
* **Java JDK 8** or higher.
* A terminal or an IDE (IntelliJ IDEA, Eclipse, etc.).

### Installation
1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/your-username/hamiltonian-cryptography.git](https://github.com/your-username/hamiltonian-cryptography.git)
    ```
2.  **Navigate to the project directory:**
    ```bash
    cd hamiltonian-cryptography
    ```
3.  **Compile the source code:**
    ```bash
    javac *.java
    ```
4.  **Run the application:**
    ```bash
    java Main
    ```

---

## 🎮 How to Use

1.  **Create a Key:**
    * **Manual:** Click and drag your mouse through the nodes. Nodes turn **green** when visited.
    * **Automatic:** Click the **Get Hint / רמז** button to let the algorithm find a path for you.
2.  **Verify the Path:**
    * Once all nodes are visited, the status bar at the top will turn green and display: *"Status: Hamiltonian path created! Key is ready."*
3.  **Encrypt:**
    * Click **Encrypt File / הצפן קובץ**.
    * Choose a file from your computer. The app will create a `.encrypted` version of that file.
4.  **Decrypt:**
    * Ensure the **exact same path** is drawn on the graph.
    * Click **Decrypt File / פענח קובץ** and select your encrypted file.

---

## 🛠️ Technical Overview

* **Graph Structure:** A 4x4 grid where each node is connected to its horizontal, vertical, and diagonal neighbors.
* **Pathfinding:** Uses a **Recursive Backtracking** algorithm with randomized neighbor selection to provide different hints every time.
* **Encryption Algorithm:** A **Block Transposition Cipher**. It uses the Lehmer code (derived from the Hamiltonian path) to shuffle the order of bytes within each block of data.

---

## 📁 File Structure

* `Main.java` - Launches the application.
* `MainWindow.java` - Controls the GUI layout and language switching.
* `GraphPanel.java` - Handles the interactive drawing logic.
* `Graph.java` - Contains the math for Lehmer codes and backtracking.
* `CryptoManager.java` - Manages byte-level file processing.
* `LanguageManager.java` - Stores all English and Hebrew strings.
