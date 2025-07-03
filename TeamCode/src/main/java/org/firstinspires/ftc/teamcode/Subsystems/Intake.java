package org.firstinspires.ftc.teamcode.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

public class Intake extends Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }
    public MotorEx Intake;
    public String intakeName = "ExpMotor3";
    public Command intake(){
        return new SetPower(Intake,
                1,
                this);
    }
    public Command release(){
        return new SetPower(Intake,
                -1,
                this);
    }
    @Override
    public void initialize() {
        Intake = new MotorEx(intakeName);
    }
}
