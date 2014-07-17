import javax.swing.*;
import java.awt.event.*;

/**
 * Created by Daniel Brady as part of the PokemonJava project.
 * Last modified on 16 July 2014 at 6:41 PM.
 *
 * This class models a WindowAdapter which brings up a modal option dialog, prompting the user to confirm the window
 * close. It also provides the option of presenting a check box that, if checked, prevents the dialog from ever being
 * displayed again (at least during the current session; change is not persistent across runs, yet).
 */
public class PromptOnCloseListener extends WindowAdapter {

    //---- Class & instance variables ----\\

    public static final Object[] OPTIONS_A = new Object[2],
                                 OPTIONS_B = new Object[3];
    public static final String DEFAULT_PROMPT = "Do you really wish to exit? Any unsaved data will be lost.",
                               DEFAULT_TITLE  = "Confirm Exit";

    private Object[] options = OPTIONS_A;
    private String prompt    = DEFAULT_PROMPT,
                   title     = DEFAULT_TITLE;

    //---- Constructors ----\\

    /**
     * Creates a PromptOnCloseListener with default prompt, title, and options.
     */
    public PromptOnCloseListener () {
        final JCheckBox dontAsk = new JCheckBox( "Do not ask me again" );
        dontAsk.addItemListener( new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // If the selectable item that triggered the event was the checkbox
                if ( e.getItemSelectable() == dontAsk ) {
                    // Set the flag variable to the state of the check box: true if checked, false if unchecked.
                    PFrame.setExitOnClose( dontAsk.isSelected() );
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

    public Object[] getOptions () {
        return options;
    }

    public String getPrompt () {
        return prompt;
    }

    public String getTitle () {
        return title;
    }
}
