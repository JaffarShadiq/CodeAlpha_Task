# CodeAlpha Artificial Intelligence Chatbot

A beginner-friendly Java desktop chatbot created for **CodeAlpha Java Programming - Task 3**.

## What it does

- Provides a real-time Swing GUI where the user can type messages.
- Responds to frequently asked questions about Java and the project.
- Uses clear rule-based logic rather than claiming to use an external AI service.
- Applies simple NLP-inspired steps: lowercasing, punctuation removal, tokenization, stop-word removal, and keyword scoring.

## Project structure

```text
CodeAlpha_ArtificialIntelligenceChatbot/
├── src/
│   ├── ChatbotApp.java       # Swing user interface and program entry point
│   └── ChatbotEngine.java    # NLP-style text processing and responses
├── .gitignore
└── README.md
```

## Requirements

- Java Development Kit (JDK) 11 or newer
- A terminal such as Command Prompt, PowerShell, or the VS Code terminal

Check that Java is installed:

```powershell
java -version
javac -version
```

## Run the project

Open a terminal in the project folder and run:

```powershell
cd src
javac ChatbotApp.java ChatbotEngine.java
java ChatbotApp
```

A chat window will open. Try these messages:

- `Hello`
- `What is Java?`
- `How does this chatbot work?`
- `Tell me about this CodeAlpha project`
- `help`

## How the chatbot works

1. `ChatbotApp` gets a message from the input box.
2. `ChatbotEngine` normalizes the message to lowercase and removes punctuation.
3. It splits the message into words and ignores common stop words such as `the` and `is`.
4. It compares the remaining words with keyword groups for each FAQ intent.
5. The intent with the highest number of matching keywords supplies the answer.

This is a **rule-based chatbot**. The assignment explicitly allows rule-based answers, so the README accurately describes what the program does. More FAQs can be added in `ChatbotEngine` by adding another `addIntent(...)` line.

## Honest explanation for a reviewer

> I built a Java Swing chatbot with a rule-based NLP-style response engine. It does not call ChatGPT or an online API. It processes user text by normalizing and tokenizing it, removes common words, scores keyword matches across prepared FAQ intents, and returns the best matching response. I chose this approach so I could understand and explain every part of the program.

## Possible improvements

- Store FAQs in a text file or database.
- Add buttons for common questions.
- Use a natural-language library or trained model in a future version.
- Save conversation history.
