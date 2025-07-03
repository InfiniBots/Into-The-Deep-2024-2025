package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.ResetEncoder;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

@Config
public class Extendo extends Subsystem {
    public static final Extendo INSTANCE = new Extendo();
    private Extendo() { }
    public MotorEx Extendo;
    public static int tolerance = 10;
    public PIDFController controller = new PIDFController(0.005, 0.0, 0.0, new StaticFeedforward(0.0), 10);
    public String extendoName = "ExpMotor2";

    public Command Extend() {
        return new RunToPosition(Extendo,
                2000.0,
                controller,
                this);
    }

    public Command Retract() {
        return new RunToPosition(Extendo,
                0.0,
                controller,
                this);
        new ResetEncoder(Extendo, this);
    }

    public Command extendoManualExtend()  {
        return new SetPower(Extendo,
                0.2,
                this);
    }

    public Command extendoManualRetract()  {
        return new SetPower(Extendo,
                -0.2,
                this);
    }




    @Override
    public Command getDefaultCommand() {
        return new HoldPosition(Extendo, controller, this);
    }

    @Override
    public void initialize()  {
        Extendo = new MotorEx(extendoName);

    }

    public void periodic()  {
        OpModeData.telemetry.addData("Lift Position", Extendo.getCurrentPosition());
    }






}
