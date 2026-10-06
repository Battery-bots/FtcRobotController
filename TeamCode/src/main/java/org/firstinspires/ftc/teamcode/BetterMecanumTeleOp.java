package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

/*
 * Experiment for Mecanum drive
 * The program will allow the robot to drive field relative in order to aim and drive
 * the robot with much more accuracy.
 * Made outside the club probably
 * Maybe
 */
@TeleOp (name="Main TeleOp", group="buzz")
public class BetterMecanumTeleOp extends OpMode {
    // calls drive object from MecanumDrive class
    MecanumDrive drive = new MecanumDrive();
    // calls intake object from Intake class
    Intake intake = new Intake();

    double forward;
    double strafe;
    double rotate;
    boolean intakeOn = false;
    boolean lastA = false;

    public void init(){
        telemetry.addData("Controller 1", "Press A + Start to initialize!");

        //initializes all motors to the current hardware map
        drive.init(hardwareMap);
        intake.init(hardwareMap);
    }

    public void loop(){
        // variables for wheels
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        // function for mecanum drive
        drive.driveFieldRelative(forward, strafe, rotate);

        // if statement that toggles intake if A is pressed, NOT held
        if (gamepad1.a && !lastA) {
            intakeOn = !intakeOn;
        }

        // Toggles intake
        if (intakeOn){
            intake.intakeOn();
        }
        else {
            intake.intakeOff();
        }

        lastA = gamepad1.a; // variable to track if A button is being held

    }
}