public class Thread2 extends Thread{
    private int value;


    public Thread2(int value) {
        this.value = value;
    }

    public void run(){
        while(this.value >= 0) {
            System.out.println("название потока" + Thread.currentThread().getName() +
                    " / Начальное значение - " + this.value);

            while (this.value >= 0) {
                System.out.println("название потока" + Thread.currentThread().getName() +
                        " /  значение - " + this.value);
                this.value -= 1;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException _) {
                }
            }
            System.out.println("Thread:" + Thread.currentThread().getName() + "has finished");
        }
    }
}
