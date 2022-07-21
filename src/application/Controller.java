package application;



public class Controller {
	private static GuiBuilder guiBuild;
	private static ModDraw draw;
	private static double windowHeight;
	private static double windowWidth;
	private static GEngine currentGame;
	private static String[][]btn = new String[][]{ {"Snake","Tetris","3D Model","Tetris 3D"},{"snake","T2D","mod","T3D"}};
	private static CommonEHandler eHandler;
	private static Main view;
	private static boolean backBttnVis=false;
	private static double[] winLoc;
	
	public static CommonEHandler getEventHandler() {
		if (eHandler==null) eHandler = new CommonEHandler();
		return eHandler;
	}
	
	public static void setView(Main view) {
		Controller.view = view;
	}

	public static Main getView() {
		return view;
	}

	public static void startPage() {
		if (getCurrentGame() != null) {
			getCurrentGame().killTimer();
			setCurrentGame(null);
		}
		getGuiBuild().setText("");
		Block.resetStaticTTL();
		Block.set3D(false);
		setBackBttnVis(false);
		view.reload();
	}

	public static void modelView() {
		setBackBttnVis(true);
		setDraw(new ModDraw());
		getGuiBuild().setDrawToMainFrame();
		view.reload();
	}

	public static void startSnake() {
		if(getCurrentGame()!=null) {getCurrentGame().exit();}
		setBackBttnVis(true);
		setCurrentGame(new Snake());
		getCurrentGame().setSize(getWindowWidth(), getWindowHeight()-100);
		getGuiBuild().setSnakeToMainFrame();
		view.reload();
		getCurrentGame().go();
	}

	public static void startTetris(boolean t3D) {
		if(getCurrentGame()!=null) getCurrentGame().exit();
		setBackBttnVis(true);
		setCurrentGame(new Tetris(t3D,250,10));
		getCurrentGame().setSize(getWindowWidth(), getWindowHeight()-100);
		getGuiBuild().setTetrisToMainFrame();
		view.reload();
		getCurrentGame().go();
	}

	public static void startText() {
		getGuiBuild().setContentFrame(true);
	}

	public static void setSize(){
		getGuiBuild().setSize(getWindowWidth(), getWindowHeight());
		view.reload();
	}

	public static void scale(double inc) {
		setWindowHeight(getWindowHeight()+inc);
		setWindowWidth(getWindowHeight()*1.5);
		setSize();
	}

	public static void main(String[] args){
		HighScore hsc = new HighScore();
		setWindowHeight(500);
		setWindowWidth(getWindowHeight()*1.5);
		setGuiBuild(new GuiBuilder(getWindowWidth(),getWindowHeight()));
		Main.go();
	}

	public static double[] getWinLoc() {
		return winLoc;
	}

	public static void setWinLoc(double[] winLoc) {
		Controller.winLoc = winLoc;
	}

	public static double getWindowWidth() {
		return windowWidth;
	}

	public static void setWindowWidth(double w) {
		Controller.windowWidth = w;
	}

	public static double getWindowHeight() {
		return windowHeight;
	}

	public static void setWindowHeight(double windowHeight) {
		Controller.windowHeight = windowHeight;
	}

	public static GuiBuilder getGuiBuild() {
		return guiBuild;
	}

	public static void setGuiBuild(GuiBuilder guiBuild) {
		Controller.guiBuild = guiBuild;
	}

	public static boolean isBackBttnVis() {
		return backBttnVis;
	}

	public static void setBackBttnVis(boolean backBttnVis) {
		Controller.backBttnVis = backBttnVis;
	}

	public static GEngine getCurrentGame() {
		return currentGame;
	}

	public static void setCurrentGame(GEngine currentGame) {
		Controller.currentGame = currentGame;
	}

	public static ModDraw getDraw() {
		return draw;
	}

	public static void setDraw(ModDraw draw) {
		Controller.draw = draw;
	}

	public static String[][] getBtn() {
		return btn;
	}

	public static void setBtn(String[][] btn) {
		Controller.btn = btn;
	}


	
	
}

