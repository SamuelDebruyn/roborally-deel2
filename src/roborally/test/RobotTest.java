package roborally.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import roborally.exception.EntityNotOnBoardException;
import roborally.exception.IllegalPositionException;
import roborally.exception.NotEnoughEnergyException;
import roborally.exception.TargetNotReachableException;
import roborally.model.Battery;
import roborally.model.Board;
import roborally.model.Entity;
import roborally.model.RepairKit;
import roborally.model.Robot;
import roborally.model.SurpriseBox;
import roborally.model.Wall;
import roborally.property.Energy;
import roborally.property.Orientation;
import roborally.property.Position;
import roborally.property.Weight;

public class RobotTest {

	Robot robot_up_10000;
	Robot robot_right_10000;
	Robot robot_left_10000;
	Robot robot_down_10000;
	Robot robot_up_1000;
	Robot robot_right_1000;
	Robot robot_left_1000;
	Robot robot_down_1000;
	Robot robot_onBoard_20_20_up_10000;
	Robot robot_onBoard_20_20_right_10000;
	Robot robot_onBoard_20_20_left_10000;
	Robot robot_onBoard_20_20_down_10000;
	Robot robot_onBoard_20_20_up_1000;
	Robot robot_onBoard_20_20_right_1000;
	Robot robot_onBoard_20_20_left_1000;
	Robot robot_onBoard_20_20_down_1000;
	Robot robot_onBoard_40_40_up_10000;
	Robot robot_onBoard_40_40_right_10000;
	Robot robot_onBoard_40_40_left_10000;
	Robot robot_onBoard_40_40_down_10000;
	Robot robot_onBoard_40_40_up_1000;
	Robot robot_onBoard_40_40_right_1000;
	Robot robot_onBoard_40_40_left_1000;
	Robot robot_onBoard_40_40_down_1000;
	Board board_20_20 = new Board(20, 20);
	Board board_40_40 = new Board(40, 40);
	Battery batt;
	RepairKit rk;

	@Before
	public void setUp(){
		robot_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_20_20_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_onBoard_20_20_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_onBoard_20_20_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_onBoard_20_20_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_onBoard_20_20_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_onBoard_20_20_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_onBoard_20_20_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_onBoard_20_20_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_20_20_up_10000.putOnBoard(board_20_20, new Position(1, 1));
		robot_onBoard_20_20_right_10000.putOnBoard(board_20_20, new Position(2, 1));
		robot_onBoard_20_20_left_10000.putOnBoard(board_20_20, new Position(3, 1));
		robot_onBoard_20_20_down_10000.putOnBoard(board_20_20, new Position(4, 1));
		robot_onBoard_20_20_up_1000.putOnBoard(board_20_20, new Position(5, 1));
		robot_onBoard_20_20_right_1000.putOnBoard(board_20_20, new Position(6, 1));
		robot_onBoard_20_20_left_1000.putOnBoard(board_20_20, new Position(7, 1));
		robot_onBoard_20_20_down_1000.putOnBoard(board_20_20, new Position(8, 1));
		robot_onBoard_40_40_up_10000 = new Robot(Orientation.UP, new Energy(10000));
		robot_onBoard_40_40_right_10000 = new Robot(Orientation.RIGHT, new Energy(10000));
		robot_onBoard_40_40_left_10000 = new Robot(Orientation.LEFT, new Energy(10000));
		robot_onBoard_40_40_down_10000 = new Robot(Orientation.DOWN, new Energy(10000));
		robot_onBoard_40_40_up_1000 = new Robot(Orientation.UP, new Energy(1000));
		robot_onBoard_40_40_right_1000 = new Robot(Orientation.RIGHT, new Energy(1000));
		robot_onBoard_40_40_left_1000 = new Robot(Orientation.LEFT, new Energy(1000));
		robot_onBoard_40_40_down_1000 = new Robot(Orientation.DOWN, new Energy(1000));
		robot_onBoard_40_40_up_10000.putOnBoard(board_40_40, new Position(1, 1));
		robot_onBoard_40_40_right_10000.putOnBoard(board_40_40, new Position(2, 1));
		robot_onBoard_40_40_left_10000.putOnBoard(board_40_40, new Position(3, 1));
		robot_onBoard_40_40_down_10000.putOnBoard(board_40_40, new Position(4, 1));
		robot_onBoard_40_40_up_1000.putOnBoard(board_40_40, new Position(5, 1));
		robot_onBoard_40_40_right_1000.putOnBoard(board_40_40, new Position(6, 1));
		robot_onBoard_40_40_left_1000.putOnBoard(board_40_40, new Position(7, 1));
		robot_onBoard_40_40_down_1000.putOnBoard(board_40_40, new Position(8, 1));
		batt = new Battery(new Energy(2000), new Weight(2000));
		rk = new RepairKit(new Energy(2000), new Weight(2000));
	}

