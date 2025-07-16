package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

@Config
@TeleOp
public class ExtendoPID extends NextFTCOpMode {

    public static double f = 0.0;
    public static double p = 0.0;
    public static double d = 0.0;
    public static double i = 0.0;
    public static int target = 0;

    public static double ticksPerRevolution = 384.5;
    public static double gearRatio = 1;
    public MotorEx extendoMotor;
    public String extendoMotorName = "Extendo";
    private double calculateFeedforward() {
        return Math.cos(Math.toRadians(target / ((ticksPerRevolution * gearRatio)/360))) * f;
    }
    public PIDFController controller = new PIDFController(p, i, d, v -> calculateFeedforward(), 15);
    @Override
    public void onInit() {
        extendoMotor = new MotorEx(extendoMotorName);
        extendoMotor.resetEncoder();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        target = (int)extendoMotor.getCurrentPosition();

    }

    @Override
    public void onUpdate() {
        controller.setKP(p);
        controller.setKI(i);
        controller.setKD(d);
        extendoMotor.setPower(controller.calculate(extendoMotor.getCurrentPosition(), target));

        telemetry.addData("Current Position", "%.1f ticks", extendoMotor.getCurrentPosition());
        telemetry.addData("Target Position", "%d ticks", target);
        telemetry.addData("Motor Power", "%.3f", extendoMotor.getPower());
        telemetry.update();
    }


}