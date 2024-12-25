package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Config
@Autonomous
public class BlueSampleAuton extends LinearOpMode {

    public static double horizontal_claw = 0.21;
    public static double vertical_claw = 0.54;
    public static double claw_open = 0.5;
    public static double claw_close = 0.9;

    public static double strafex1 = 9.5;
    public static double strafey1 = 33;
    public static double strafex2 = 57.5;
    public static double strafey2 = 40;
    public static double strafex3 = 57.5;
    public static double strafey3 = 44;
    public static double strafex4 = 57.5;
    public static double strafey4 = 44;

    public static double strafex5 = 57.5;
    public static double strafey5 = 44;
    public static double strafex6 = 57.5;
    public static double strafey6 = 44;
    public static double strafex7 = 57.5;
    public static double strafey7 = 44;
    public static double strafex8 = 57.5;
    public static double strafey8 = 44;
    public static double strafex9 = 57.5;
    public static double strafey9 = 44;
    public static double strafex10 = 57.5;
    public static double strafey10 = 44;

    public class Slide {
        private DcMotorEx leftslide;
        private DcMotorEx rightslide;

        public Slide(HardwareMap hardwareMap) {
            leftslide = hardwareMap.get(DcMotorEx.class, "ExpMotor0");
            leftslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftslide.setDirection(DcMotorSimple.Direction.REVERSE);

            rightslide = hardwareMap.get(DcMotorEx.class, "ExpMotor1");
            rightslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        }
    }

    public class Pivot {
        private DcMotorEx rightpivot;
        private DcMotorEx leftpivot;

        public Pivot(HardwareMap hardwareMap) {
            rightpivot = hardwareMap.get(DcMotorEx.class, "ExpMotor3");
            rightpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightpivot.setDirection(DcMotorSimple.Direction.REVERSE);
            leftpivot = hardwareMap.get(DcMotorEx.class, "ExpMotor3");
            leftpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftpivot.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        public class RightPivotBucket implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 1500.0) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    return false;
                }

            }
        }

        public class LeftPivotBucket implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = leftpivot.getCurrentPosition();
                packet.put("leftPivot", pos);
                if (pos < 1500.0) {
                    return true;
                } else {
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action rightPivotBucket() {
            return new RightPivotBucket();
        }

        public Action leftPivotBucket() {
            return new LeftPivotBucket();
        }
    }


    public class RotationClaw {
        private Servo clawrotate;

        public RotationClaw(HardwareMap hardwareMap) {
            clawrotate = hardwareMap.get(Servo.class, "Servo0");
        }

        public class VerticalClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                clawrotate.setPosition(vertical_claw);
                return false;
            }
        }

        public Action verticalClaw() {
            return new VerticalClaw();
        }

        public class HorizontalCLaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                clawrotate.setPosition(horizontal_claw);
                return false;
            }
        }

        public Action horizontalClaw() {
            return new HorizontalCLaw();
        }
    }

    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hardwareMap) {
            claw = hardwareMap.get(Servo.class, "Servo1");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(claw_close);
                return false;
            }
        }

        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                claw.setPosition(claw_open);
                return false;
            }
        }

        public Action openClaw() {
            return new OpenClaw();
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(9.5, 60.6, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Pivot lift = new Pivot(hardwareMap);
        RotationClaw clawrotate = new RotationClaw(hardwareMap);

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(strafex1, strafey1))
                .strafeTo(new Vector2d(strafex2, strafex2))
                .strafeTo(new Vector2d(strafex3, strafey3))
                .waitSeconds(3);


        waitForStart();

            Actions.runBlocking(
                    new SequentialAction(
                            tab1.build()
                    )
            );


        }

    }

