package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class Diddy extends LinearOpMode {
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


        int LeftSlideFull = 4390;
        int LeftSlideDown = 0;
        int RightSlideFull = 4390;
        int RightSlideDown = 0;
        int LeftSlideSubmersible = 500;
        int RightSlideSubmersible = 500;

        int LeftPivotBucket = 1561;
        int RightPivotBucket = 1561;
        int LeftPivotDown = 0;
        int RightPivotDown = 0;
        int LeftPivotSubmersible = 3550;
        int RightPivotSubmersible = 3550;

        frontLeftMotor = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backLeftMotor = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "backRightMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frontRightMotor");

        ExpMotor0 = hardwareMap.get(DcMotor.class, "ExpMotor0");
        ExpMotor0.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor1 = hardwareMap.get(DcMotor.class, "ExpMotor1");
        ExpMotor2 = hardwareMap.get(DcMotor.class, "ExpMotor2");
        ExpMotor3 = hardwareMap.get(DcMotor.class, "ExpMotor3");

        CRServo1 = hardwareMap.get(CRServo.class, "CRServo1");
        Servo0 = hardwareMap.get(Servo.class, "Servo0");
        Servo1 = hardwareMap.get(Servo.class, "Servo1");

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ExpMotor0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExpMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExpMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExpMotor3.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ExpMotor0.setTargetPosition(LeftSlideDown);
        ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ExpMotor1.setTargetPosition(LeftSlideDown);
        ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ExpMotor2.setTargetPosition(LeftPivotDown);
        ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ExpMotor3.setTargetPosition(RightPivotDown);
        ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Servo0.setPosition(0);
        Servo1.setPosition(1);  



        waitForStart();

        while (opModeIsActive()) {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(gamepad1);
            currentGamepad2.copy(gamepad2);

            int position1 = ExpMotor0.getCurrentPosition();
            int position2 = ExpMotor1.getCurrentPosition();
            int position3 = ExpMotor2.getCurrentPosition();
            int position4 = ExpMotor3.getCurrentPosition();

            int position1T = ExpMotor0.getTargetPosition();
            int position2T = ExpMotor1.getTargetPosition();
            int position3T = ExpMotor2.getTargetPosition();
            int position4T = ExpMotor3.getTargetPosition();

            double x = -gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;

            double theta = Math.atan2(y, x);
            double power = Math.hypot(x, y);

            double sin = Math.sin(theta - Math.PI / 4);
            double cos = Math.cos(theta - Math.PI / 4);
            double max = Math.max(Math.abs(sin), Math.abs(cos));

            double frontLeft = power * cos / max + turn;
            double frontRight = power * sin / max - turn;
            double backLeft = power * sin / max + turn;
            double backRight = power * cos / max - turn;

            if ((power + Math.abs(turn)) > 1) {
                frontLeft /= power + Math.abs(turn);
                frontRight /= power + Math.abs(turn);
                backLeft /= power + Math.abs(turn);
                backRight /= power + Math.abs(turn);
            }

            frontLeftMotor.setPower(frontLeft / -1.25); /* hi cants*/
            backLeftMotor.setPower(backLeft / -1.25);
            frontRightMotor.setPower(frontRight / 1.25);
            backRightMotor.setPower(backRight / 1.25);


            if (gamepad2.y) {
                ExpMotor0.setTargetPosition(LeftSlideFull);
                ExpMotor1.setTargetPosition(RightSlideFull);
                ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor0.setPower(1);
                ExpMotor1.setPower(1);
            }

            if (gamepad2.b) {
                ExpMotor0.setTargetPosition(LeftSlideDown);
                ExpMotor1.setTargetPosition(RightSlideDown);
                ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor0.setPower(1);
                ExpMotor1.setPower(1);
            }

            if (gamepad2.x) {
                /*ExpMotor2.setTargetPosition(LeftPivotBucket);*/
                ExpMotor3.setTargetPosition(RightPivotBucket);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(1);
                ExpMotor3.setPower(1);
            }

            if (gamepad2.a) {
                /*ExpMotor2.setTargetPosition(LeftPivotDown);*/
                ExpMotor3.setTargetPosition(RightPivotDown);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(1);
                ExpMotor3.setPower(1);

            }

            if (gamepad2.left_bumper) {
                Servo0.setPosition(0.3);
                Servo1.setPosition(-0.3);
            }

            if(gamepad2.right_bumper)   {
                Servo0.setPosition(0);
                Servo1.setPosition(0);
            }

            if(gamepad1.left_bumper) {
                /*ExpMotor2.setTargetPosition(LeftPivotSubmersible);*/
                ExpMotor3.setTargetPosition(RightPivotSubmersible);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(0.5);
                ExpMotor3.setPower(0.5);
            }

            if(gamepad1.right_bumper) {
                ExpMotor0.setTargetPosition(LeftSlideSubmersible);
                ExpMotor1.setTargetPosition(RightSlideSubmersible);
                ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor0.setPower(0.5);
                ExpMotor1.setPower(0.5);
            }


                telemetry.addData("Left Slide", position1);
                telemetry.addData("Right Slide", position2);
                telemetry.addData("Left Pivot", position3);
                telemetry.addData("Right Pivot", position4);
                telemetry.addData("Encoder Position", position1T);
                telemetry.addData("Encoder Position", position2T);
                telemetry.addData("Encoder Position", position3T);
                telemetry.addData("Encoder Position", position4T);
                telemetry.update();


        }
    }
}

