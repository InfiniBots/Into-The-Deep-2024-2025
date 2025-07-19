package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Subsystems.Extendo;

@Config
@TeleOp
public class ExtendoTesting extends OpMode  {
    public DcMotorEx Extendo;
    @Override
    public void init() {
        Extendo = hardwareMap.get(DcMotorEx.class, "Extendo");
        Extendo.setDirection(DcMotorSimple.Direction.REVERSE);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

    @Override
    public void loop() {
        Extendo.setPower(gamepad1.left_stick_y);
        //Detract 1050 -> 1115
        //Extend -5 -> 5

        telemetry.addData("ExtendoPower", Extendo.getPower());
        telemetry.addData("Extendopos", Extendo.getCurrentPosition());
        telemetry.update();


    }
}
