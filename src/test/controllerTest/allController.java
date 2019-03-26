package test.controllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.controllerTest.fortificationTest.FortificationTest;
import test.controllerTest.initializationTest.InitializationTest;
import test.controllerTest.reinforcementTest.ReinforcementTest;

@RunWith(Suite.class)
@SuiteClasses({FortificationTest.class, ReinforcementTest.class, InitializationTest.class})
public class allController {

}
