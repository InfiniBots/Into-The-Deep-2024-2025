package org.firstinspires.ftc.teamcode.Subsystems;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.core.command.utility.conditionals.PassiveConditionalCommand;
import com.rowanmcalpin.nextftc.core.command.utility.statemachine.AdvancingCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

public class Intake extends Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() { }
    public MotorEx Intake;
    public String intakeName = "Intake";
    public Command intake(){
        return new SetPower(Intake,
                1,
                this);
    }
    public Command slightPower(){
        return new SetPower(Intake,
                0.2,
                this);
    }
    public Command noPower(){
        return new SetPower(Intake,
                0,
                this);
    }
    public Command Outtake(){
        return new SetPower(Intake,
                -1,
                this);
    }

    public boolean intakeSwitch = true;
    public Command toggleIntake() {
        return new SequentialGroup(
                new InstantCommand(() -> {
                    intakeSwitch = !intakeSwitch;
                }),
                new PassiveConditionalCommand(
                        () -> intakeSwitch,
                        this::intake,
                        this::slightPower
                )
        );
    }
    @Override
    public void initialize() {
        Intake = new MotorEx(intakeName).reverse();
    }
}
