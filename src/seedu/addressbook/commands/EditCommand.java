
package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;

/**
 * Adds a person to the address book.
 */

public class EditCommand extends Command{

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person in the address book. "
            + "Parameters: INDEX FIELD_TO_EDIT/CORRECTED_EDITS\n\t"
            + "Example: " + COMMAND_WORD
            + " 2 p/98765432 ";

    public static final String MESSAGE_SUCCESS = "person edited: %1$s";

    private final Person personToEdit;

    private String editString;

    public enum Editfield{
        p, //phone
        e, //email
        a, //address
        n  //name
    }
    
    Editfield editfield;
    
    /**
     * Convenience constructor using raw values.
     * @return 
     * @return 
     * @return 
     * @return 
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    
    public EditCommand(int targetIndex, String editfield, String editString) throws IllegalValueException {
        this.editfield = Editfield.valueOf(editfield); //converts editfield from type String to type Editfield
        this.setTargetIndex(targetIndex);
        this.personToEdit = getTargetEditablePerson();
        this.editString = editString;
        Execute();
        
    }
    
    public void Execute() throws IllegalValueException {
    
        switch (editfield) {
        case p:  Phone newPhone = new Phone(editString, false);
                 personToEdit.changePhone(newPhone);
                 break; 
        case e:  Email newEmail = new Email(editString, false);
                 personToEdit.changeEmail(newEmail);
                 break;
        case a:  Address newAddress = new Address(editString, false);
                 personToEdit.changeAddress(newAddress);
                 break;
        case n:  Name newName = new Name(editString);
                 personToEdit.changeName(newName);
                 break;
        default: throw new IllegalValueException("invalid editfield");
        }
    }

    @Override
    public CommandResult execute() {
            return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit));
       
    }
 

   
}
