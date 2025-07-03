package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.LambdaCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.ResetEncoder;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

import java.util.Set;

@Config
public class Lift extends Subsystem {
    public static final Lift INSTANCE = new Lift();
    private Lift() { }

    public MotorEx rightLift;
    public MotorEx leftLift;
    public MotorGroup liftMotors;
    public static double kP = 0.005;
    public static double kI = 0.0;
    public static double kD = 0.0;
    public static double kF = 0.0;
    public static double tolerance = 10;
    public PIDFController controller = new PIDFController(kP, kI, kD, new StaticFeedforward(kF), tolerance);
    public String rightLiftName = "ExpMotor0";
    public String leftLiftName = "ExpMotor1";

    public Command liftDown() {
        return new RunToPosition(liftMotors,
                0.0,
                controller,
                this);
        new ResetEncoder(leftLift, this);
        new ResetEncoder(rightLift, this);
    }

    public Command liftHighBucket() {
        return new RunToPosition(liftMotors,
                1000.0,
                controller,
                this);
    }

    public Command liftLowBucket() {
        return new RunToPosition(liftMotors,
                400.0,
                controller,
                this);
    }

    public Command liftHighChamber() {
        return new RunToPosition(liftMotors,
                500.0,
                controller,
                this);
    }

    public Command liftManualUp()  {
        return new SetPower(liftMotors,
                0.8,
                this);
    }

    public Command liftManualDown()  {
        return new SetPower(liftMotors,
                -0.8,
                this);
    }

    @Override
    public Command getDefaultCommand() {
        return new HoldPosition(liftMotors, controller, this);
    }

    @Override
    public void initialize()  {
        rightLift = new MotorEx(rightLiftName);
        leftLift = new MotorEx(leftLiftName);
        liftMotors = new MotorGroup(rightLift, leftLift);
    }

    public void periodic()  {
        OpModeData.telemetry.addData("Lift Position", liftMotors.getCurrentPosition());

    }

    




}
