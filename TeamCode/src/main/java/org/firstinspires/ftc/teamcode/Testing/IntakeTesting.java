package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class IntakeTesting extends OpMode  {
    public DcMotorEx Intake;

    @Override
    public void init() {
        Intake  = hardwareMap.get(DcMotorEx.class, "Intake");
    }

    @Override
    public void loop() {
        Intake.setPower(gamepad1.right_trigger); //intake sample in
        Intake.setPower(gamepad1.left_trigger/-1); //intake sample out

    }
}
