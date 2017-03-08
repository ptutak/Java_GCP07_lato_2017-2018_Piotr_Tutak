
class Timer {
	long time;
	long turnTime;
	
	Timer(){
		time=System.currentTimeMillis();
		turnTime=10000;
	}
	Timer(long turnTime){
		this.turnTime=turnTime;
	}
	
	public long getTurnTime() {
		return turnTime;
	}
	public void setTurnTime(long turnTime) {
		this.turnTime = turnTime;
	}
	
	void resetTimer(){
		time=System.currentTimeMillis();
	}
	void startTimer(){
		time=System.currentTimeMillis();
		long tmpTime=time;
		while((tmpTime-time)<turnTime){
			tmpTime=System.currentTimeMillis();
		}
		
	}
}
