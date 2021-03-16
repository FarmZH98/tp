package seedu.duke;

import seedu.duke.command.ArchiveCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ComposeCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.NumberCommand;
import seedu.duke.command.ReadCommand;
import seedu.duke.command.SendCommand;
import seedu.duke.email.Email;
import seedu.duke.email.Archive;
import seedu.duke.exceptions.InvalidIndexException;

import java.util.ArrayList;

public class Parser {
    private static EmailManager emailManager;
    private Command cmd;

    public Parser() {
        cmd = null;
    }

    public Command getCmd() {
        return cmd;
    }

    public void parse(String userInputString) {
        if (userInputString.toLowerCase().matches("^(list)[ ].*$")) {
            cmd = new ListCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase("bye")) {
            cmd = new ExitCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase("help")) {
            cmd = new HelpCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(read)[ ].*$")) {
            cmd = new ReadCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(delete)[ ].*$")) {
            cmd = new DeleteCommand(userInputString);
        } else if (userInputString.toLowerCase().matches("^(archive)[ ].*$")) {
            cmd = new ArchiveCommand(userInputString);
        } else if (userInputString.equalsIgnoreCase(("compose"))) {
            cmd = new ComposeCommand((userInputString));
        } else if (userInputString.toLowerCase().matches("^(send)[ ].*$")) {
            cmd = new SendCommand(userInputString);
        } else if (userInputString.toLowerCase().startsWith("number")) {
            cmd = new NumberCommand(userInputString);
        } else {
            cmd = null;
        }
    }

    public static int extractIndex(String userInput) throws InvalidIndexException {
        try {
            String[] cmdArg = userInput.split(" ", 2);
            String args = cmdArg[1].trim();
            int indexShow = Integer.parseInt(args);
            return indexShow;
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }

    }


    public static ArrayList<Email> getTypeToList(String userInput) {
        String[] cmdArg = userInput.split(" ", 2);
        String emailType = cmdArg[1].trim();
        ArrayList<Email> emailsToPrint = null;
        switch (emailType) {
        case ("emails"):
            emailsToPrint = emailManager.getAllEmails();
            break;
        case ("archive"):
            emailsToPrint = emailManager.getArchivedEmails();
            break;
        case ("deleted"):
            emailsToPrint = emailManager.getDeletedEmails();
            break;
        case ("draft"):
            emailsToPrint = emailManager.getDraftEmails();
            break;
        case ("inbox"):
            emailsToPrint = emailManager.getInboxEmails();
            break;
        case ("junk"):
            emailsToPrint = emailManager.getJunkEmails();
            break;
        case ("sent"):
            emailsToPrint = emailManager.getSentEmails();
            break;
        default:
        }
        return emailsToPrint;
    }
}
