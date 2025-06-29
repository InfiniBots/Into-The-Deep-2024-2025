package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TransferPractice extends OpMode {

    public enum IntakeState {
        intakeExtend,
        intakeDown,
        intakeRun,
        intakeDetract,
        intakeUp,

    }

    IntakeState intakeState = IntakeState.intakeExtend;
    

    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}
