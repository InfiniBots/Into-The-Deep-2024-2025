package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class Diddy extends LinearOpMode {
    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftRear;
    DcMotor rightRear;
    DcMotor ExpMotor0;
    DcMotor ExpMotor1;
    DcMotor ExpMotor2;
    DcMotor ExpMotor3;
    Servo Servo0;
    Servo Servo1;

    public static double horizontal_claw = 0.635;
    public static double vertical_claw = 0.3;
    public static double claw_open = 0.25;
    public static double claw_close = 0.55;

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

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        ExpMotor0 = hardwareMap.get(DcMotor.class, "ExpMotor0");
        ExpMotor0.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor1 = hardwareMap.get(DcMotor.class, "ExpMotor1");
        ExpMotor2 = hardwareMap.get(DcMotor.class, "ExpMotor2");
        ExpMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
        ExpMotor3 = hardwareMap.get(DcMotor.class, "ExpMotor3");

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

            int position1 = leftRear.getCurrentPosition();
            int position2 = leftFront.getCurrentPosition();
            int position3 = rightRear.getCurrentPosition();
            int position4 = rightFront.getCurrentPosition();

            int position1T = ExpMotor0.getTargetPosition();
            int position2T = ExpMotor1.getTargetPosition();
            int position3T = ExpMotor2.getTargetPosition();
            int position4T = ExpMotor3.getTargetPosition();

            ExpMotor0.setPower(gamepad2.right_stick_y/-1);
            ExpMotor1.setPower(gamepad2.right_stick_y/-1);
            ExpMotor2.setPower(gamepad2.left_stick_y/-1.25);
            ExpMotor3.setPower(gamepad2.left_stick_y/-1.25);

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

            leftFront.setPower(frontLeft / -1.25); /* hi cants*/
            leftRear.setPower(backLeft / -1.25);
            rightFront.setPower(frontRight / 1.25);
            rightRear.setPower(backRight / 1.25);


           /* if (gamepad2.y) {
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
                ExpMotor2.setTargetPosition(LeftPivotBucket);
                ExpMotor3.setTargetPosition(RightPivotBucket);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(1);
                ExpMotor3.setPower(1);
            }

            if (gamepad2.a) {
                ExpMotor2.setTargetPosition(LeftPivotDown);
                ExpMotor3.setTargetPosition(RightPivotDown);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(1);
                ExpMotor3.setPower(1);

            }



            if(gamepad1.left_bumper) {
                ExpMotor2.setTargetPosition(LeftPivotSubmersible);
                ExpMotor3.setTargetPosition(RightPivotSubmersible);
                ExpMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor3.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor2.setPower(0.5);
                ExpMotor3.setPower(0.5);
            }


            if(gamepad2.right_bumper) {
                ExpMotor0.setTargetPosition(LeftSlideSubmersible);
                ExpMotor1.setTargetPosition(RightSlideSubmersible);
                ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor0.setPower(1);
                ExpMotor1.setPower(1);
            }*/

            if(gamepad2.left_bumper) {
                Servo0.setPosition(vertical_claw);
            }
            if(gamepad2.right_bumper) {
                Servo0.setPosition(horizontal_claw);
            }
            if (gamepad2.x) {
                Servo1.setPosition(claw_open);
            }
            if (gamepad2.b) {
                Servo1.setPosition(claw_close);
            }

                telemetry.addData("Left Rear", position1);
                telemetry.addData("Left Front", position2);
                telemetry.addData("Right Rear", position3);
                telemetry.addData("Right Front", position4);
                telemetry.addData("Encoder Position", position1T);
                telemetry.addData("Encoder Position", position2T);
                telemetry.addData("Encoder Position", position3T);
                telemetry.addData("Encoder Position", position4T);
                telemetry.update();


        }
    }
}

