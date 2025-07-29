package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
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
public class SpecAuto extends LinearOpMode {


    public class Extendo {
        private DcMotorEx Extendo;

        public Extendo(HardwareMap hardwareMap) {
            Extendo = hardwareMap.get(DcMotorEx.class, "Extendo");
            Extendo.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        public class ExtendoZeroPos implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    Extendo.setPower(0.3);
                    initialized = true;
                }

                double pos = Extendo.getCurrentPosition();
                packet.put("Extendo", pos);
                if (pos <= 2) {
                    return true;
                } else {
                    Extendo.setPower(0);
                    return false;
                }

            }
        }

        public Action extendoZeroPos() {
            return new ExtendoZeroPos();
        }
    }


    public class Claw {
        private Servo Claw;
        public Claw(HardwareMap hardwareMap) {
            Claw = hardwareMap.get(Servo.class, "Claw");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Claw.setPosition(0.3);
                return false;
            }
        }
        public Action closeClaw()  {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Claw.setPosition(0.95);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
    }

    public class Wrist {
        private Servo Wrist ;
        public Wrist(HardwareMap hardwareMap) {
            Wrist = hardwareMap.get(Servo.class, "Wrist");
        }
        public class SpecimenWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Wrist.setPosition(0.435);
                return false;
            }
        }

        public Action specimenWall() {
            return new SpecimenWall();
        }

        public class SpecimenChamberPrepare implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Wrist.setPosition(0);
                return false;
            }
        }

        public Action specimenChamberPrepare() {
            return new SpecimenChamberPrepare();
        }

        public class SpecimenChamber implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                Wrist.setPosition(0.8);
                return false;
            }
        }

        public Action specimenChamber() {
            return new SpecimenChamber();
        }
    }

    public class rightIntake {
        private Servo rightIntakeServo ;
        public rightIntake(HardwareMap hardwareMap) {
            rightIntakeServo = hardwareMap.get(Servo.class, "rightIntakeServo");
        }
        public class IntakePos implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightIntakeServo.setPosition(0.7);
                return false;
            }
        }

        public Action intakePos() {
            return new IntakePos();
        }

        public class IntakeUp implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightIntakeServo.setPosition(0);
                return false;
            }
        }

        public Action intakeUp() {
            return new IntakeUp();
        }
    }

    public class rightDeposit {
        private Servo rightDeposit ;
        public rightDeposit(HardwareMap hardwareMap) {
            rightDeposit = hardwareMap.get(Servo.class, "rightDeposit");
        }

        public class DepSpecimenWall implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightDeposit.setPosition(0);
                return false;
            }
        }

        public Action depSpecimenWall() {
            return new DepSpecimenWall();
        }

        public class DepSpecimenChamberPrepare implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightDeposit.setPosition(1);
                return false;
            }
        }

        public Action depSpecimenChamberPrepare() {
            return new DepSpecimenChamberPrepare();
        }

        public class DepSpecimenChamber implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                rightDeposit.setPosition(0.8);
                return false;
            }
        }

        public Action depSpecimenChamber() {
            return new DepSpecimenChamber();
        }
    }

    public class Intake {
        private DcMotorEx Intake;
        public Intake(HardwareMap hardwareMap)  {
            Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(-4, 59, Math.toRadians(-90));
        PinpointDrive drive = new PinpointDrive(hardwareMap, initialPose);
        Claw claw = new Claw(hardwareMap);
        Wrist wrist = new Wrist(hardwareMap);
        rightDeposit rightDeposit = new rightDeposit(hardwareMap);
        rightIntake rightIntake = new rightIntake(hardwareMap);
        Extendo extendo = new Extendo(hardwareMap);


        TrajectoryActionBuilder SpecimenOne = drive.actionBuilder(initialPose)
                .splineToLinearHeading(new Pose2d(-4.00, 27.00, Math.toRadians(-90.00)), Math.toRadians(-90.00));
        TrajectoryActionBuilder wait2 = drive.actionBuilder(new Pose2d(-4.00, 27.00, Math.toRadians(-90.00)))
                .waitSeconds(0.25);
        TrajectoryActionBuilder wait21 = drive.actionBuilder(new Pose2d(-4.00, 27.00, Math.toRadians(-90.00)))
                .waitSeconds(0.1);
        TrajectoryActionBuilder SpecimenOneBack = drive.actionBuilder(new Pose2d(-4.00, 27.00, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(-4.00, 44.00), Math.toRadians(-90.00));
        TrajectoryActionBuilder wait3 = drive.actionBuilder(new Pose2d(-4.00, 44.00, Math.toRadians(-90.00)))
                .waitSeconds(0.25);
        TrajectoryActionBuilder Push1stSample = drive.actionBuilder(new Pose2d(-4.00, 44.00, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(-17.00, 38.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-36.00, 36.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-36.00, 12.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-46.00, 12.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-46.00, 44.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 12.00), Math.toRadians(-90.00));
        TrajectoryActionBuilder Push2nd3rdSample = drive.actionBuilder(new Pose2d(-46.00, 12.00, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(-53.00, 12.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-54.00, 44.00), Math.toRadians(-90.00));
        TrajectoryActionBuilder SpecimenTwoPickup = drive.actionBuilder(new Pose2d(-54.00, 44.00, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(-48.00, 59.50), Math.toRadians(-90.00));
        TrajectoryActionBuilder wait4 = drive.actionBuilder(new Pose2d(-48.00, 59.50, Math.toRadians(-90.00)))
                .waitSeconds(1.0);
        TrajectoryActionBuilder SpecimenTwoPlace = drive.actionBuilder(new Pose2d(-48.00, 59.50, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(-48.00, 47.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-15.00, 47.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(0, 27.00), Math.toRadians(-90.00));
        TrajectoryActionBuilder wait5 = drive.actionBuilder(new Pose2d(0, 27.00, Math.toRadians(-90.00)))
                .waitSeconds(0.25);
        TrajectoryActionBuilder SpecimenTwoBack = drive.actionBuilder(new Pose2d(0, 27.00, Math.toRadians(-90.00)))
                .splineToConstantHeading(new Vector2d(0, 44.00), Math.toRadians(-90.00));
        TrajectoryActionBuilder wait6 = drive.actionBuilder(new Pose2d(0, 44.00, Math.toRadians(-90.00)))
                .waitSeconds(0.25);
        TrajectoryActionBuilder Park = drive.actionBuilder(new Pose2d(0, 44.00, Math.toRadians(-90.00)))
            .splineToConstantHeading(new Vector2d(-48.00, 59.50), Math.toRadians(-90.00));
             /*   .splineToLinearHeading(new Pose2d(-48.00, 34.00, Math.toRadians(210.00)), Math.toRadians(210.00));
                .splineToLinearHeading(new Pose2d(-48.00, 35.00, Math.toRadians(115.00)), Math.toRadians(115.00))
                .splineToLinearHeading(new Pose2d(-58.00, 34.00, Math.toRadians(208.00)), Math.toRadians(208.00)); */
             /*   .splineToSplineHeading(new Pose2d(-58.00, 35.00, Math.toRadians(90.00)), Math.toRadians(90.00))
                .splineToSplineHeading(new Pose2d(-51.12, 47.43, Math.toRadians(-90.00)), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-5.50, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-6.00, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-5.50, 32.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 60.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 64.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-44.00, 56.00), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(-24.64, 52.89), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(-6.00, 32.00), Math.toRadians(-90.00)); */

        Actions.runBlocking(rightDeposit.depSpecimenWall());
        Actions.runBlocking(wrist.specimenChamberPrepare());
        Actions.runBlocking(extendo.extendoZeroPos());

        waitForStart();

        if (isStopRequested()) return;

        Action specimenOne;
        specimenOne = SpecimenOne.build();

        Action Wait2;
        Wait2 = wait2.build();

        Action Wait21;
        Wait21 = wait21.build();

        Action specimenOneBack;
        specimenOneBack = SpecimenOneBack.build();

        Action push1stSample;
        push1stSample = Push1stSample.build();

        Action push2nd3rdSample;
        push2nd3rdSample = Push2nd3rdSample.build();

        Action specimenTwoPickup;
        specimenTwoPickup = SpecimenTwoPickup.build();

        Action Wait4;
        Wait4 = wait4.build();

        Action Wait5;
        Wait5 = wait5.build();

        Action Wait6;
        Wait6 = wait6.build();

        Action Wait3;
        Wait3 = wait3.build();

        Action specimenTwoPlace;
        specimenTwoPlace = SpecimenTwoPlace.build();

        Action specimenTwoBack;
        specimenTwoBack  = SpecimenTwoBack.build();

        Actions.runBlocking(
                new SequentialAction(
                        claw.closeClaw(),
                        new ParallelAction(
                                specimenOne,
                                rightDeposit.depSpecimenChamberPrepare(),
                                wrist.specimenChamberPrepare()
                        ),
                        Wait2,
                        new ParallelAction(
                                rightDeposit.depSpecimenChamber(),
                                wrist.specimenChamber()
                        ),
                        specimenOneBack,
                        Wait3,
                        claw.openClaw(),
                        new ParallelAction(
                                push1stSample,
                                rightDeposit.depSpecimenWall(),
                                wrist.specimenWall()
                        ),
                        push2nd3rdSample,
                        specimenTwoPickup,
                        Wait4,
                        claw.closeClaw(),
                        new ParallelAction(
                                specimenTwoPlace,
                                rightDeposit.depSpecimenChamberPrepare(),
                                wrist.specimenChamberPrepare()
                        ),
                        Wait5,
                        new ParallelAction(
                                rightDeposit.depSpecimenChamber(),
                                wrist.specimenChamber()
                        ),
                        specimenTwoBack,
                        Wait6,
                        claw.openClaw()







                )
        );
    }
}