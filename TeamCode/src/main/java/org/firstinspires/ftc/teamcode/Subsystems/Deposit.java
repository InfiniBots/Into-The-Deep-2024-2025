package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;
import java.util.List;

public class Deposit extends Subsystem {
    public static final Deposit INSTANCE = new Deposit();
    private Deposit() { }
    public Servo rightServo;
    public Servo leftServo;
    public String rightServoName = "rightDeposit";
    public String leftServoName = "leftDeposit";

    public Command specimenWall() {
        return new MultipleServosToPosition(
                List.of(rightServo, leftServo),
                0.9,
                this);
    }

    public Command specimenChamberPrepare() {
        return new MultipleServosToPosition(
                List.of(rightServo, leftServo),
                0.3,
                this);
    }

    public Command specimenChamber() {
        return new MultipleServosToPosition(
                List.of(rightServo, leftServo),
                0.4,
                this);
    }

    public boolean depositSwitch = true;
    public Command toggleDeposit() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    depositSwitch = !depositSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> depositSwitch,
                        this::specimenChamberPrepare,
                        this::specimenChamber
                )
        );
    }

    @Override
    public void initialize()  {
        rightServo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightServoName);
        leftServo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftServoName);
        leftServo.setDirection(Servo.Direction.REVERSE);
    }

}
