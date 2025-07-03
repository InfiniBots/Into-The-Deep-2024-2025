package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Disabled
@TeleOp
public class Main extends LinearOpMode {
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

    public static double horizontal_claw = 0.21;
    public static double vertical_claw = 0.54;
    public static double claw_open = 0.5;
    public static double claw_close = 0.9;
    public static double max_slide = 4000;

    public static int LeftSlideFull = 850;

    public static int RightSlideFull = 850;

    @Override
    public void runOpMode() throws InterruptedException {


        Gamepad currentGamepad1 = new Gamepad();
        Gamepad currentGamepad2 = new Gamepad();
        Gamepad previousGamepad1 = new Gamepad();
        Gamepad previousGamepad2 = new Gamepad();

        int LeftSlideDown = 0;
        int RightSlideDown = 0;

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

        ExpMotor0.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ExpMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ExpMotor0.setTargetPosition(LeftSlideDown);
        ExpMotor1.setTargetPosition(RightSlideDown);

        ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

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


            if(gamepad2.a) {
                ExpMotor0.setTargetPosition(LeftSlideFull);
                ExpMotor1.setTargetPosition(RightSlideFull);
                ExpMotor0.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ExpMotor0.setPower(-0.8);
                ExpMotor1.setPower(0.8);
            }

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

            leftFront.setPower(frontLeft / -1.25);
            leftRear.setPower(backLeft / -1.25);
            rightFront.setPower(frontRight / 1.25);
            rightRear.setPower(backRight / 1.25);

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

            ExpMotor2.setPower(gamepad2.left_stick_y/-1);
            ExpMotor3.setPower(gamepad2.left_stick_y/-1);

        }
    }
}



