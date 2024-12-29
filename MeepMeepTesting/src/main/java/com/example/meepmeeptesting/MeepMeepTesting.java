package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
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
                .setConstraints(50, 50, Math.toRadians(180), Math.toRadians(180), 15)
                .setDimensions(17.193, 16.818)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(9.5, 61.6, Math.toRadians(90)))
                .strafeTo(new Vector2d(9.5, 37.25))
                .strafeTo(new Vector2d(50.75, 44.5))
                .waitSeconds(3)


                .strafeToSplineHeading(new Vector2d(55, 55), Math.toRadians(225))
                .waitSeconds(3)

                .strafeToSplineHeading(new Vector2d(58.25, 43.75), Math.toRadians(90))
                .waitSeconds(3)

                .turn(Math.toRadians(135))
                .strafeTo(new Vector2d(55, 55))
                .waitSeconds(3)

                .turn(Math.toRadians(-73))
                .strafeTo(new Vector2d(48.5, 37))
                .waitSeconds(3)

                .turn(Math.toRadians(-73))
                .strafeTo(new Vector2d(55, 55))
                .waitSeconds(3)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}