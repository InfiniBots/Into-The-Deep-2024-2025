package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import java.util.List;

public class IntakePivot extends Subsystem {
    public static final IntakePivot INSTANCE = new IntakePivot();
    private IntakePivot() { }
    public Servo rightIntake;
    public Servo leftIntake;
    public String rightIntakeName = "rightIntakeServo";
    public String leftIntakeName = "leftIntakeServo";


    public double getPosition() {
        return rightIntake.getPosition();
    }

    public Command incrementalPos() {
        return new InstantCommand(() -> {
            double currentPos = rightIntake.getPosition();
            double newPos = Math.min(1.0, currentPos + 0.1);
            rightIntake.setPosition(newPos);
        });
    }

    public Command intakeZeroPos() {
        return new ServoToPosition(
           rightIntake,
            0,
            this);
    }


    public Command decrementalPos() {
        return new InstantCommand(() -> {
            double currentPos = rightIntake.getPosition();
            double newPos = Math.min(1.0, currentPos - 0.1);
            rightIntake.setPosition(newPos);
        });
    }


    /* public boolean intakePivotSwitch = true;
    public Command toggleIntakePivot() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    intakePivotSwitch = !intakePivotSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> intakePivotSwitch,
                        this::submersiblePosition,
                        this::transferPosition
                )
        );
    } */

    @Override
    public void initialize()  {
        rightIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightIntakeName);
        leftIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftIntakeName);
        leftIntake.setDirection(Servo.Direction.REVERSE);
        rightIntake.setPosition(0);
    }

}
