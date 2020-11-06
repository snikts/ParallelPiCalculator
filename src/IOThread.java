import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class IOThread extends Thread {
    ThreadSafeQueue numbers;
    DefaultTerminalFactory dtf = new DefaultTerminalFactory();
    Terminal terminal = null;
    public IOThread(ThreadSafeQueue results) {
        numbers = results;
        try {
            terminal = dtf.createTerminal();
            terminal.clearScreen();
        }
        catch(Exception ex) {

        }
    }

    public void run() {
        int x = 0;
        int y = 0;
        try {
            terminal.setCursorPosition(x, y);
            terminal.putCharacter('3');
            x=x+1;
            terminal.setCursorPosition(x, y);
            terminal.putCharacter('.');
            x=x+1;
            terminal.flush();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        while(!numbers.contains(-1) || !numbers.isEmpty()) {
            if(numbers.size() > 0) {
                String theInt = Integer.toString((Integer)numbers.dequeue());
                if(!theInt.equals("-1")) {
                    try {
                        if (x < 66) {
                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(theInt.charAt(0));
                            x = x + 1;
                            terminal.flush();
                        } else {
                            x = 2;
                            y = y + 1;
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        }
    }

}
