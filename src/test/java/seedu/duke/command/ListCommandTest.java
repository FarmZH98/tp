package seedu.duke.command;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.EmailManager;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.email.Archive;
import seedu.duke.email.Deleted;
import seedu.duke.email.Draft;
import seedu.duke.email.Email;
import seedu.duke.email.Inbox;
import seedu.duke.email.Junk;
import seedu.duke.email.Sent;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ListCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private ArrayList<Email> emails = new ArrayList<>();
    private EmailManager emailManager = new EmailManager();
    private Ui ui = new Ui();
    private Storage storage = new Storage();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        emails.add(new Archive("123@gmail.com", "456@gmail.com", "S1", "2012-01-01", "C1"));
        emails.add(new Deleted("123@gmail.com", "456@gmail.com", "S2", "2012-01-02", "C2"));
        emails.add(new Email("123@gmail.com", "456@gmail.com", "S0", "2012-01-02", "C2"));
        emails.add(new Draft("123@gmail.com", "456@gmail.com", "S3", "2012-01-02", "C2"));
        emails.add(new Inbox("123@gmail.com", "456@gmail.com", "S4", "2012-01-02", "C2"));
        emails.add(new Junk("123@gmail.com", "456@gmail.com", "S5", "2012-01-02", "C2"));
        emails.add(new Sent("123@gmail.com", "456@gmail.com", "S6", "2012-01-02", "C2"));
        emailManager = new EmailManager(emails);

    }

    @Test
    void execute_valid_list_Archive_success() {
        new ListCommand("list archive").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Archive][UNREAD]\n"
                        + "|| Subject: S1\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_valid_list_Deleted_success() {
        new ListCommand("list deleted").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Deleted][UNREAD]\n"
                        + "|| Subject: S2\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_valid_list_Draft_success() {
        new ListCommand("list draft").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Draft][UNREAD]\n"
                        + "|| Subject: S3\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_valid_list_Inbox_success() {
        new ListCommand("list inbox").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Inbox][UNREAD]\n"
                        + "|| Subject: S4\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_valid_list_Junk_success() {
        new ListCommand("list junk").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Junk][UNREAD]\n"
                        + "|| Subject: S5\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_valid_list_Sent_success() {
        new ListCommand("list sent").execute(emailManager, ui, storage);

        Assertions.assertEquals("1. [Sent][UNREAD]\n"
                        + "|| Subject: S6\n"
                        + "|| From: 123@gmail.com --> To: 456@gmail.com",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void execute_invalid_list_message() {
        new ListCommand("list -1").execute(emailManager, ui, storage);

        Assert.assertEquals("OOPS!!! The Email type that you enter is invalid."
                + System.lineSeparator()
                + "It must be one of: [emails, inbox, archive, deleted, draft, junk, sent]",
                outputStreamCaptor.toString()
                .trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
