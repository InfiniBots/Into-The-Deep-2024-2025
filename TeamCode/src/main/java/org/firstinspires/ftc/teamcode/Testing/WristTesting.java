package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.Wrist;

@Config
@TeleOp(group = "Random Tests")
public class WristTesting extends OpMode {
    Servo Wrist;
    public static double wallPos = 0.0;
    public static double chamberPreparePos = 0.0;
    public static double chamberPos = 0.0;

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
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);

        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);

        if (currentGamepad1.a && !previousGamepad1.a) {
            Wrist.setPosition(wallPos);
        }

        if (currentGamepad1.b && !previousGamepad1.b) {
            Wrist.setPosition(chamberPreparePos);
        }
        if (currentGamepad1.y && !previousGamepad1.y) {
            Wrist.setPosition(chamberPos);
        }

    }
}
