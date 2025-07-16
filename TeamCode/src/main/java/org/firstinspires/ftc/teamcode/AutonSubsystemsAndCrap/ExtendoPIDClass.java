package org.firstinspires.ftc.teamcode.AutonSubsystemsAndCrap;

import com.arcrobotics.ftclib.controller.PIDController;

public class ExtendoPIDClass {

    private static PIDController controller;

    public static double p = 0.0, i = 0.0, d = 0.0;
    public static double f = 0.0;
    public static final double ticksPerRevolution = 384.5;

    static double power;

    public static double returnExtendoPID(double target, double extendoPos) {

        controller = new PIDController(p, i, d);

        controller.setPID(p, i, d);

        double pid = controller.calculate(extendoPos, target);

        double ff = Math.cos(Math.toRadians(target / (ticksPerRevolution / 360))) * f;

        power = pid + ff;
        return power;

    }
}
