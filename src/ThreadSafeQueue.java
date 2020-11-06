import java.util.Queue;
import java.util.LinkedList;

public class ThreadSafeQueue {

    private Queue queue = new LinkedList();

    public synchronized Object dequeue() {
        Object toDequeue = null;
        synchronized(queue) {
            while (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    return toDequeue;
                }

            }
            toDequeue = queue.remove();
            return toDequeue;
        }
    }

    public synchronized void enqueue(Object theObject) {
        synchronized (queue) {
            queue.add(theObject);
            notifyAll();
        }
    }

    public synchronized Object lookAt(int i) {
        synchronized (queue) {
            Object[] objects = queue.toArray();
            return objects[i];
        }
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
    public synchronized int size() { return queue.size(); }
    public synchronized boolean contains(Object o) {return queue.contains(o);}


}
