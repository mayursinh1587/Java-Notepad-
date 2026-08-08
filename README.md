# 📝 Java Notepad

A simple and feature-rich **Notepad application built using Java Swing**. This project provides the essential text-editing features of a traditional Notepad application, along with additional functionality such as Find & Replace, Undo/Redo, Word Wrap, Font customization, Status Bar, and Print support.

---

## 📌 Project Overview

**Java Notepad** is a desktop-based text editor developed using **Java and Swing**.

The application provides an easy-to-use graphical user interface for creating, editing, opening, saving, and printing text documents.

The project is suitable for beginners who want to understand:

* Java Swing
* GUI application development
* Event handling
* File handling
* Text editing
* Menu creation
* Keyboard shortcuts
* Exception handling
* Object-Oriented Programming

---

## ✨ Features

### 📄 File Management

* **New** – Create a new blank document.
* **Open** – Open existing `.txt` and `.java` files.
* **Save** – Save the current document.
* **Save As** – Save the document with a different name or location.
* **Print** – Print the current document.
* **Exit** – Close the application safely.
* **Unsaved Changes Warning** – Warns the user before closing or creating a new document when changes have not been saved.

### ✏️ Editing

* **Undo**
* **Redo**
* **Cut**
* **Copy**
* **Paste**
* **Delete**
* **Select All**
* **Find**
* **Replace**
* **Time/Date**

### 🎨 Formatting

* **Word Wrap**
* **Font Selection**
* **Font Size**
* **Bold**
* **Italic**
* **Custom Text Font**

### 📊 Status Bar

The application displays:

* Current line number
* Current column number
* Total character count

Example:

```text
Ln 5, Col 12 | 245 characters
```

### 🖱️ Context Menu

Right-clicking inside the text area provides quick access to:

* Cut
* Copy
* Paste
* Select All

### 📁 File Support

Currently supported file types:

```text
.txt
.java
```

Files are read and written using **UTF-8 encoding**.

---

## 🛠️ Technologies Used

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java              | Programming Language            |
| Java Swing        | Graphical User Interface        |
| Java AWT          | Events, Fonts and GUI utilities |
| Java NIO          | File Reading and Writing        |
| Java I/O          | File Handling                   |
| Swing UndoManager | Undo and Redo                   |
| Git               | Version Control                 |
| GitHub            | Project Hosting                 |

---

## 🖥️ Application Interface

The application contains:

```text
+----------------------------------------------------------+
| File   Edit   Format   View   Help                       |
+----------------------------------------------------------+
|                                                          |
|                                                          |
|                  Text Editing Area                       |
|                                                          |
|                                                          |
|                                                          |
+----------------------------------------------------------+
| Ln 1, Col 1 | 0 characters                              |
+----------------------------------------------------------+
```

---

## 📂 Project Structure

```text
Java-Notepad/
│
├── src/
│   └── org/
│       └── s1gma/
│           └── tutorial/
│               └── Notepad.java
│
├── README.md
│
└── .gitignore
```

> The exact folder structure may vary depending on the IDE or Java project setup.

---

## 🚀 How to Run

### Method 1: Using IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Open the project.
3. Make sure Java/JDK is configured.
4. Open `Notepad.java`.
5. Run the `main()` method.
6. The Notepad window will open.

---

### Method 2: Using Eclipse

1. Open Eclipse.
2. Import the Java project.
3. Open `Notepad.java`.
4. Right-click the file.
5. Select:

```text
Run As → Java Application
```

---

### Method 3: Using Command Prompt

Navigate to the source directory:

```bash
cd src
```

Compile the program:

```bash
javac org/s1gma/tutorial/Notepad.java
```

Run the application:

```bash
java org.s1gma.tutorial.Notepad
```

---

## ⌨️ Keyboard Shortcuts

| Shortcut           | Action           |
| ------------------ | ---------------- |
| `Ctrl + N`         | New              |
| `Ctrl + O`         | Open             |
| `Ctrl + S`         | Save             |
| `Ctrl + Shift + S` | Save As          |
| `Ctrl + P`         | Print            |
| `Ctrl + Z`         | Undo             |
| `Ctrl + Y`         | Redo             |
| `Ctrl + X`         | Cut              |
| `Ctrl + C`         | Copy             |
| `Ctrl + V`         | Paste            |
| `Ctrl + F`         | Find             |
| `Ctrl + H`         | Replace          |
| `Ctrl + A`         | Select All       |
| `F5`               | Insert Time/Date |

---

## 📖 How It Works

### 1. Creating a New File

The **New** option clears the current text area and creates a new untitled document.

If the current document contains unsaved changes, the application asks whether the user wants to save them first.

---

### 2. Opening a File

The **Open** option displays a file chooser.

The user can select:

```text
.txt
.java
```

The selected file is read using UTF-8 encoding and displayed inside the text area.

---

### 3. Saving a File

The **Save** option stores the current text in the selected file.

If the document has not been saved before, the application automatically opens the **Save As** dialog.

---

### 4. Save As

**Save As** allows the user to:

* Select a location
* Enter a filename
* Save the document as a new file

If no extension is provided, `.txt` is automatically added.

---

### 5. Undo and Redo

The application uses Java Swing's `UndoManager` to provide editing history.

Users can undo or redo text changes using:

```text
Ctrl + Z
Ctrl + Y
```

