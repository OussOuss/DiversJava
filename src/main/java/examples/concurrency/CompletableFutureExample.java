package examples.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExample {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> future1
				= CompletableFuture.supplyAsync(() -> "Hello");
		CompletableFuture<String> future2
				= CompletableFuture.supplyAsync(() -> "Beautiful");
		CompletableFuture<String> future3
				= CompletableFuture.supplyAsync(() -> "World");

		//La méthode statique CompletableFuture.allOf permet d’attendre la fin de tous les Futures fournis sous forme de var-arg:
		CompletableFuture<Void> combinedFuture
				= CompletableFuture.allOf(future1, future2, future3);

		String valueCombinedFuture = Stream.of(future1, future2, future3)
				.map(CompletableFuture::join)
				.collect(Collectors.joining(" "));
		System.out.println(valueCombinedFuture);

		int price = 15; // Let's keep it simple and work with whole number prices here
		int weightInGrams = 900;

		calculateShippingPrice(weightInGrams) // Here, we get the future
				.thenAccept(shippingPrice -> { // And then immediately work on it!
					// This fluent style is very useful for keeping it concise
					System.out.println("Your total price is: " + (price + shippingPrice));
				});
		System.out.println("Please stand by. We are calculating your total price.");
		Thread.sleep(5000L);
	}


	public static CompletableFuture<Integer> calculateShippingPrice(int weightInGrams) {
		return CompletableFuture.supplyAsync(() -> {
			// supplyAsync is a factory method that turns a given
			// Supplier<U> into a CompletableFuture<U>

			// Let's just say each 200 grams is a new dollar on your shipping costs
			int shippingCosts = weightInGrams / 200;

			try {
				Thread.sleep(2000L); // Now let's simulate some waiting time...
			} catch(InterruptedException e) { /* We can safely ignore that */ }

			return shippingCosts; // And send the costs back!
		});
	}
}
