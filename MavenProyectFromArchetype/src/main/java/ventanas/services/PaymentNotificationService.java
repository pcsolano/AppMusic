package ventanas.services;

public class PaymentNotificationService {
	private final PaymentObserver observer;

	public PaymentNotificationService(PaymentObserver observer) {
		this.observer = observer;
	}

	public void notifyObserver() {
		observer.updatePremium();
	}
}
