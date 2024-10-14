package test;


public class ControllerMaker {

	// implement

	public static class  ControllerHolder
	{
		public static Controller controller = new Controller();
	}


	public static Controller get() {
		return  ControllerHolder.controller;

	}

}
