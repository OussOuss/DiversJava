package design.pattern.template;

public class Tennis extends Game {

	   @Override
	   void endPlay() {
	      System.out.println("Tennis Game Finished!");
	   }

	   @Override
	   void initialize() {
	      System.out.println("Tennis Game Initialized! Start playing.");
	   }

	   @Override
	   void startPlay() {
	      System.out.println("Tennis Game Started. Enjoy the game!");
	   }
	}