
class Timer {
	private long gameStartTime;
	private long turnStartTime;
	private long turnLimitTime;
	
	void setTurnLimitTime(long turnLimitTime) {
		if (turnLimitTime<0)
			this.turnLimitTime=0;
		else
			this.turnLimitTime = turnLimitTime;
	}

	Timer(){
		turnLimitTime=10000;
	}
	
	Timer(long turnLimitTime){
		if (turnLimitTime<0)
			this.turnLimitTime=0;
		else
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
