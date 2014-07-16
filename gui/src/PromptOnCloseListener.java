import javax.swing.*;
import java.awt.event.*;

/**
 * Created by dabrady on 7/16/2014.
 */
public class PromptOnCloseListener extends WindowAdapter {

    //---- Class & instance variables ----\\

    public static final Object[] OPTIONS_A = new Object[2],
                                 OPTIONS_B = new Object[3];
    public static final String DEFAULT_PROMPT = "Do you really wish to exit? Any unsaved data will be lost.",
                               DEFAULT_TITLE  = "Confirm Exit";
    private static JCheckBox dontAsk = null;

    private Object[] options = OPTIONS_A;
    private String prompt    = DEFAULT_PROMPT,
                   title     = DEFAULT_TITLE;

    //---- Constructors ----\\

    /**
     * Creates a PromptOnCloseListener with default prompt, title, and options.
     */
    public PromptOnCloseListener () {
        dontAsk = new JCheckBox( "Do not ask me again" );
        dontAsk.addItemListener( new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // If the selectable item that triggered the event was the checkbox
                if ( e.getItemSelectable() == PromptOnCloseListener.dontAsk ) {
                    // Set the flag variable to the state of the check box: true if checked, false if unchecked.
                    PFrame.setExitOnClose( PromptOnCloseListener.dontAsk.isSelected() );
                }
            }
        } );

        OPTIONS_A[0] = "Yes";
        OPTIONS_A[1] = "No";

        OPTIONS_B[0] = "Yes";
        OPTIONS_B[1] = "No";
        OPTIONS_B[2] = dontAsk;
    }

    /**
     * Creates a PromptOnCloseListener with the given prompt and title and potentially a check box providing the user
     * the option of never displaying the prompt again.
     * @param prompt prompt to display
     * @param title  title of dialog window
     * @param dontAsk if true, displays check box; otherwise no check box is displayed
     */
    public PromptOnCloseListener ( String prompt, String title, boolean dontAsk ) {
        this();
        this.prompt  = prompt;
        this.title   = title;
        this.options = (dontAsk) ? OPTIONS_B : OPTIONS_A;
    }

    //---- Utilities ----\\\

    @Override
    public void windowClosing ( WindowEvent e ) {
        if ( !PFrame.isExitOnClose() ) {
            int action = JOptionPane.showOptionDialog(PFrame.getInstance(),
                                                                    prompt, /* Prompt */
                                                                     title, /* Title */
                                                JOptionPane.DEFAULT_OPTION, /* Provide no options */
                                               JOptionPane.WARNING_MESSAGE, /* Warning icon */
                                                                      null, /* Provide no other icon */
                                                                   options, /* Options array */
                                                               options[1]); /* Default selection (obtains initial focus) */
            if (action == JOptionPane.OK_OPTION) {
                PFrame.getInstance().destroy();
            }
        } else {
            PFrame.getInstance().destroy();
        }
    }

    //---- Getters & Setters ----\\

    public Object[] getOptions() {
        return options;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getTitle() {
        return title;
    }
}
