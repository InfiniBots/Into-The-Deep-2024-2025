package org.firstinspires.ftc.teamcode.limelight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import java.util.List;

public class LimelightSampleTracking {
    public Limelight3A limelight;
    public LLResultTypes.DetectorResult lockedSample = null;
    public Telemetry telemetry;
    public double limelightMountAngleDegrees = 65.0;
    public double limelightLensHeightInches = 10.0;
    public double sampleHeightInches = 1.5;
    public double threshold = 2;

    public LimelightSampleTracking(HardwareMap hardwareMap, Telemetry telemetry) {
        this.limelight = hardwareMap.get(Limelight3A.class, "limelight");
        this.telemetry = telemetry;
    }

    public LLResultTypes.DetectorResult getClosestYellowSample() {
        LLResult result = limelight.getLatestResult();
        if (result == null || result.getDetectorResults().isEmpty()) {
            lockedSample = null;
            return null;
        }

        List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
        LLResultTypes.DetectorResult closestSample = null;
        double minDistance = Double.MAX_VALUE;

        for (LLResultTypes.DetectorResult detection : detectorResults) {
            if (detection.getClassName().equalsIgnoreCase("yellow")) {
                double tx = detection.getTargetXDegrees();
                double ty = detection.getTargetYDegrees();

                double angleToSampleRadians = Math.toRadians(limelightMountAngleDegrees + ty);
                double distance = (limelightLensHeightInches - sampleHeightInches) / Math.tan(angleToSampleRadians);

                if (lockedSample == null) {
                    lockedSample = detection;
                    minDistance = distance;
                    closestSample = detection;
                } else if (distance < minDistance - threshold) {
                    minDistance = distance;
                    closestSample = detection;
                }
            }
        }

        if (closestSample != null) {
            lockedSample = closestSample;
        }

        return lockedSample;
    }

    public void trackClosestYellowSample() {
        LLResultTypes.DetectorResult target = getClosestYellowSample();

        if (target != null) {
            double angleToSampleRadians = Math.toRadians(limelightMountAngleDegrees + target.getTargetYDegrees());
            double distance = (limelightLensHeightInches - sampleHeightInches) / Math.tan(angleToSampleRadians);

            telemetry.addData("Locked Sample", "X:  Y: , Distance: ",
                    target.getTargetXDegrees(), target.getTargetYDegrees(), distance);
        } else {
            telemetry.addData("No Yellow Sample found", "No Sample");
        }
        telemetry.update();
    }
}