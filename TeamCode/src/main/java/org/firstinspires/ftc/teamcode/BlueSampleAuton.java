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
            rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        public class RightSlideFull implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 4200.0) {
                    return true;
                } else {
                    rightslide.setPower(0);
                    return false;
                }

            }
        }
        public class LeftSlideFull implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftslide.setPower(0.5);
                    initialized = true;
                }

                double pos = leftslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 4200.0) {
                    return true;
                } else {
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action rightSlideFull() {
            return new RightSlideFull();
        }

        public Action leftSlideFull() {
            return new LeftSlideFull();
        }
        public class RightSlideDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 5.0) {
                    return true;
                } else {
                    rightslide.setPower(0);
                    return false;
                }

            }
        }
        public class LeftSlideDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftslide.setPower(0.5);
                    initialized = true;
                }

                double pos = leftslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 5.0) {
                    return true;
                } else {
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action rightSlideDown() {
            return new RightSlideDown();
        }

        public Action leftSlideDown() {
            return new LeftSlideDown();
        }
        public class RightSlideSpecimen implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightslide.setPower(0.5);
                    initialized = true;
                }

                double pos = rightslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 1000.0) {
                    return true;
                } else {
                    rightslide.setPower(0);
                    return false;
                }

            }
        }
        public class LeftSlideSpecimen implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftslide.setPower(0.5);
                    initialized = true;
                }

                double pos = leftslide.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 1000.0) {
                    return true;
                } else {
                    leftslide.setPower(0);
                    return false;
                }

            }
        }
        public Action rightSlideSpecimen() {
            return new RightSlideSpecimen();
        }

        public Action leftSlideSpecimen() {
            return new LeftSlideSpecimen();
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

        public class LeftPivotPickup implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    leftpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = leftpivot.getCurrentPosition();
                packet.put("leftPivot", pos);
                if (pos < 3000.0) {
                    return true;
                } else {
                    leftpivot.setPower(0);
                    return false;
                }
            }
        }
        public class RightPivotPickup implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    rightpivot.setPower(0.5);
                    initialized = true;
                }

                double pos = rightpivot.getCurrentPosition();
                packet.put("rightPivot", pos);
                if (pos < 3000.0) {
                    return true;
                } else {
                    rightpivot.setPower(0);
                    return false;
                }

            }
        }
        public Action leftPivotPickup() {
            return new LeftPivotPickup();
        }
        public Action rightPivotPickup() {
            return new RightPivotPickup();
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
                .strafeTo(new Vector2d(strafex1, strafey1))
                .waitSeconds(3);

        TrajectoryActionBuilder SamplePickup1 = drive.actionBuilder(initialPose)
                .strafeTo(new Vector2d(strafex2, strafex2))
                .strafeTo(new Vector2d(strafex3, strafey3));

        TrajectoryActionBuilder BucketDrop1 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(BucketDrop1Turn))
                .strafeTo(new Vector2d(BucketDrop1X, BucketDrop1Y));


        TrajectoryActionBuilder SamplePickup2 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(SamplePickup2Turn))
                .strafeTo(new Vector2d(SamplePickup2X, SamplePickup2Y));


        TrajectoryActionBuilder BucketDrop2 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(BucketDrop2Turn))
                .strafeTo(new Vector2d(BucketDrop2X, BucketDrop2Y));


        TrajectoryActionBuilder SamplePickup3 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(SamplePickup3Turn))
                .strafeTo(new Vector2d(SamplePickup3X, SamplePickup3Y));


        TrajectoryActionBuilder BucketDrop3 = drive.actionBuilder(initialPose)
                .turn(Math.toRadians(BucketDrop3Turn))
                .strafeTo(new Vector2d(BucketDrop3X, BucketDrop3Y));


        waitForStart();
            Actions.runBlocking(
                    new SequentialAction(
                            SpecimenPlace.build(),
                            leftpivot.leftPivotBucket(),
                            rightpivot.rightPivotBucket(),
                            leftslide.leftSlideSpecimen(),
                            rightslide.rightSlideSpecimen(),
                            claw.openClaw(),

                            SamplePickup1.build(),
                            leftpivot.leftPivotPickup(),
                            rightpivot.rightPivotPickup(),
                            claw.closeClaw(),
                            leftpivot.leftPivotBucket(),
                            rightpivot.rightPivotBucket(),

                            BucketDrop1.build(),
                            leftslide.leftSlideFull(),
                            rightslide.rightSlideFull(),
                            leftslide.leftSlideDown(),
                            rightslide.rightSlideDown(),

                            SamplePickup2.build(),
                            leftpivot.leftPivotPickup(),
                            rightpivot.rightPivotPickup(),
                            claw.closeClaw(),
                            leftpivot.leftPivotBucket(),
                            rightpivot.rightPivotBucket(),

                            BucketDrop2.build(),
                            leftslide.leftSlideFull(),
                            rightslide.rightSlideFull(),
                            leftslide.leftSlideDown(),
                            rightslide.rightSlideDown(),

                            SamplePickup3.build(),
                            leftpivot.leftPivotPickup(),
                            rightpivot.rightPivotPickup(),
                            claw.closeClaw(),
                            leftpivot.leftPivotBucket(),
                            rightpivot.rightPivotBucket(),

                            BucketDrop3.build(),
                            leftslide.leftSlideFull(),
                            rightslide.rightSlideFull(),
                            leftslide.leftSlideDown(),
                            rightslide.rightSlideDown()

                    )
            );


        }

    }

