package org.s1gma.tutorial;
// Author Is mayursinh1587

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notepad_Enhanced extends JFrame {
    private final JTextArea textArea = new JTextArea();
    private final UndoManager undoManager = new UndoManager();
    private final JLabel status = new JLabel("Ln 1, Col 1");
    private File currentFile;
    private boolean modified = false;

    public Notepad_Enhanced() {
        setTitle("Untitled - Notepad");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setTabSize(4);
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { changed(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { changed(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { changed(); }
        });
        textArea.addCaretListener(e -> updateStatus());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        status.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        add(status, BorderLayout.SOUTH);
        setJMenuBar(createMenuBar());
        createPopupMenu();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { exit(); }
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File"), edit = new JMenu("Edit"), format = new JMenu("Format"), view = new JMenu("View"), help = new JMenu("Help");

        item(file, "New", KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK, e -> newFile());
        item(file, "Open...", KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK, e -> openFile());
        item(file, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK, e -> save());
        item(file, "Save As...", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, e -> saveAs());
        file.addSeparator();
        item(file, "Print...", KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK, e -> printFile());
        file.addSeparator();
        item(file, "Exit", 0, 0, e -> exit());

        item(edit, "Undo", KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK, e -> { if (undoManager.canUndo()) undoManager.undo(); });
        item(edit, "Redo", KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK, e -> { if (undoManager.canRedo()) undoManager.redo(); });
        edit.addSeparator();
        item(edit, "Cut", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK, e -> textArea.cut());
        item(edit, "Copy", KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK, e -> textArea.copy());
        item(edit, "Paste", KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK, e -> textArea.paste());
        item(edit, "Delete", KeyEvent.VK_DELETE, 0, e -> textArea.replaceSelection(""));
        edit.addSeparator();
        item(edit, "Find", KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK, e -> find());
        item(edit, "Replace", KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK, e -> replace());
        item(edit, "Select All", KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK, e -> textArea.selectAll());
        item(edit, "Time/Date", KeyEvent.VK_F5, 0, e -> textArea.insert(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date()), textArea.getCaretPosition()));

        JCheckBoxMenuItem wrap = new JCheckBoxMenuItem("Word Wrap");
        wrap.addActionListener(e -> textArea.setLineWrap(wrap.isSelected()));
        format.add(wrap);
        item(format, "Font...", 0, 0, e -> chooseFont());

        JCheckBoxMenuItem statusBar = new JCheckBoxMenuItem("Status Bar", true);
        statusBar.addActionListener(e -> status.setVisible(statusBar.isSelected()));
        view.add(statusBar);

        item(help, "About Notepad", 0, 0, e -> JOptionPane.showMessageDialog(this,
                "Java Notepad\n\nA Java Swing text editor with common Notepad features.", "About", JOptionPane.INFORMATION_MESSAGE));

        bar.add(file); bar.add(edit); bar.add(format); bar.add(view); bar.add(help);
        return bar;
    }

    private void item(JMenu menu, String name, int key, int modifiers, ActionListener listener) {
        JMenuItem i = new JMenuItem(name);
        if (key != 0) i.setAccelerator(KeyStroke.getKeyStroke(key, modifiers));
        i.addActionListener(listener);
        menu.add(i);
    }

    private void createPopupMenu() {
        JPopupMenu p = new JPopupMenu();
        for (String s : new String[]{"Cut", "Copy", "Paste", "Select All"}) {
            JMenuItem i = new JMenuItem(s);
            i.addActionListener(e -> {
                switch (s) { case "Cut" -> textArea.cut(); case "Copy" -> textArea.copy(); case "Paste" -> textArea.paste(); default -> textArea.selectAll(); }
            });
            p.add(i);
        }
        textArea.setComponentPopupMenu(p);
    }

    private void changed() {
        if (!modified) { modified = true; updateTitle(); }
        updateStatus();
    }

    private void updateTitle() {
        String name = currentFile == null ? "Untitled" : currentFile.getName();
        setTitle((modified ? "*" : "") + name + " - Notepad");
    }

    private void updateStatus() {
        try {
            int pos = textArea.getCaretPosition();
            int line = textArea.getLineOfOffset(pos) + 1;
            int col = pos - textArea.getLineStartOffset(line - 1) + 1;
            status.setText("Ln " + line + ", Col " + col + "    |    " + textArea.getDocument().getLength() + " characters");
        } catch (Exception ignored) {}
    }

    private void newFile() {
        if (!confirmSave()) return;
        textArea.setText(""); currentFile = null; modified = false; updateTitle();
    }

    private void openFile() {
        if (!confirmSave()) return;
        JFileChooser c = new JFileChooser();
        c.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt, *.java)", "txt", "java"));
        if (c.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                textArea.setText(Files.readString(c.getSelectedFile().toPath(), StandardCharsets.UTF_8));
                textArea.setCaretPosition(0); currentFile = c.getSelectedFile(); modified = false; updateTitle(); updateStatus();
            } catch (IOException ex) { error(ex); }
        }
    }

    private boolean save() {
        if (currentFile == null) return saveAs();
        try {
            Files.writeString(currentFile.toPath(), textArea.getText(), StandardCharsets.UTF_8);
            modified = false; updateTitle(); return true;
        } catch (IOException ex) { error(ex); return false; }
    }

    private boolean saveAs() {
        JFileChooser c = new JFileChooser();
        c.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));
        if (c.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return false;
        File f = c.getSelectedFile();
        if (!f.getName().contains(".")) f = new File(f.getAbsolutePath() + ".txt");
        if (f.exists() && JOptionPane.showConfirmDialog(this, "Replace existing file?", "Confirm", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return false;
        currentFile = f;
        return save();
    }

    private boolean confirmSave() {
        if (!modified) return true;
        int a = JOptionPane.showConfirmDialog(this, "Save changes before continuing?", "Notepad", JOptionPane.YES_NO_CANCEL_OPTION);
        if (a == JOptionPane.YES_OPTION) return save();
        return a == JOptionPane.NO_OPTION;
    }

    private void exit() { if (confirmSave()) { dispose(); System.exit(0); } }

    private void find() {
        String q = JOptionPane.showInputDialog(this, "Find:");
        if (q == null || q.isEmpty()) return;
        String all = textArea.getText(); int start = textArea.getCaretPosition(); int i = all.indexOf(q, start);
        if (i < 0 && start > 0) i = all.indexOf(q);
        if (i >= 0) textArea.select(i, i + q.length()); else JOptionPane.showMessageDialog(this, "Text not found.");
    }

    private void replace() {
        JPanel p = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField find = new JTextField(), repl = new JTextField();
        p.add(new JLabel("Find:")); p.add(find); p.add(new JLabel("Replace with:")); p.add(repl);
        if (JOptionPane.showConfirmDialog(this, p, "Replace", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION && !find.getText().isEmpty())
            textArea.setText(textArea.getText().replace(find.getText(), repl.getText()));
    }

    private void chooseFont() {
        String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String name = (String) JOptionPane.showInputDialog(this, "Font:", "Choose Font", JOptionPane.PLAIN_MESSAGE, null, names, textArea.getFont().getFamily());
        if (name != null) textArea.setFont(new Font(name, textArea.getFont().getStyle(), textArea.getFont().getSize()));
    }

    private void printFile() {
        try { textArea.print(); } catch (Exception ex) { error(ex); }
    }

    private void error(Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }

    public static void main(String[] args) { SwingUtilities.invokeLater(() -> new Notepad_Enhanced().setVisible(true)); }
}
