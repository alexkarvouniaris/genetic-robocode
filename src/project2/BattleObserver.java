package project2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleFinishedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.BattlePausedEvent;
import robocode.control.events.BattleResumedEvent;
import robocode.control.events.BattleStartedEvent;
import robocode.control.events.IBattleListener;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.events.TurnStartedEvent;

// Our private battle listener for handling the battle event we are interested in.
public class BattleObserver implements IBattleListener {
	int score ;
	public BattleObserver() {
		score = 0;
	}
	
	public int getScore() {
		return score;
	}

	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		for (robocode.BattleResults result : event.getSortedResults()) {
			String name = result.getTeamLeaderName();
			if (name.equals("project2.SuperTrackerVol2*")) {
				score = result.getScore();
			}
		}
	}

	@Override
	public void onBattleError(BattleErrorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleFinished(BattleFinishedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleMessage(BattleMessageEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattlePaused(BattlePausedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleResumed(BattleResumedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleStarted(BattleStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurnStarted(TurnStartedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
