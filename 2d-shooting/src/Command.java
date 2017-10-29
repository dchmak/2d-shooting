/**
 * Command
 * Interface that defines a character
 * @author Daniel
 */
public interface Command {
    void atk(char c);
    void jump(char c,double elapsedTime);
    void move(int x);
}