import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

/**
 * A beginner-friendly Swing user interface for the CodeAlpha chatbot.
 */
public class ChatbotApp extends JFrame {
    private final JTextArea conversationArea;
    private final JTextField inputField;
    private final ChatbotEngine chatbot;

    public ChatbotApp() {
        chatbot = new ChatbotEngine();

        setTitle("CodeAlpha AI Chatbot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 520);
        setMinimumSize(new Dimension(520, 400));
        setLocationRelativeTo(null);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(37, 99, 235));
        header.setBorder(BorderFactory.createEmptyBorder(14, 18, 14, 18));
        JLabel title = new JLabel("CodeAlpha AI Chatbot");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(title, BorderLayout.WEST);

        conversationArea = new JTextArea();
        conversationArea.setEditable(false);
        conversationArea.setLineWrap(true);
        conversationArea.setWrapStyleWord(true);
        conversationArea.setFont(new Font("SansSerif", Font.PLAIN, 15));
        conversationArea.setMargin(new java.awt.Insets(14, 14, 14, 14));
        conversationArea.setText("Bot: Hello! I am a rule-based Java chatbot.\n"
                + "Bot: Ask me about Java, this project, help, or say hi.\n\n");

        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 15));
        inputField.addActionListener(this::sendMessage);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this::sendMessage);

        JPanel inputPanel = new JPanel(new BorderLayout(10, 0));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(12, 14, 14, 14));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        add(new JScrollPane(conversationArea), BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void sendMessage(ActionEvent event) {
        String userMessage = inputField.getText().trim();
        if (userMessage.isEmpty()) {
            return;
        }

        conversationArea.append("You: " + userMessage + "\n");
        conversationArea.append("Bot: " + chatbot.getResponse(userMessage) + "\n\n");
        inputField.setText("");
        conversationArea.setCaretPosition(conversationArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatbotApp().setVisible(true));
    }
}
