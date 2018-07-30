package otahiri.design.pattern.observer;

/**
 * Observer pattern is used when there is one-to-many relationship between
 * objects such as if one object is modified, its depenedent objects are to be
 * notified automatically. Observer pattern falls under behavioral pattern
 * category.
 * 
 * @author otahiri
 *
 */
public class GrabStocks {

	public static void main(String[] args) {

		// Create the Subject object
		// It will handle updating all observers
		// as well as deleting and adding them

		StockGrabber stockGrabber = new StockGrabber();

		// Create an Observer that will be sent updates from Subject

		StockObserver observer1 = new StockObserver(stockGrabber);

		stockGrabber.setIBMPrice(197.00);
//		stockGrabber.setAAPLPrice(677.60);
//		stockGrabber.setGOOGPrice(676.40);

		StockObserver observer2 = new StockObserver(stockGrabber);

		stockGrabber.setIBMPrice(256.00);
//		stockGrabber.setAAPLPrice(677.60);
//		stockGrabber.setGOOGPrice(676.40);

		// Delete one of the observers

		// stockGrabber.unregister(observer2);

//		stockGrabber.setIBMPrice(197.00);
//		stockGrabber.setAAPLPrice(677.60);
//		stockGrabber.setGOOGPrice(676.40);

	}

}