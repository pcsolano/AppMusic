package ventanas.services;

import java.util.ArrayList;
import java.util.List;

public class PlayNotificationService {
	private final List<PlayObserver> observers;

	public PlayNotificationService() {
		observers = new ArrayList<PlayObserver>();
	}

	public void subscribe(PlayObserver observer) {
		observers.add(observer);
	}

	public void unsubscribe(PlayObserver observer) {
		observers.remove(observer);
	}

	public void notifyPlaylist() {
		observers.forEach(observer -> observer.updateBoton());
	}
}
