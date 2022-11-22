package atividadeExtraclasse2;

public class RunnableSleepJoinThreads implements Runnable {
	public void run() {

		for (int i = 0; i < 4; i++) {
			System.out.println(Thread.currentThread().getName() + "  " + i);
			try {
				Thread.sleep(1000);
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
		Thread t = Thread.currentThread();
		System.out.println("Thread atual: " + t.getName());
		System.out.println("Is Alive? " + t.isAlive());
	}

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new RunnableSleepJoinThreads());
		t.start();

		Thread t2 = new Thread(new RunnableSleepJoinThreads());
		t2.start();

		t.join(10000);
		System.out.println("\n10000" + " milliseconds: \n");
		System.out.println("Thread atual: " + t.getName());
		System.out.println("Is alive? " + t.isAlive());
		System.out.println("Thread atual: " + t2.getName());
		System.out.println("Is alive? " + t2.isAlive());
	}
}
