package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Claw extends Subsystem {
    public static final Claw INSTANCE = new Claw();
    private Claw() { }
    public Servo Claw;
    public String clawName = "Claw";

    public Command Close() {
        return new ServoToPosition(Claw,
                0.4,
                this);
    }

    public Command Open() {
        return new ServoToPosition(Claw,
                0.85,
                this);
    }

    public boolean clawSwitch = true;
    public Command toggleClaw() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    clawSwitch = !clawSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> clawSwitch,
                        this::Close,
                        this::Open
                )
        );
    }


    @Override
    public void initialize()  {
        Claw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, clawName);
    }

}
