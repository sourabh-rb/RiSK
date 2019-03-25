package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.controllerTest.attackTest.AttackTest;
import test.controllerTest.fortificationTest.FortificationTest;
import test.controllerTest.graphTest.GraphTest;
import test.controllerTest.initializationTest.InitializationTest;
import test.controllerTest.reinforcementTest.ReinforcementTest;

@RunWith(Suite.class)
@SuiteClasses({FortificationTest.class, InitializationTest.class, ReinforcementTest.class, GraphTest.class, AttackTest.class})

public class AllTests {

}
