
class Timer {
	private long gameStartTime;
	private long actualTurnTime;
	long turnLimitTime;
	
	Timer(){
		turnLimitTime=10000;
	}
	Timer(long turnLimitTime){
		this.turnLimitTime=turnLimitTime;
	}
	
	void startTimer(){
		gameStartTime=System.currentTimeMillis();
		
	}
}
