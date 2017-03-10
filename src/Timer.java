
class Timer extends Thread {
	private long gameStartTime;
	private long turnStartTime;
	private long turnLimitTime;
	private TimeInfo timeInfo;
		
	void setTurnLimitTime(long turnLimitTime) {
		if (turnLimitTime<0)
			this.turnLimitTime=0;
		else
			this.turnLimitTime = turnLimitTime;
	}

	Timer(TimeInfo timeInfo){
		this.timeInfo=timeInfo;
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
	
	@Override
	public void run(){
		Player prevPlayer=timeInfo.getTurnPlayer();
		while (!timeInfo.isTimerOn()) {
			
		}
		while (timeInfo.isTimerOn()){
			if (prevPlayer!=timeInfo.getTurnPlayer())
				nextTurn();
			
		}
	}
}
