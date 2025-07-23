package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Wrist extends Subsystem {
    public static final Wrist INSTANCE = new Wrist();
    private Wrist() { }
    public Servo Wrist;
    public String wristName = "Wrist";

    public Command Transfer() {
        return new ServoToPosition(Wrist,
                0.1,
                this);
    }

    public Command specimenWall() {
        return new ServoToPosition(Wrist,
                0.465,
                this);
    }

    public Command specimenChamberPrepare() {
        return new ServoToPosition(Wrist,
                0,
                this);
    }

    public Command specimenChamber() {
        return new ServoToPosition(Wrist,
                0.8,
                this);
    }

    public Command highBucket() {
        return new ServoToPosition(Wrist,
                0.2,
                this);
    }

    public boolean wristSwitch = true;
    public Command toggleWrist() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    wristSwitch = !wristSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> wristSwitch,
                        this::specimenChamberPrepare,
                        this::specimenChamber
                )
        );
    }



    @Override
    public void initialize()  {
        Wrist = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, wristName);
    }

}
