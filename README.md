Hamiltonian Cryptography
A Java-based desktop application that bridges graph theory and data security. This project allows users to generate cryptographic keys by drawing Hamiltonian paths on an interactive grid, which are then mathematically converted into Lehmer codes to encrypt and decrypt files using a transposition cipher.

🌟 Features
Interactive Graph UI: Draw a custom path across a 4x4 node grid. The path must visit every node exactly once (a Hamiltonian Path).

Auto-Solver (Hint): Stuck? The app includes a randomized backtracking algorithm to instantly generate a valid Hamiltonian path for you.

Lehmer Code Generation: Automatically converts your completed visual path into a mathematical permutation key (Lehmer code).

File Encryption & Decryption: Uses the generated key to secure any file via a block transposition cipher.

Bilingual Interface: Seamlessly switch between English and Hebrew (עברית) UI with the click of a button.

🧠 How It Works
The Graph: The app generates a 4x4 grid of nodes connected to their neighbors (including diagonals).

The Path: The user (or the auto-solver) traces a Hamiltonian path through the graph.

The Key: The sequence of visited nodes is treated as a permutation. This permutation is converted into a Lehmer Code, which provides a unique numerical representation of the path.

The Cipher: When encrypting a file, the file's bytes are read in blocks. The Lehmer code determines how the bytes within each block are shuffled (transposed). Decryption simply applies the inverse permutation to restore the original file.

🚀 Getting Started
Prerequisites
Java Development Kit (JDK): Version 8 or higher.

An IDE (like IntelliJ IDEA, Eclipse) or command-line Java tools.

Installation & Running
Clone the repository:

Bash

git clone https://github.com/yourusername/hamiltonian-cryptography.git
Navigate to the project directory:

Bash

cd hamiltonian-cryptography/src
Compile the Java files:

Bash

javac *.java
Run the application:

Bash

java Main
🎮 How to Use
Generate a Key: * Click and drag your mouse across the blue nodes to connect them. A node turns green once visited.

You must visit every node exactly once.

Alternatively, click Get Hint (רמז / פתרון) to generate a random path automatically.

Encrypt a File:

Once a valid path is drawn and the top status turns green, click Encrypt File (הצפן קובץ).

Select the file you want to secure. A new file with an .encrypted extension will be created.

Decrypt a File:

You must recreate the exact same Hamiltonian path used for encryption.

Click Decrypt File (פענח קובץ) and select the .encrypted file. A new .decrypted file will be generated.

Change Language:

Click the עברית / English button in the bottom right corner to toggle the application's display language.

📁 Project Structure
Main.java - Application entry point.

MainWindow.java - Main GUI frame and layout management.

GraphPanel.java - Custom JPanel for drawing and interacting with the visual graph.

Graph.java - Handles grid generation and the backtracking algorithm for the auto-solver.

Node.java - Data structure for individual graph vertices.

CryptoManager.java - Handles file I/O and the transposition cipher logic.
