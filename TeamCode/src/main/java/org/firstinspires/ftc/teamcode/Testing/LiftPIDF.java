package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;

@TeleOp
@Config
public class LiftPIDF extends NextFTCOpMode {

    public static double f = 0.0;
    public static double p = 0.0;
    public static double d = 0.0;
    public static double i = 0.0;
    public static int target = 0;

    public static double ticksPerRevolution = 145.1;
    public static double gearRatio = 0.8;
    public MotorEx rightLift;
    public MotorEx leftLift;
    public MotorGroup liftMotors;
    public String rightLiftName = "ExpMotor0";
    public String leftLiftName = "ExpMotor1";
    private double calculateFeedforward() {
        return Math.cos(Math.toRadians(target / ((ticksPerRevolution * gearRatio)/360)) * f );
    }
    public PIDFController controller = new PIDFController(p, i, d, v -> calculateFeedforward(), 15);
    @Override
    public void onInit() {
        rightLift = new MotorEx(rightLiftName);
        leftLift = new MotorEx(leftLiftName).reverse();
        rightLift.resetEncoder();
        leftLift.resetEncoder();
        liftMotors = new MotorGroup(rightLift, leftLift);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        target = (int)rightLift.getCurrentPosition();

    }

    @Override
    public void onUpdate() {
        controller.setKP(p);
        controller.setKI(i);
        controller.setKD(d);
        liftMotors.setPower(controller.calculate(rightLift.getCurrentPosition(), target));

        telemetry.addData("Current Position", "%.1f ticks", rightLift.getCurrentPosition());
        telemetry.addData("Target Position", "%d ticks", target);
        telemetry.addData("Motor Power", "%.3f", rightLift.getPower());
        telemetry.update();
    }


}