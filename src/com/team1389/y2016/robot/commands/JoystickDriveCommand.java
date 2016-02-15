package com.team1389.y2016.robot.commands;

import org.strongback.command.Command;
import org.strongback.components.ui.ContinuousRange;
import org.strongback.components.ui.InputDevice;

import com.team1389.y2016.robot.subsystems.Drivetrain;

//This class commands the drive train to set the motors to the specified speeds in order to turn
public class JoystickDriveCommand extends Command {

	Drivetrain driveTrain;
	InputDevice joyStick;

	public JoystickDriveCommand(Drivetrain driveTrain, InputDevice joystick) {
		super(driveTrain.getRequirements());
		this.driveTrain = driveTrain;
		this.joyStick = joystick;

	}

	@Override
	public boolean execute() {
		
		double speedMod = 0.65;
		double turnMod = .65;
		double turnAlotMod = 0.8;
		
		if(joyStick.getButton(1).isTriggered()){
			speedMod = 1.0;
		}
		
		double y, normalTurn, extraTurn;
		double left, right;
		normalTurn = joyStick.getAxis(0).read() * turnMod;
		y = joyStick.getAxis(1).read();
		extraTurn = joyStick.getAxis(2).read() * turnAlotMod;
		double x = absMax(normalTurn, extraTurn);
		left = y - x;
		right = y + x;
//		System.out.println("left: " + left + " right: " + right);
		driveTrain.set(left * speedMod, right * speedMod);

		return false;
	}
	
	public double absMax(double a, double b){
		if (Math.abs(a) > Math.abs(b)){
			return a;
		} else {
			return b;
		}
	}

}