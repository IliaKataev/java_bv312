public class Thread3 implements Runnable{
    private int value;


    public Thread3(int value) {
        this.value = value;
    }

    public void run(){
        int limit = this.value/2;
        while(this.value >= 0) {
            System.out.println("название потока" + Thread.currentThread().getName() +
                    " / Начальное значение - " + this.value);

            while (this.value >= 0) {
                System.out.println("название потока" + Thread.currentThread().getName() +
                        " /  значение - " + this.value);
                this.value -= 1;
                if(this.value <= limit && Thread.currentThread().isInterrupted()){
                    System.out.println("Поток "+ Thread.currentThread().getName()+" был прерван");
                    return;
                }
                try {
                    Thread.sleep(300);

                } catch (InterruptedException _) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + "has finished");
        }
    }
}