	@Test
	public void testIsValidRobotEnergyEnergyRobot() {
		assertTrue(Robot.isValidRobotEnergy(new Energy(1000), robot_down_1000));
		assertFalse(Robot.isValidRobotEnergy(new Energy(20000.1), robot_down_1000));
	}

	@Test
	public void testTurnClockWise() {
		robot_onBoard_20_20_down_10000.turnClockWise();
		assertEquals(Orientation.LEFT, robot_onBoard_20_20_down_10000.getOrientation());
		assertEquals(new Energy(9900), robot_onBoard_20_20_down_10000.getEnergy());
	}

	@Test
	public void testTurnCounterClockWise() {
		robot_onBoard_20_20_down_10000.turnCounterClockWise();
		assertEquals(Orientation.RIGHT, robot_onBoard_20_20_down_10000.getOrientation());
		assertEquals(new Energy(9900), robot_onBoard_20_20_down_10000.getEnergy());
	}
	
	@Test(expected = NotEnoughEnergyException.class)
	public void testTurnClockWiseNotEnoughEnergyException(){
		robot_onBoard_20_20_down_1000.move();
		robot_onBoard_20_20_down_1000.move();
		robot_onBoard_20_20_down_1000.turnClockWise();
	}
	
	@Test(expected = NotEnoughEnergyException.class)
	public void testTurnCounterClockWiseNotEnoughEnergyException(){
		robot_onBoard_20_20_down_1000.move();
		robot_onBoard_20_20_down_1000.move();
		robot_onBoard_20_20_down_1000.turnCounterClockWise();
	}

	@Test
	public void testMove() {
		robot_onBoard_20_20_up_10000.move();
		assertEquals(new Position(1, 0), robot_onBoard_20_20_up_10000.getPosition());
		assertEquals(new Energy(9500), robot_onBoard_20_20_up_10000.getEnergy());
	}
	
	@Test(expected = NotEnoughEnergyException.class)
	public void testMoveNotEnoughEnergyException(){
		robot_down_1000.turnClockWise();
		robot_down_1000.turnClockWise();
		robot_down_1000.turnClockWise();
		robot_down_1000.turnClockWise();
		robot_down_1000.turnClockWise();
		robot_down_1000.turnClockWise();
		robot_down_1000.move();
	}
	
	@Test(expected = IllegalPositionException.class)
	public void testMoveIllegalPositionException(){
		robot_onBoard_20_20_up_1000.setPosition(new Position(0, 0));
		robot_onBoard_20_20_up_1000.move();
	}

	@Test
	public void testGetEnergyRequiredToReach() {
		Wall wall = new Wall();
		wall.putOnBoard(board_20_20, new Position(1, 2));
		assertEquals(new Energy(2300), robot_onBoard_20_20_up_10000.getEnergyRequiredToReach(new Position(1, 3)));
	}
	
	@Test(expected = TargetNotReachableException.class)
	public void testGetEnergyRequiredToReachTargetNotReachableException(){
		robot_onBoard_40_40_up_1000.getEnergyRequiredToReach(new Position(40, 40));
	}

	@Test
	public void testMoveNextTo() {
		fail("Not yet implemented");
	}

