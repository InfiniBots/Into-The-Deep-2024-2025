
package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous
public class RightSideAuton2 extends OpMode {
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

    public static double horizontal_claw = 1;
    public static double vertical_claw = 0.38;
    public static double claw_open = 0.25;
    public static double claw_close = 0.55;

    public static int sleep_time = 826;
    public static int sleep_time2 = 1400;
    public static int sleep_time3 = 1300;
    public static int sleep_time4 = 300;
    public static int sleep_time5= 800;
    public static int sleep_time6= 300;
    public static int sleep_time7= 300;
    public static int sleep_time8= 300;
    public static int sleep_time9= 300;
    public static int sleep_time10= 300;
    public static int Starting_Specimen = 0;
    public static int Specimen_Forward = 0;


    @Override
    public void init() {

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

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(Starting_Specimen);
        leftRear.setTargetPosition(Starting_Specimen);
        rightRear.setTargetPosition(Starting_Specimen);
        rightFront.setTargetPosition(Starting_Specimen);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ExpMotor0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ExpMotor3.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Servo1.setPosition(0.55);

    }

    @Override
    public void start() {

        leftFront.setTargetPosition(Starting_Specimen);
        leftRear.setTargetPosition(Starting_Specimen);
        rightRear.setTargetPosition(Starting_Specimen);
        rightFront.setTargetPosition(Starting_Specimen);


        ExpMotor2.setPower(-0.5);
        ExpMotor3.setPower(-0.4);

        sleep(sleep_time2);
        ExpMotor2.setPower(0);
        ExpMotor3.setPower(0);

        ExpMotor0.setPower(0.5);
        ExpMotor1.setPower(0.5);

        sleep(sleep_time3);
        ExpMotor0.setPower(0);
        ExpMotor1.setPower(0);

        ExpMotor2.setPower(-0.5);
        ExpMotor3.setPower(-0.4);
        sleep(sleep_time4);

        ExpMotor2.setPower(0);
        ExpMotor3.setPower(0);

        ExpMotor0.setPower(-0.7);
        ExpMotor1.setPower(-0.7);

        sleep(sleep_time5);
        ExpMotor0.setPower(0);
        ExpMotor1.setPower(0);

        Servo1.setPosition(0.25);

    }

    @Override
    public void loop() {

    }
}


