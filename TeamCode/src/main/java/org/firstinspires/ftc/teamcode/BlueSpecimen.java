package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;


import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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

@Config
@Autonomous
public class BlueSpecimen extends LinearOpMode {

    public static double vertical_claw = 0;
    public static double horizontal_claw = 0.98;

    public static double sample3_claw = 0.23;
    public static double claw_open = 0;
    public static double claw_close = 1;


    public static double SpecimenTurnPos = 320;
    public static double PivotBucketPos = 1780;
    public static double PivotBucket1Pos = 1500;
    public static double PivotSpecimen1Pos = 1400;
    public static double PivotSpecimen2Pos = 1685;
    public static double PivotPickupPos = 3500;
    public static double SlideFullPos = 2900;
    public static double SlideSpecimenPos = 1000;
    public static double SlideDownPos = 50;
    public static double SlideSpecimenDownPos = 100;

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
                    rightslide.setPower(1);
                    leftslide.setPower(1);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos);
                if (pos < SlideFullPos) {
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
                    rightslide.setPower(-1);
                    leftslide.setPower(-1);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos1);
                if (pos > SlideDownPos) {
                    return true;
                } else {
                    rightslide.setPower(0);
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public class SlideSpecimenDown implements Action {
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
                if (pos > SlideSpecimenDownPos) {
                    return true;
                }
                else {
                    rightslide.setPower(0);
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action slideDown() {
            return new SlideDown();
        }

        public Action slideSpecimenDown() {
            return new SlideSpecimenDown();
        }
        public class SlideSpecimen implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.8);
                    leftslide.setPower(0.8);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                double pos1 = leftslide.getCurrentPosition();
                packet.put("rightSlide", pos);
                packet.put("leftSlide", pos1);
                if (pos < SlideSpecimenPos) {
                    return true;
                }
                if (pos1 < SlideSpecimenPos) {
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
            rightpivot.setDirection(DcMotorEx.Direction.REVERSE);
            leftpivot = hardwareMap.get(DcMotorEx.class, "ExpMotor3");
            leftpivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftpivot.setDirection(DcMotorSimple.Direction.REVERSE);
            leftpivot.setDirection(DcMotorEx.Direction.FORWARD);

        }

        public class PivotSpecimen1 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(-0.8);
                    leftpivot.setPower(-0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos < PivotSpecimen1Pos) {
                    return true;
                }
                if (pos1 < PivotSpecimen1Pos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action pivotSpecimen1() {
            return new PivotSpecimen1();
        }

        public class PivotSpecimen3 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.8);
                    leftpivot.setPower(0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos > PivotSpecimen1Pos) {
                    return true;
                }
                if (pos1 > PivotSpecimen1Pos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action pivotSpecimen3() {
            return new PivotSpecimen3();
        }

        public class PivotBucket implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(-0.65);
                    leftpivot.setPower(-0.65);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos < PivotBucketPos) {
                    return true;
                }
                if (pos1 < PivotBucketPos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }
        public Action pivotBucket(){
            return new PivotBucket();
        }

        public class PivotBucket1 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.8);
                    leftpivot.setPower(0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos > PivotBucket1Pos) {
                    return true;
                }
                if (pos1 > PivotBucket1Pos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }
        public Action pivotBucket1(){
            return new PivotBucket1();
        }
        public class PivotSpecimen2 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(-0.8);
                    leftpivot.setPower(-0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos < PivotSpecimen2Pos ) {
                    return true;
                }
                if (pos1 < PivotSpecimen2Pos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action pivotSpecimen2() {
            return new PivotSpecimen2();
        }

        public class PivotSpecimen4 implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.8);
                    leftpivot.setPower(0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put(" leftPivot", pos1);
                if (pos > PivotSpecimen2Pos ) {
                    return true;
                }
                if (pos1 > PivotSpecimen2Pos) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    leftpivot.setPower(0);
                    return false;
                }

            }
        }

        public Action pivotSpecimen4() {
            return new PivotSpecimen4();
        }


        public class PivotPickup implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(-0.8);
                    leftpivot.setPower(-0.8);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                double pos1 = leftpivot.getCurrentPosition();
                packet.put("leftPivot", pos1);
                if (pos < PivotPickupPos) {
                    return true;
                }
                if (pos1 < PivotPickupPos) {
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

        public class HorizontalClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                clawrotate.setPosition(horizontal_claw);
                return false;
            }
        }

        public Action horizontalClaw() {
            return new HorizontalClaw();
        }
        public class Sample3Claw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                clawrotate.setPosition(sample3_claw);
                return false;
            }
        }
        public Action sample3Claw() {
            return new Sample3Claw();
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
    public void runOpMode() {
        Pose2d initialPose = new Pose2d(-9.5, 36, Math.toRadians(90));
       //Pose2d initialPose = new Pose2d(-9.5, 61.6, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Slide leftslide = new Slide(hardwareMap);
        Slide rightslide = new Slide(hardwareMap);
        Pivot leftpivot = new Pivot(hardwareMap);
        Pivot rightpivot = new Pivot(hardwareMap);
        RotationClaw clawrotate = new RotationClaw(hardwareMap);

        TrajectoryActionBuilder SpecimenPlace = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(-9.5, 36));

        TrajectoryActionBuilder wait1 = drive.actionBuilder(new Pose2d(-9.5, 36, Math.toRadians(90)))
                .waitSeconds(1);
        TrajectoryActionBuilder SamplePush = drive.actionBuilder(new Pose2d(-9.5, 36, Math.toRadians(90)))
                .strafeTo(new Vector2d(-36, 41))
                .strafeTo(new Vector2d(-36, 12))
                .strafeTo(new Vector2d(-46, 12))
                .strafeTo(new Vector2d(-48, 52.5));

        TrajectoryActionBuilder SpecimenPickup1 = drive.actionBuilder(new Pose2d(-48, 52.5, Math.toRadians(90)))
                .strafeToSplineHeading(new Vector2d(-35, 47.5), Math.toRadians(SpecimenTurnPos));
        TrajectoryActionBuilder wait2 = drive.actionBuilder(new Pose2d(-35, 47.5, Math.toRadians(330)))
                .waitSeconds(0.75);
        TrajectoryActionBuilder SpecimenPlace2 = drive.actionBuilder(new Pose2d(-35, 47.5, Math.toRadians(330)))
                .strafeToSplineHeading(new Vector2d(-7, 38), Math.toRadians(90));
        TrajectoryActionBuilder wait4 = drive.actionBuilder(new Pose2d(-7, 38, Math.toRadians(90)))
                .waitSeconds(0.75);
        TrajectoryActionBuilder SpecimenBack2 = drive.actionBuilder(new Pose2d(-7, 38, Math.toRadians(90)))
                .strafeToSplineHeading(new Vector2d(-7, 43), Math.toRadians(90));
        TrajectoryActionBuilder SpecimenPickup3 = drive.actionBuilder(new Pose2d(-7, 43, Math.toRadians(90)))
                .strafeToSplineHeading(new Vector2d(-35, 47.5), Math.toRadians(SpecimenTurnPos));
        TrajectoryActionBuilder wait3 = drive.actionBuilder(new Pose2d(-35, 47.5, Math.toRadians(330)))
                .waitSeconds(0.75);
        TrajectoryActionBuilder SpecimenPlace3= drive.actionBuilder(new Pose2d(-35, 47.5, Math.toRadians(330)))
                .strafeToSplineHeading(new Vector2d(-5, 38), Math.toRadians(90));

        Actions.runBlocking(claw.closeClaw());


        waitForStart();
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                leftpivot.pivotSpecimen1(),
                                rightpivot.pivotSpecimen1(),
                                SpecimenPlace.build()),

                        leftslide.slideSpecimen(),
                        rightslide.slideSpecimen(),
                        leftpivot.pivotSpecimen2(),
                        rightpivot.pivotSpecimen2(),
                        leftslide.slideSpecimenDown(),
                        rightslide.slideSpecimenDown(),
                        claw.openClaw(),
                        wait1.build(),
                        leftpivot.pivotSpecimen3(),
                        rightpivot.pivotSpecimen3(),
                        SamplePush.build(),

                        SpecimenPickup1.build(),
                        leftpivot.pivotPickup(),
                        rightpivot.pivotPickup(),
                        clawrotate.horizontalClaw(),
                        claw.closeClaw(),
                        wait2.build(),

                        leftpivot.pivotSpecimen3(),
                        rightpivot.pivotSpecimen3(),
                        SpecimenPlace2.build(),
                        leftslide.slideSpecimen(),
                        rightslide.slideSpecimen(),
                        leftpivot.pivotSpecimen2(),
                        rightpivot.pivotSpecimen2(),
                        leftslide.slideSpecimenDown(),
                        rightslide.slideSpecimenDown(),
                        claw.openClaw(),
                        wait4.build(),
                        SpecimenBack2.build(),

                        SpecimenPickup3.build(),
                        leftpivot.pivotPickup(),
                        rightpivot.pivotPickup(),
                        clawrotate.horizontalClaw(),
                        claw.closeClaw(),
                        wait3.build(),

                        leftpivot.pivotSpecimen3(),
                        rightpivot.pivotSpecimen3(),
                        SpecimenPlace3.build(),
                        leftslide.slideSpecimen(),
                        rightslide.slideSpecimen(),
                        leftpivot.pivotSpecimen2(),
                        rightpivot.pivotSpecimen2(),
                        leftslide.slideSpecimenDown(),
                        rightslide.slideSpecimenDown(),
                        claw.openClaw()


                )
        );
    }

}

