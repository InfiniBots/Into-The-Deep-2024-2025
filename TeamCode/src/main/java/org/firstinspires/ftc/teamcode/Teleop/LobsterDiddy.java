    package org.firstinspires.ftc.teamcode.Teleop;

    import com.arcrobotics.ftclib.command.WaitCommand;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorEx;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.rowanmcalpin.nextftc.core.Subsystem;
    import com.rowanmcalpin.nextftc.core.command.groups.ParallelGroup;
    import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
    import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
    import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
    import com.rowanmcalpin.nextftc.core.command.utility.delays.Delay;
    import com.rowanmcalpin.nextftc.core.command.utility.delays.WaitUntil;
    import com.rowanmcalpin.nextftc.core.command.utility.statemachine.AdvancingCommand;
    import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
    import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;

    import org.firstinspires.ftc.teamcode.Subsystems.Claw;
    import org.firstinspires.ftc.teamcode.Subsystems.Deposit;
    import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
    import org.firstinspires.ftc.teamcode.Subsystems.Intake;
    import org.firstinspires.ftc.teamcode.Subsystems.IntakeColor;
    import org.firstinspires.ftc.teamcode.Subsystems.IntakePivot;
    import org.firstinspires.ftc.teamcode.Subsystems.Lift;
    import org.firstinspires.ftc.teamcode.Subsystems.Wrist;

    import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;
    import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
    import com.rowanmcalpin.nextftc.core.command.Command;
    import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

    import java.security.acl.Group;
    import java.util.Set;

    @TeleOp
    public class LobsterDiddy extends NextFTCOpMode {
        public LobsterDiddy() {
            super( Extendo.INSTANCE, Intake.INSTANCE,  IntakePivot.INSTANCE, Deposit.INSTANCE, Wrist.INSTANCE, Claw.INSTANCE);
        }
        public String frontLeftName = "leftFront";
        public String frontRightName = "rightFront";
        public String backLeftName = "leftRear";
        public String backRightName = "rightRear";

        public MotorEx frontLeftMotor;
        public MotorEx frontRightMotor;
        public MotorEx backLeftMotor;
        public MotorEx backRightMotor;

        public MotorEx[] motors;

        public Command driverControlled;

        @Override
        public void onInit() {

            frontLeftMotor = new MotorEx(frontLeftName);
            backLeftMotor = new MotorEx(backLeftName);
            backRightMotor = new MotorEx(backRightName);
            frontRightMotor = new MotorEx(frontRightName);

            frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

            frontLeftMotor.getMotor().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            backLeftMotor.getMotor().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            backRightMotor.getMotor().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            frontRightMotor.getMotor().setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

            motors = new MotorEx[] {frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
        }


        @Override
        public void onStartButtonPressed() {
            driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1());
            driverControlled.invoke();

            gamepadManager.getGamepad1().getRightBumper().setPressedCommand(
                    Extendo.INSTANCE::toggleExtendo
            );

            gamepadManager.getGamepad1().getRightTrigger().setPressedCommand(
                    value -> Intake.INSTANCE.toggleIntake()
            );

            gamepadManager.getGamepad1().getA().setPressedCommand(() -> {
                boolean goingToClose = !Claw.INSTANCE.clawSwitch;

                if (goingToClose) {
                    return new SequentialGroup(
                            Claw.INSTANCE.toggleClaw(),
                            new Delay(0.25),
                            new ParallelGroup(
                                    Deposit.INSTANCE.toggleDeposit(),
                                    Wrist.INSTANCE.toggleWrist()
                            )
                    );
                } else {
                    return new SequentialGroup(
                            new ParallelGroup(
                                    Deposit.INSTANCE.toggleDeposit(),
                                    Wrist.INSTANCE.toggleWrist()
                            ),
                            new Delay(1.0),
                            Claw.INSTANCE.toggleClaw()
                    );
                }
            });


            gamepadManager.getGamepad1().getX().setPressedCommand(
                    () -> new ParallelGroup(
                            Deposit.INSTANCE.specimenWall(),
                            Wrist.INSTANCE.specimenWall()
                    )
            );


            gamepadManager.getGamepad1().getDpadUp().setPressedCommand(IntakePivot.INSTANCE::decrementalPos);
            gamepadManager.getGamepad1().getDpadDown().setPressedCommand(IntakePivot.INSTANCE::incrementalPos);


            gamepadManager.getGamepad1().getLeftTrigger().setHeldCommand(value -> Intake.INSTANCE.Outtake());
            gamepadManager.getGamepad1().getLeftTrigger().setReleasedCommand(value -> Intake.INSTANCE.noPower());








        }
    }

