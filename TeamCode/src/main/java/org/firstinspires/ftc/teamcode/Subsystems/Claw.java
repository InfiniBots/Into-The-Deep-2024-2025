package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Claw extends Subsystem {
    public static final Claw INSTANCE = new Claw();
    private Claw() { }
    public Servo Claw;
    public String clawName = "Servo4";

    public Command Close() {
        return new ServoToPosition(Claw,
                0.7,
                this);
    }

    public Command Open() {
        return new ServoToPosition(Claw,
                0.3,
                this);
    }


    @Override
    public void initialize()  {
        Claw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, clawName);
    }

}
