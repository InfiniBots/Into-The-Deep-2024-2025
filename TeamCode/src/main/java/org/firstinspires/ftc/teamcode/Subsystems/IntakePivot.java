package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;
import java.util.List;

public class IntakePivot extends Subsystem {
    public static final IntakePivot INSTANCE = new IntakePivot();
    private IntakePivot() { }
    public Servo rightIntake;
    public Servo leftIntake;
    public String rightIntakeName = "Servo6";
    public String leftIntakeName = "Servo5";

    public Command transferPosition() {
        return new MultipleServosToPosition(
                List.of(rightIntake, leftIntake),
                0.5,
                this);
    }

    public Command Submersible() {
        return new MultipleServosToPosition(
                List.of(rightIntake, leftIntake),
                -0.5,
                this);
    }

    public Command staticPosition() {
        return new MultipleServosToPosition(
                List.of(rightIntake, leftIntake),
                0,
                this);
    }

    @Override
    public void initialize()  {
        rightIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightIntakeName);
        leftIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftIntakeName);
        leftIntake.setDirection(Servo.Direction.REVERSE);
    }

}
