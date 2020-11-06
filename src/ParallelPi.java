import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
//
//import com.googlecode.lanterna.SGR;
//import com.googlecode.lanterna.TerminalPosition;
//import com.googlecode.lanterna.TextColor;
//import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
//import com.googlecode.lanterna.terminal.Terminal;
//
//import java.io.IOException;


public class ParallelPi {

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
//        Scanner in = new Scanner(System.in);
////        String consoleLine = in.nextLine();
        ArrayList temp = new ArrayList();
        ThreadSafeQueue taskQueue = new ThreadSafeQueue();
        for (int i = 0; i < 1024; i++) {
            temp.add(i);
            System.out.println("added num " + i);
        }
        if(args.length > 0) {
            if (args[1].equals("-random")) {
                // do random stuff
                Collections.shuffle(temp);
                for (int i = 0; i < temp.size(); i++) {
                    System.out.println("Enqueued " + temp.get(i));
                    taskQueue.enqueue(temp.get(i));
                }
            } else if (args[1].equals("-reverse")) {
                // do reverse stuff here
                Collections.reverse(temp);
                for (int i = 0; i < temp.size(); i++) {
                    taskQueue.enqueue(temp.get(i));
                }
            }
        }
        else {
            for (int i = 0; i < temp.size(); i++) {
                taskQueue.enqueue(temp.get(i));
            }
        }
        ThreadSafeQueue results = new ThreadSafeQueue();
        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            threads[i] = new workerThreads(taskQueue, results);
        }
        for (int i = 0; i < cores; i++) {
            threads[i].start();
        }
        IOThread outputThread = new IOThread(results);
        outputThread.start();

    }


}
