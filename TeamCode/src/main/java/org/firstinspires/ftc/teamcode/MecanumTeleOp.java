package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;

/*
 * Original Mecanum drive program
 * Robot oriented drive
 * No other classes used
 * Outdated, but should be tested and presented as an example
 */
@TeleOp (name = "Test TeleOp", group = "Testing")
public class MecanumTeleOp extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    Intake intake = new Intake();
    double forward;
    double strafe;
    double turn;
    DcMotor intakeMotor;
    boolean intakeOn = false;
    boolean lastA = false;

    @Override
    public void init(){
        telemetry.addData("Controller 1", "Press A + Start");

        drive.init(hardwareMap);
        intake.init(hardwareMap);
    }

    @Override
    public void loop(){
        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        // boolean value checks if intake is on or off
        forward = gamepad1.left_stick_y;
        strafe = -gamepad1.left_stick_x;
        turn = -gamepad1.right_stick_x;
        drive.drive(forward, strafe, turn);



        if (gamepad1.a && !lastA){
            intakeOn = !intakeOn;
        }
        lastA = gamepad1.a;

        if (intakeOn) {
            intake.intakeOn();
            if (gamepad1.left_bumper){
                intake.reverse();
            }
            else{
                intake.forward();
            }
        }
        else {
            intake.intakeOff();
        }

    }
}
