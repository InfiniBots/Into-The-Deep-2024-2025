package org.firstinspires.ftc.teamcode.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.core.command.utility.statemachine.AdvancingCommand;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.HoldPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.ResetEncoder;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

public class Extendo extends Subsystem {
    public static final Extendo INSTANCE = new Extendo();
    private Extendo() { }
    public MotorEx Extendo;
    public static int tolerance = 10;
    public PIDFController controller = new PIDFController(0.009, 0.0, 0.0, new StaticFeedforward(0.0), 10);
    public String extendoName = "Extendo";

    public Command Extend() {
        return new RunToPosition(Extendo,
                1500,
                controller,
                this);
    }

    public Command Detract() {
        return new RunToPosition(Extendo,
                100,
                controller,
                this);
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

    public boolean extendoSwitch = true;
    public Command toggleExtendo() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    extendoSwitch = !extendoSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> extendoSwitch,
                        this::Extend,
                        this::Detract
                )
        );
    }


    @Override
    public Command getDefaultCommand() {
        return new HoldPosition(Extendo, controller, this);
    }

    @Override
    public void initialize()  {
        Extendo = new MotorEx(extendoName).reverse();
        Extendo.resetEncoder();

    }

    public void periodic()  {
        OpModeData.telemetry.addData("Lift Position", Extendo.getCurrentPosition());
    }






}
