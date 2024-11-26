package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class Debugger extends LinearOpMode {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    CRServo CRServo1;
    DcMotor ExpMotor0;
    DcMotor ExpMotor1;
    DcMotor ExpMotor2;
    DcMotor ExpMotor3;
    Servo Servo0;
    Servo Servo1;

    @Override
    public void runOpMode() throws InterruptedException {

        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");

        ExpMotor0 = hardwareMap.get(DcMotor.class, "ExpMotor0");
        ExpMotor0.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor1 = hardwareMap.get(DcMotor.class, "ExpMotor1");
        ExpMotor2 = hardwareMap.get(DcMotor.class, "ExpMotor2");
        ExpMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor3 = hardwareMap.get(DcMotor.class, "ExpMotor3");

        CRServo1 = hardwareMap.get(CRServo.class, "CRServo1");
        Servo0 = hardwareMap.get(Servo.class, "Servo0");
        Servo1 = hardwareMap.get(Servo.class, "Servo1");




        waitForStart();

        while (opModeIsActive()) {

            int LeftSlide = ExpMotor0.getCurrentPosition();
            int RightSlide = ExpMotor1.getCurrentPosition();
            int LeftPivot = ExpMotor2.getCurrentPosition();
            int RightPivot = ExpMotor3.getCurrentPosition();

            int position1T = ExpMotor0.getTargetPosition();
            int position2T = ExpMotor1.getTargetPosition();
            int position3T = ExpMotor2.getTargetPosition();
            int position4T = ExpMotor3.getTargetPosition();

            ExpMotor0.setPower(gamepad1.right_trigger/4);
            ExpMotor1.setPower(gamepad1.left_trigger/4);
            ExpMotor2.setPower(gamepad2.left_trigger/4);
            ExpMotor3.setPower(gamepad2.right_trigger/4);

            telemetry.addData("Encoder Position", LeftSlide);
            telemetry.addData("Encoder Position", RightSlide);
            telemetry.addData("Encoder Position", LeftPivot);
            telemetry.addData("Encoder Position", RightPivot);
            telemetry.addData("Encoder Position", position1T);
            telemetry.addData("Encoder Position", position2T);
            telemetry.addData("Encoder Position", position3T);
            telemetry.addData("Encoder Position", position4T);
            telemetry.update();




        }
    }
}

