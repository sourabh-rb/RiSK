package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.gameEngineTest.player.PlayerTest;
import test.gameEngineTest.startUpPhase.StartUpPhaseTest;
import test.gameEngineTest.turnManager.TurnManagerTest;
import test.gameEngineTest.validateGraph.ValidateGraphTest;
import test.utilitiesTest.UtilitiesTest;

@RunWith(Suite.class)
@SuiteClasses({ValidateGraphTest.class, PlayerTest.class, StartUpPhaseTest.class, UtilitiesTest.class, TurnManagerTest.class})

public class AllTests {

}
