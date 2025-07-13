package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;

import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Deposit;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeColor;
import org.firstinspires.ftc.teamcode.Subsystems.IntakePivot;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;
import org.firstinspires.ftc.teamcode.Subsystems.Wrist;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.core.command.Command;

public class LobsterDiddy extends NextFTCOpMode {
    public LobsterDiddy() {
        super(Claw.INSTANCE, Lift.INSTANCE, Extendo.INSTANCE, Deposit.INSTANCE, Intake.INSTANCE, IntakeColor.INSTANCE, IntakePivot.INSTANCE, Wrist.INSTANCE);
    }
    public String frontLeftName = "leftFront";
    public String frontRightName = "rightFront";
    public String backLeftName = "leftRear";
    public String backRightName = "rightRear";

    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    public Command driverControlled;

    @Override
    public void onInit() {
        frontLeftMotor = new MotorEx(frontLeftName);
        backLeftMotor = new MotorEx(backLeftName);
        backRightMotor = new MotorEx(backRightName);
        frontRightMotor = new MotorEx(frontRightName);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorEx[] {frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
    }

    @Override
    public void onStartButtonPressed() {
        driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1());
        driverControlled.invoke();
    }
}