	@Test
	public void testShoot() {
		robot_onBoard_20_20_right_10000.shoot();
		assertEquals(new Energy(9000), robot_onBoard_20_20_right_10000.getEnergy());
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		robot_onBoard_20_20_right_10000.shoot();
		assertTrue(robot_onBoard_20_20_left_10000.isTerminated());
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testShootEntityNotOnBoardException(){
		robot_down_1000.shoot();
	}

	@Test
	public void testRecharge() {
		robot_onBoard_20_20_down_1000.recharge(new Energy(1));
		assertEquals(new Energy(1001), robot_onBoard_20_20_down_1000.getEnergy());
		robot_onBoard_20_20_down_1000.recharge(new Energy(20000));
		assertEquals(new Energy(20000), robot_onBoard_20_20_down_1000.getEnergy());
	}

	@Test
	public void testMoveCost() {
		assertEquals(new Energy(500), Robot.moveCost(robot_down_1000));
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertEquals(new Energy(600), Robot.moveCost(robot_onBoard_20_20_down_1000));
	}

	@Test
	public void testGetPossessions() {
		assertEquals(0, robot_onBoard_20_20_down_1000.getPossessions().size());
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		rk.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertTrue(robot_onBoard_20_20_down_1000.getPossessions().contains(batt));
		robot_onBoard_20_20_down_1000.pickUp(rk);
		assertTrue(robot_onBoard_20_20_down_1000.getPossessions().contains(rk));
	}

	@Test
	public void testTransferPossessions() {
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.transferPossessions(robot_onBoard_20_20_down_10000);
		assertEquals(0, robot_onBoard_20_20_down_1000.getPossessions().size());
		assertEquals(1, robot_onBoard_20_20_down_10000.getPossessions().size());
	}

	@Test
	public void testPickUp() {
		assertEquals(0, robot_onBoard_20_20_down_1000.getPossessions().size());
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		rk.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		assertEquals(1, robot_onBoard_20_20_down_1000.getPossessions().size());
		robot_onBoard_20_20_down_1000.pickUp(rk);
		assertEquals(2, robot_onBoard_20_20_down_1000.getPossessions().size());
		HashSet<Entity> place = board_20_20.getEntityOnPosition(robot_onBoard_20_20_down_1000.getPosition());
		assertFalse(place.contains(batt));
		assertFalse(place.contains(rk));
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testPickUpEntityNotOnBoardException(){
		robot_down_1000.pickUp(batt);
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testPickUpEntityNotOnBoardException2(){
		robot_onBoard_20_20_down_1000.pickUp(batt);
	}
	
	@Test(expected = IllegalPositionException.class)
	public void testPickUpEntityIllegalPositionException(){
		batt.putOnBoard(board_20_20, new Position(3, 3));
		robot_onBoard_20_20_down_1000.pickUp(batt);
	}

	@Test
	public void testUse() {
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.use(batt);
		assertEquals(new Energy(3000), robot_onBoard_20_20_down_1000.getEnergy());
		assertTrue(batt.isTerminated());
		Battery batt2 = new Battery(new Energy(5000), new Weight(1));
		Battery batt3 = new Battery(new Energy(5000), new Weight(1));
		Battery batt4 = new Battery(new Energy(5000), new Weight(1));
		Battery batt5 = new Battery(new Energy(5000), new Weight(1));
		batt2.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		batt3.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		batt4.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		batt5.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt2);
		robot_onBoard_20_20_down_1000.pickUp(batt3);
		robot_onBoard_20_20_down_1000.pickUp(batt4);
		robot_onBoard_20_20_down_1000.pickUp(batt5);
		robot_onBoard_20_20_down_1000.use(batt2);
		robot_onBoard_20_20_down_1000.use(batt3);
		robot_onBoard_20_20_down_1000.use(batt4);
		robot_onBoard_20_20_down_1000.use(batt5);
		assertEquals(new Energy(20000), robot_onBoard_20_20_down_1000.getEnergy());
		Battery batt6 = new Battery(new Energy(100), new Weight(1));
		batt6.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt6);
		robot_onBoard_20_20_down_1000.turnClockWise();
		robot_onBoard_20_20_down_1000.use(batt6);
		assertEquals(new Energy(20000), robot_onBoard_20_20_down_1000.getEnergy());
		SurpriseBox box = new SurpriseBox(new Weight(1));
		box.putOnBoard(board_20_20, robot_onBoard_20_20_down_10000.getPosition());
		robot_onBoard_20_20_down_10000.pickUp(box);
		robot_onBoard_20_20_down_10000.use(box);
		assertTrue(box.isTerminated());
		robot_onBoard_40_40_right_10000.shoot();
		rk.putOnBoard(board_40_40, robot_onBoard_40_40_left_10000.getPosition());
		robot_onBoard_40_40_left_10000.pickUp(rk);
		robot_onBoard_40_40_left_10000.use(rk);
		assertTrue(rk.isTerminated());
	}

	@Test
	public void testDrop() {
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.drop(batt);
		HashSet<Entity> place = board_20_20.getEntityOnPosition(robot_onBoard_20_20_down_1000.getPosition());
		assertTrue(place.contains(batt));
		assertFalse(robot_onBoard_20_20_down_1000.getPossessions().contains(batt));
	}
	
	@Test(expected = EntityNotOnBoardException.class)
	public void testDropEntityNotOnBoardException(){
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.removeFromBoard();
		robot_onBoard_20_20_down_1000.drop(batt);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDropIllegalArgumentException(){
		robot_onBoard_20_20_down_1000.drop(batt);
	}
	
	@Test
	public void testLoadProgramFromFile() {
		assertNull(robot_onBoard_20_20_down_1000.getProgram());
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Test mislukt of testprogramma bestaat niet.");
		}
		assertNotNull(robot_onBoard_20_20_down_1000.getProgram());
	}

	@Test
	public void testGetProgram() {
		assertNull(robot_onBoard_20_20_down_1000.getProgram());
		// Het is noodzakelijk om een correct programma op te geven.
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Opgegeven testprogramma ongeldig.");
		}
		assertNotNull(robot_onBoard_20_20_down_1000.getProgram());
	}

