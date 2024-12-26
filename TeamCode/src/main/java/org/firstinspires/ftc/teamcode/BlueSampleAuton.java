package org.firstinspires.ftc.teamcode;

import android.app.usage.NetworkStats;

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
    public static double strafey2 = 38;
    public static double strafex3 = 57.5;
    public static double strafey3 = 47.5;
    public static double BucketDrop1Turn = 135;
    public static double BucketDrop1X = 53;

    public static double BucketDrop1Y = 53;

    public static double SamplePickup2Turn = -135;
    public static double SamplePickup2X = 67.5;
    public static double SamplePickup2Y = 47.5;
    public static double BucketDrop2Turn = 135;
    public static double BucketDrop2X = 53;
    public static double BucketDrop2Y = 53;
    public static double SamplePickup3Turn = -135;
    public static double SamplePickup3X = 77.5;
    public static double SamplePickup3Y = 47.5;
    public static double BucketDrop3Turn = 135;
    public static double BucketDrop3X = 53;
    public static double BucketDrop3Y = 53;

    public class Slide {
        private DcMotorEx leftslide;
        private DcMotorEx rightslide;

        public Slide(HardwareMap hardwareMap) {
            leftslide = hardwareMap.get(DcMotorEx.class, "ExpMotor0");
            leftslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftslide.setDirection(DcMotorSimple.Direction.REVERSE);

            rightslide = hardwareMap.get(DcMotorEx.class, "ExpMotor1");
            rightslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        public class SlideFull implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.5);
                    leftslide.setPower(0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos);
                if (pos < 3500.0) {
                    return true;
                }
                if (pos1 < 3500.0) {
                    return true;
                }
                else {
                    rightslide.setPower(0);
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action slideFull() {
            return new SlideFull();
        }

        public class SlideDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(-0.5);
                    leftslide.setPower(-0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos1);
                if (pos > 100.0) {
                    return true;
                }
                if (pos1 > 100.0) {
                    return true;
                } else {
                    rightslide.setPower(0);
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action slideDown() {
            return new SlideDown();
        }
        public class SlideSpecimen implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.5);
                    leftslide.setPower(0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos1);
                if (pos < 1000.0) {
                    return true;
                }
                if (pos1 < 1000.0) {
                    return true;
                }
                else {
                    rightslide.setPower(0);
                    leftslide.setPower(0);
                    return false;
                }
            }

        }
        public Action slideSpecimen() {
            return new SlideSpecimen();
        }

    }

    public class Pivot {
        private DcMotorEx rightpivot;
        private DcMotorEx leftpivot;
        public Pivot(HardwareMap hardwareMap) {
            rightpivot = hardwareMap.get(DcMotorEx.class, "ExpMotor2");
            rightpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftpivot = hardwareMap.get(DcMotorEx.class, "ExpMotor3");
            leftpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftpivot.setDirection(DcMotorSimple.Direction.REVERSE);

        }

        public class PivotBucket implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.5);
                    leftpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put("leftPivot", pos1);
                if (pos < 100.0) {
                    return true;
                }
                if (pos1 < 100.0) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action pivotBucket() {
            return new PivotBucket();
        }

        public class PivotPickup implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.5);
                    leftpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put("leftPivot", pos1);
                if (pos < 3000.0) {
                    return true;
                }
                if (pos1 < 3000.0) {
                    return true;
                }else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }
        public Action pivotPickup() {
            return new PivotPickup();
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
        Slide leftslide = new Slide(hardwareMap);
        Slide rightslide = new Slide(hardwareMap);
        Pivot leftpivot = new Pivot(hardwareMap);
        Pivot rightpivot = new Pivot(hardwareMap);
        RotationClaw clawrotate = new RotationClaw(hardwareMap);

        TrajectoryActionBuilder SpecimenPlace = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(9.5, 33))
                .strafeTo(new Vector2d(58.5, 40.5))
                .waitSeconds(3)

                .turn(Math.toRadians(135))
                .strafeTo(new Vector2d(66, 53))
                .waitSeconds(3)

                .turn(Math.toRadians(-135))
                .strafeTo(new Vector2d(68.5, 40.5))
                .waitSeconds(3)

                .turn(Math.toRadians(135))
                .strafeTo(new Vector2d(66, 53))
                .waitSeconds(3)

                .turn(Math.toRadians(-135))
                .strafeTo(new Vector2d(58, 21.5))
                .turn(Math.toRadians(90))
                .waitSeconds(3)

                .turn(Math.toRadians(45))
                .strafeTo(new Vector2d(66, 53))
                .waitSeconds(3);


        waitForStart();
            Actions.runBlocking(
                    new SequentialAction(
                            SpecimenPlace.build()
                    )
            );
        }

    }

