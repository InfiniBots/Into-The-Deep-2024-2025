package org.firstinspires.ftc.teamcode.Teleop;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class LobsterCup extends LinearOpMode {

    DcMotorEx leftFront;
    DcMotorEx rightFront;
    DcMotorEx leftRear;
    DcMotorEx rightRear;
    DcMotorEx ExpMotor0;
    DcMotorEx ExpMotor1;
    DcMotorEx ExpMotor2;
    DcMotorEx ExpMotor3;
    Servo Servo0;
    Servo Servo1;
    Servo Servo2;
    Servo Servo3;


    @Override
    public void runOpMode() throws InterruptedException {

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();

        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        ExpMotor0 = hardwareMap.get(DcMotorEx.class, "ExpMotor0");
        ExpMotor0.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor1 = hardwareMap.get(DcMotorEx.class, "ExpMotor1");
        ExpMotor2 = hardwareMap.get(DcMotorEx.class, "ExpMotor2");
        ExpMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor3 = hardwareMap.get(DcMotorEx.class, "ExpMotor3");

        Servo0 = hardwareMap.get(Servo.class, "Servo0");
        Servo1 = hardwareMap.get(Servo.class, "Servo1");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ExpMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        while (opModeIsActive()) {

            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);
        }
    }
}