---

### 6. Find

The **Find** feature searches for text inside the document.

Keyboard shortcut:

```text
Ctrl + F
```

If the text is found, it is automatically selected.

---

### 7. Replace

The **Replace** feature allows the user to replace matching text with new text.

Keyboard shortcut:

```text
Ctrl + H
```

---

### 8. Word Wrap

Word Wrap automatically moves long lines onto the next line so that horizontal scrolling is not required.

It can be enabled or disabled from:

```text
Format → Word Wrap
```

---

### 9. Font Customization

The Font option allows the user to customize:

* Font family
* Font size
* Bold
* Italic

---

### 10. Status Bar

The Status Bar provides useful information about the current document.

It displays:

```text
Line Number
Column Number
Character Count
```

---

### 11. Print

The Print option uses Java's built-in printing support to print the current document.

Keyboard shortcut:

```text
Ctrl + P
```

---

## 🧩 Main Java Components

The application is primarily built using Java Swing components.

### JFrame

Used as the main application window.

```java
public class Notepad extends JFrame
```

### JTextArea

Used as the main text-editing area.

```java
private final JTextArea textArea = new JTextArea();
```

### JMenuBar

Used to create the application menu.

Menus include:

```text
File
Edit
Format
View
Help
```

### JFileChooser

Used for opening and saving files.

### JScrollPane

Provides scrolling functionality for the text area.

### UndoManager

Provides Undo and Redo functionality.

### JOptionPane

Used for:

* Messages
* Confirmation dialogs
* Find dialogs
* Error messages
* About information

---

## 📁 Supported File Types

The application currently supports:

```text
Text Files (*.txt)
Java Files (*.java)
```

The file filter allows users to easily select supported files.

---

## 🔐 File Handling

Files are stored using **UTF-8 encoding**.

This allows the application to correctly handle a wide range of characters and text content.

Example:

```java
Files.readString(file.toPath(), StandardCharsets.UTF_8);
```

and:

```java
Files.writeString(
    currentFile.toPath(),
    textArea.getText(),
    StandardCharsets.UTF_8
);
```

---

## ⚠️ Error Handling

The application handles common errors such as:

* Unable to open a file
* Unable to save a file
* Printing errors
* Invalid file operations

Users receive an error dialog when an operation fails.

---

## 🎯 Learning Objectives

This project demonstrates practical knowledge of:

1. Java programming
2. Object-Oriented Programming
3. Java Swing
4. GUI development
5. Event handling
6. File handling
7. Exception handling
8. Keyboard shortcuts
9. Menu creation
10. Text processing
11. Undo/Redo implementation
12. Java NIO
13. Git and GitHub

---

## 🔮 Future Improvements

The project can be extended with additional features such as:

* 🌙 Dark Mode
* 🎨 Multiple themes
* 🔢 Line numbers beside the text area
* 🔎 Case-sensitive Find
* 🔎 Find Next / Find Previous
* 🔄 Replace All
* 📑 Multiple tabs
* 📋 Recent Files
* ⭐ Favorites
* 📝 Auto-save
* 💾 Backup files
* 🔤 More font controls
* 📊 Word count
* 🔍 Zoom In / Zoom Out
* 🌐 Encoding selection
* 📁 Drag-and-drop files
* 🖥️ Full-screen mode
* 🧑‍💻 Syntax highlighting for Java
* ⚙️ Preferences/settings

---

## 📸 Screenshots

Add screenshots of your application here.

Example:

```markdown
![Main Window](screenshots/main-window.png)

![File Menu](screenshots/file-menu.png)

![Edit Menu](screenshots/edit-menu.png)
```

Recommended screenshots:

1. Main Notepad window
2. File menu
3. Edit menu
4. Find/Replace dialog
5. Font dialog
6. About dialog

---

## 🧪 Example Usage

### Creating a Document

```text
File → New
```

Type:

```text
Hello World!

This is my Java Notepad application.
```

Then save it using:

```text
File → Save
```

---

### Opening a Document

```text
File → Open
```

Select a `.txt` or `.java` file.

The content will appear inside the editor.

---

## 📦 Requirements

Before running the project, make sure you have:

* Java JDK installed
* Java-compatible IDE or Command Prompt
* Windows/Linux/macOS operating system

Recommended:

```text
JDK 8 or later
```

---

## 👨‍💻 Author

**Mayursinh Chauhan**

Computer Science Student
Interested in:

* Software Development
* Java
* Python
* Web Development
* Full-Stack Development
* Building real-world projects

---

## ⭐ Project Purpose

This project was created as a learning project to understand Java GUI development and file handling while building a practical desktop application.

If you find this project useful, consider giving the repository a ⭐ **Star** on GitHub.

---

## 📄 License

This project is available for educational and personal use.

You are free to study, modify, and improve the source code.

---

## 🙌 Acknowledgements

This project was developed using standard Java libraries, primarily:

```text
Java Swing
Java AWT
Java I/O
Java NIO
Java Undo Framework
```

---

## 🚀 Future Goal

The goal is to continue improving this application into a more complete desktop text editor with modern UI, tabs, themes, syntax highlighting, search tools, and additional productivity features.

---

### ⭐ If you like this project

```text
⭐ Star this repository
🍴 Fork the repository
💻 Improve the project
📢 Share it with others
```

**Made with ❤️ and Java ☕**