	@Test
	/**
	 * Deze test mislukt ook wanneer een ander voorbeeld dan example.prog wordt gebruikt om te testen.
	 */
	public void testSaveProgramToFile() {
		// Het is noodzakelijk om een correct programma op te geven.
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			fail("Opgegeven testprogramma ongeldig.");
		}
		assertEquals(0, robot_onBoard_20_20_down_1000.saveProgramToFile("exampleCopy.prog"));
		assertTrue((new File("exampleCopy.prog")).exists());
		try {
			robot_onBoard_20_20_down_10000.loadProgramFromFile("exampleCopy.prog");
		} catch (FileNotFoundException e) {
			fail("Inladen van nieuw programma lukt niet.");
		}
		robot_onBoard_20_20_down_10000.setPosition(new Position(3, 3));
		robot_onBoard_20_20_down_10000.stepn(2);
		assertEquals(Orientation.LEFT, robot_onBoard_20_20_down_10000.getOrientation());
	}

	@Test
	public void testStepn() {
		try {
			robot_onBoard_20_20_down_1000.loadProgramFromFile("example.prog");
		} catch (FileNotFoundException e) {
			// Een testprogramma is nodig om deze methode te testen.
			fail("Het testprogramma bestaat niet.");
		}
		robot_onBoard_20_20_down_1000.stepn(2);
		assertEquals(Orientation.LEFT, robot_onBoard_20_20_down_1000.getOrientation());
	}
	
	@Test(expected = IllegalStateException.class)
	public void testStepnIllegalStateException(){
		robot_onBoard_20_20_down_1000.stepn(1);
	}

	@Test
	public void testSetPosition() {
		assertNull(robot_down_1000.getPosition());
		robot_down_1000.putOnBoard(board_20_20, new Position(5, 5));
		assertEquals(new Position(5, 5), robot_down_1000.getPosition());
		robot_down_1000.setPosition(new Position(6, 6));
		assertEquals(new Position(6, 6), robot_down_1000.getPosition());
	}

	@Test(expected = IllegalStateException.class)
	public void testSetPositionIllegalStateException(){
		robot_onBoard_20_20_down_1000.destroy();
		robot_onBoard_20_20_down_1000.setPosition(new Position(0, 0));
	}

	@Test(expected = EntityNotOnBoardException.class)
	public void testSetPositionEntityNotOnBoardException(){
		robot_down_1000.setPosition(new Position(0, 0));
	}

	@Test(expected = IllegalPositionException.class)
	public void testSetPositionIllegalPositionException(){
		robot_onBoard_20_20_down_1000.setPosition(new Position(21, 0));
	}

	@Test
	public void testPutOnBoard() {
		assertFalse(robot_down_1000.isOnBoard());
		robot_down_1000.putOnBoard(board_20_20, new Position(3, 3));
		assertTrue(robot_down_1000.isOnBoard());
		assertEquals(new Position(3, 3), robot_down_1000.getPosition());
		assertEquals(board_20_20, robot_down_1000.getBoard());
		HashSet<Entity> place = board_20_20.getEntityOnPosition(new Position(3, 3));
		assertTrue(place.contains(robot_onBoard_20_20_down_1000));
	}

	@Test(expected = IllegalStateException.class)
	public void testPutOnBoardIllegalStateException(){
		robot_onBoard_20_20_down_1000.putOnBoard(board_40_40, new Position(3, 3));
	}

	@Test
	public void testRemoveFromBoard() {
		assertTrue(robot_onBoard_20_20_down_1000.isOnBoard());
		robot_onBoard_20_20_down_1000.removeFromBoard();
		assertFalse(robot_onBoard_20_20_down_1000.isOnBoard());
		assertNull(robot_onBoard_20_20_down_1000.getPosition());
		assertNull(robot_onBoard_20_20_down_1000.getBoard());
		HashSet<Entity> place = board_20_20.getEntityOnPosition(robot_down_1000.getPosition());
		if(place != null)
			assertFalse(place.contains(robot_onBoard_20_20_down_1000));
	}

	@Test(expected = EntityNotOnBoardException.class)
	public void testRemoveFromBoardEntityNotOnBoardException(){
		robot_down_1000.removeFromBoard();
	}

	@Test
	public void testDestroy() {
		assertFalse(robot_down_1000.isTerminated());
		robot_down_1000.destroy();
		assertTrue(robot_down_1000.isTerminated());
		batt.putOnBoard(board_20_20, robot_onBoard_20_20_down_1000.getPosition());
		assertFalse(batt.isTerminated());
		robot_onBoard_20_20_down_1000.pickUp(batt);
		robot_onBoard_20_20_down_1000.destroy();
		assertEquals(robot_onBoard_20_20_down_1000.getPossessions().size(), 0);
		assertTrue(batt.isTerminated());
	}

	@Test
	public void testToString() {
		assertEquals("positie: 5, 1, energie: 1000.0 WS, maximale energie: 20000.0 WS, oriŽntatie: UP, gewicht: 0 gr", robot_onBoard_20_20_up_1000.toString());
		assertEquals("positie: staat niet op een bord, energie: 1000.0 WS, maximale energie: 20000.0 WS, oriŽntatie: UP, gewicht: 0 gr", robot_up_1000.toString());
	}

	@Test
	public void testGetOrientation() {
		assertEquals(Orientation.UP, robot_up_1000.getOrientation());
		assertEquals(Orientation.DOWN, robot_down_1000.getOrientation());
		assertEquals(Orientation.LEFT, robot_left_1000.getOrientation());
		assertEquals(Orientation.RIGHT, robot_right_1000.getOrientation());
	}

	@Test
	public void testGetEnergy() {
		assertEquals(new Energy(1000), robot_down_1000.getEnergy());
		assertEquals(new Energy(10000), robot_down_10000.getEnergy());
	}

	@Test
	public void testIsValidRobotEnergyEnergy() {
		assertTrue(Robot.isValidRobotEnergy(new Energy(1000)));
		assertFalse(Robot.isValidRobotEnergy(new Energy(20000.1)));
	}

	@Test
	public void testGetEnergyFraction() {
		assertEquals(0.5,1, robot_down_10000.getEnergyFraction());
	}

	@Test
	public void testGetBoard() {
		assertEquals(board_20_20, robot_onBoard_20_20_down_1000.getBoard());
	}

	@Test
	public void testIsOnBoard() {
		assertTrue(robot_onBoard_20_20_down_1000.isOnBoard());
		assertFalse(robot_down_1000.isOnBoard());
	}

	@Test
	public void testGetPosition() {
		assertEquals(new Position(1, 1), robot_onBoard_20_20_up_10000.getPosition());
	}

	@Test
	public void testIsTerminated() {
		assertFalse(robot_down_1000.isTerminated());
		robot_down_1000.destroy();
		assertTrue(robot_down_1000.isTerminated());
	}

	@Test
	public void testIsValidEntity() {
		assertTrue(robot_down_1000.isValidEntity());
		assertTrue(robot_onBoard_20_20_down_1000.isValidEntity());
		robot_onBoard_20_20_down_1000.setPosition(null);
		assertFalse(robot_onBoard_20_20_down_1000.isValidEntity());
	}

}