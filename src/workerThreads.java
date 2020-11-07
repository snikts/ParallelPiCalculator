public class workerThreads extends Thread {

    Bpp calcPi = new Bpp();
    ThreadSafeQueue tasks;
    ThreadSafeQueue results;

    public workerThreads(ThreadSafeQueue tasks, ThreadSafeQueue results) {
        this.tasks = tasks;
        this.results = results;
    }

    @Override
    public void run() {
        while(!tasks.isEmpty()) {
            int pos = (Integer)tasks.dequeue();
            int calculated = calcPi.getDecimal(pos);
            results.enqueue(calculated);
//            System.out.println("dequeued pos " + pos );
//            System.out.println("size of tasks is " + tasks.size());
        }
        results.enqueue(-1);
    }

}
