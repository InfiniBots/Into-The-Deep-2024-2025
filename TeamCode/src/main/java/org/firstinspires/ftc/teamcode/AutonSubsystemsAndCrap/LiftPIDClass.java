package org.firstinspires.ftc.teamcode.AutonSubsystemsAndCrap;

import com.arcrobotics.ftclib.controller.PIDController;

public class LiftPIDClass {

    private static PIDController controller;

    public static double p = 0.0, i = 0.0, d = 0.0;
    public static double f = 0.0;
    public static final double ticksPerRevolution = 145.1;
    public static final double gearRatio = 0.8;
    static double power;

    public static double returnLiftPID(double target, double liftPos) {

        controller = new PIDController(p, i, d);

        controller.setPID(p, i, d);

        double pid = controller.calculate(liftPos, target);

        double ff = Math.cos(Math.toRadians(target / ((ticksPerRevolution * gearRatio)/360))) * f ;

        power = pid + ff;
        return power;

    }
}
