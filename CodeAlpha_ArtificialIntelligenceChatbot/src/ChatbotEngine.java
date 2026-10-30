import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A small rule-based chatbot engine.
 *
 * It uses simple NLP-inspired steps: normalization, tokenization, stop-word
 * removal, and keyword scoring. This keeps the logic easy to understand and
 * does not pretend to use an external AI service or a trained ML model.
 */
public class ChatbotEngine {
    private final Map<String, Set<String>> intentKeywords = new HashMap<>();
    private final Map<String, String> responses = new HashMap<>();
    private final Set<String> stopWords = new HashSet<>(Arrays.asList(
            "a", "an", "the", "is", "are", "am", "i", "you", "me", "my",
            "to", "for", "of", "and", "or", "in", "on", "at", "with", "about",
            "what", "how", "can", "please", "tell", "do", "does", "it", "this"
    ));

    public ChatbotEngine() {
        addIntent("greeting", "Hello! Nice to meet you. How can I help today?",
                "hello", "hi", "hey", "morning", "afternoon", "evening");
        addIntent("goodbye", "Goodbye! Have a great day and keep practicing Java.",
                "bye", "goodbye", "later", "exit");
        addIntent("thanks", "You are welcome!", "thanks", "thank", "appreciate");
        addIntent("identity", "I am a Java-based, rule-based AI chatbot made for a CodeAlpha internship project.",
                "name", "who", "bot", "yourself", "identity");
        addIntent("help", "Try asking: 'What is Java?', 'Tell me about this project', or 'How does this chatbot work?'.",
                "help", "commands", "features", "options");
        addIntent("java", "Java is an object-oriented programming language widely used for desktop, web, and enterprise applications.",
                "java", "programming", "object", "oriented", "language");
        addIntent("project", "This project has a Swing GUI and a rule-based response engine. It cleans your message, checks keywords, and selects the best matching answer.",
                "project", "codealpha", "internship", "task", "chatbot");
        addIntent("working", "I convert text to lowercase, remove punctuation, split it into words, ignore common stop words, and score each FAQ intent using keywords.",
                "work", "works", "working", "nlp", "natural", "processing", "algorithm", "rule", "rules");
        addIntent("time", "I do not access live time or the internet, but I am ready to answer my built-in FAQs anytime.",
                "time", "date", "today", "weather", "live");
    }

    private void addIntent(String intent, String response, String... keywords) {
        intentKeywords.put(intent, new HashSet<>(Arrays.asList(keywords)));
        responses.put(intent, response);
    }

    public String getResponse(String message) {
        Set<String> words = extractMeaningfulWords(message);
        String bestIntent = null;
        int highestScore = 0;

        for (Map.Entry<String, Set<String>> entry : intentKeywords.entrySet()) {
            int score = 0;
            for (String keyword : entry.getValue()) {
                if (words.contains(keyword)) {
                    score++;
                }
            }
            if (score > highestScore) {
                highestScore = score;
                bestIntent = entry.getKey();
            }
        }

        if (bestIntent == null) {
            return "I am not sure I understand that yet. Try asking about Java, this project, or type 'help'.";
        }
        return responses.get(bestIntent);
    }

    private Set<String> extractMeaningfulWords(String message) {
        String normalized = message.toLowerCase().replaceAll("[^a-z0-9 ]", " ");
        Set<String> words = new HashSet<>();

        for (String word : normalized.trim().split("\\s+")) {
            if (!word.isBlank() && !stopWords.contains(word)) {
                words.add(word);
            }
        }
        return words;
    }
}
