
class Timer {
	private long gameStartTime;
	private long turnStartTime;
	long turnLimitTime;
	
	Timer(){
		turnLimitTime=10000;
	}
	Timer(long turnLimitTime){
		this.turnLimitTime=turnLimitTime;
	}
	
	void nextTurn(){
		turnStartTime=System.currentTimeMillis();
	}
	void gameStart(){
		gameStartTime=System.currentTimeMillis();
		turnStartTime=gameStartTime;
	}
}
