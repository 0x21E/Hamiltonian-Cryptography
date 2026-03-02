import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Main application window. Coordinates between the Graph UI and Crypto logic.
 */
public class MainWindow extends JFrame {
    private GraphPanel graphPanel;
    private CryptoManager cryptoManager;
    private JLabel statusLabel;

    private JButton btnEncrypt, btnDecrypt, btnReset, btnHint, btnLang;

    public MainWindow(Graph graph) {
        cryptoManager = new CryptoManager();

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(statusLabel, BorderLayout.NORTH);

        graphPanel = new GraphPanel(graph, this);
        add(graphPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnEncrypt = new JButton();
        btnDecrypt = new JButton();
        btnReset = new JButton();
        btnHint = new JButton();
        btnLang = new JButton();

        btnEncrypt.addActionListener(e -> handleFileAction(true));
        btnDecrypt.addActionListener(e -> handleFileAction(false));
        btnReset.addActionListener(e -> {
            graphPanel.clearPath();
            refreshTexts();
        });

        btnHint.addActionListener(e -> {
            List<Integer> solvedPath = graph.findHamiltonianPath();
            if (!solvedPath.isEmpty()) {
                graphPanel.showPath(solvedPath);
                refreshTexts();
            } else {
                statusLabel.setText(LanguageManager.get("status_hint_fail"));
                statusLabel.setForeground(Color.RED);
            }
        });

        btnLang.addActionListener(e -> {
            LanguageManager.setLanguage(LanguageManager.getLanguage() == LanguageManager.Language.EN ?
                    LanguageManager.Language.HE : LanguageManager.Language.EN);
            refreshTexts();
        });

        buttonPanel.add(btnEncrypt);
        buttonPanel.add(btnDecrypt);
        buttonPanel.add(btnReset);
        buttonPanel.add(btnHint);
        buttonPanel.add(btnLang);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshTexts();
    }

    /**
     * Updates all UI components based on the current language and path status.
     */
    public void refreshTexts() {
        setTitle(LanguageManager.get("window_title"));
        btnEncrypt.setText(LanguageManager.get("btn_encrypt"));
        btnDecrypt.setText(LanguageManager.get("btn_decrypt"));
        btnReset.setText(LanguageManager.get("btn_reset"));
        btnHint.setText(LanguageManager.get("btn_hint"));
        btnLang.setText(LanguageManager.getLanguage() == LanguageManager.Language.EN ? "עברית" : "English");

        // Logic to display the specific success status when the path is ready
        if (graphPanel.getLastLehmerCode() != null) {
            statusLabel.setText(LanguageManager.get("status_path_complete"));
            statusLabel.setForeground(new Color(34, 139, 34)); // Dark Green
        } else {
            statusLabel.setText(LanguageManager.get("status_start"));
            statusLabel.setForeground(Color.BLACK);
        }

        boolean isHebrew = LanguageManager.getLanguage() == LanguageManager.Language.HE;
        statusLabel.setComponentOrientation(isHebrew ? ComponentOrientation.RIGHT_TO_LEFT : ComponentOrientation.LEFT_TO_RIGHT);
    }

    private void handleFileAction(boolean isEncrypting) {
        List<Integer> lehmer = graphPanel.getLastLehmerCode();
        if (lehmer == null) {
            JOptionPane.showMessageDialog(this, LanguageManager.get("err_no_path"), LanguageManager.get("title_error"), JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File in = fileChooser.getSelectedFile();
            File out = new File(in.getAbsolutePath() + (isEncrypting ? ".encrypted" : ".decrypted"));
            if (cryptoManager.processFile(in, out, lehmer, isEncrypting)) {
                JOptionPane.showMessageDialog(this, LanguageManager.get("msg_saved_as") + " " + out.getName());
            }
        }
    }
}