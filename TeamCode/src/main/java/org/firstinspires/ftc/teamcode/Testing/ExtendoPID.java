package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

@Config
@TeleOp
public class ExtendoPID extends OpMode {

    private PIDController controller;
    public static double f = 0.0;
    public static double p = 0.003;
    public static double d = 0.0;
    public static double i = 0.0;
    public static int target = 0;
    public static double ticksPerRevolution = 384.5;
    public static double gearRatio = 1;
    private DcMotorEx Extendo;
    private Servo rightIntake;

    @Override
    public void init() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        Extendo = hardwareMap.get(DcMotorEx.class, "Extendo");
        Extendo.setDirection(DcMotorSimple.Direction.REVERSE);
        rightIntake = hardwareMap.get(Servo.class, "rightIntakeServo");
        Extendo.setTargetPosition(0);
        rightIntake.setPosition(0);

    }

    @Override
    public void loop() {
        controller.setPID(p, i, d);
        int extendoPos = Extendo.getCurrentPosition();
        double pid = controller.calculate(extendoPos, target);
        double ff = Math.cos(Math.toRadians(target / ((ticksPerRevolution * gearRatio)/360))) * f ;

        double power = pid + ff;
        Extendo.setPower(power);

        telemetry.addData("pos", extendoPos);
        telemetry.addData("target", target);
        telemetry.update();

    }
}