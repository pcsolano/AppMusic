package ventanas.services;

public class NextPreviousNotificationService {
	private final NextPreviousObserver observer;

	public NextPreviousNotificationService(NextPreviousObserver observer) {
		this.observer = observer;
	}

	public void notifyNext() {
		observer.nextUpdate();
	}

	public void notifyPrevious() {
		observer.previousUpdate();
	}
}
