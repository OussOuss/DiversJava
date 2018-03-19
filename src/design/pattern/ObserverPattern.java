package design.pattern;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {
	public static void main(String[] args) {
		Feed f = new Feed();
		f.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		f.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in London... " + tweet);
			}
		});
		
		f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
	}
}

interface Observer {
	void notify(String tweet);
}

interface Subject {
	void registerObserver(Observer o);

	void notifyObservers(String tweet);
}

class Feed implements Subject {
	private final List<Observer> observers = new ArrayList<>();

	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
}