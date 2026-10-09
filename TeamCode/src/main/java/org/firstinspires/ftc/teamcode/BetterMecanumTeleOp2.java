package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDriveTest;

/*
 * Will be deleted after tested
 */
@TeleOp (name="Test 2 TeleOp", group="test")
public class BetterMecanumTeleOp2 extends OpMode {
    // calls drive object from MecanumDrive class
    MecanumDriveTest drive = new MecanumDriveTest();
    // calls intake object from Intake class
    Intake intake = new Intake();

    double forward;
    double strafe;
    double rotate;
    boolean intakeOn = false;
    boolean lastA = false;

    @Override
    public void init(){
        telemetry.addData("Controller 1", "Press A + Start to initialize!");

        //initializes all motors to the current hardware map
        drive.init(hardwareMap);
        intake.init(hardwareMap);
    }

    @Override
    public void loop(){
        // variables for wheels
        forward = gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        // if statement that toggles intake if A is pressed, NOT held
        if (gamepad1.a && !lastA) {
            intakeOn = !intakeOn;
        }

        // manually resets orientation if drifting
        if (gamepad1.b) {

            drive.resetOrientation();

        }

        // Toggles intake
        if (intakeOn) {
            intake.intakeOn();
        }
        else {
            intake.intakeOff();
        }

        lastA = gamepad1.a; // variable to track if A button is being held

        // function for mecanum drive
        drive.driveFieldRelative(forward, strafe, rotate);

        telemetry.addData("Intake On", intakeOn);
        telemetry.addData("Angle", drive.returnAngle());
        telemetry.update();
    }
}