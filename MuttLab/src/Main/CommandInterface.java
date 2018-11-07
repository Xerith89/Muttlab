package Main;
import java.util.Optional;

/**
 * Implement this interface in a commands class in order
 * to add a new command
 */
public interface CommandInterface {
    
    public void executeCommand(Optional<String> arg);
}
