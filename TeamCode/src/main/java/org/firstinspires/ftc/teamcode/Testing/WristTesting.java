package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Wrist;

@Config
@Autonomous
public class WristTesting extends OpMode {
    Servo Wrist;
    public static double transferPos = 0.0;

    @Override
    public void init() {
        Wrist = hardwareMap.get(Servo.class, "Wrist");
    }
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    @Override
    public void loop() {
        Wrist.setPosition(transferPos);

    }
}
