package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 14.193)
                .setDimensions(17.193, 16.818)
                .build();

        /*myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-9.5, 61.6, Math.toRadians(90)))
                .strafeTo(new Vector2d(-9.5, 36))
                .strafeTo(new Vector2d(-36, 36))
                .strafeTo(new Vector2d(-36, 12))
                .strafeTo(new Vector2d(-46, 12))
                .strafeTo(new Vector2d(-48, 52.5))
                .strafeToSplineHeading(new Vector2d(-38, 48), Math.toRadians(300))
                .strafeToSplineHeading(new Vector2d(-9.5, 36), Math.toRadians(90))
                .strafeToSplineHeading(new Vector2d(-7, 43), Math.toRadians(90))
                .strafeToSplineHeading(new Vector2d(-38, 48), Math.toRadians(300))
                .strafeToSplineHeading(new Vector2d(-9.5, 36), Math.toRadians(90))
                .build());*/


         myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(9.5, 60.6, Math.toRadians(90)))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(50, 42.5))
                .waitSeconds(1)
                .strafeToSplineHeading(new Vector2d(55, 55), Math.toRadians(205))
                .waitSeconds(0.5)
                .strafeToSplineHeading(new Vector2d(60, 40.5), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToSplineHeading(new Vector2d(55, 55), Math.toRadians(205))
                .waitSeconds(0.5)
                .strafeToSplineHeading(new Vector2d(43, 21.5), Math.toRadians(180))
                .waitSeconds(0.5)
                .strafeToSplineHeading(new Vector2d(55, 55), Math.toRadians(205))
                .waitSeconds(0.5)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}