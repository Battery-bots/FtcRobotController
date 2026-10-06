package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumTeleOp extends OpMode {
    DcMotor frontLeftDrive;
    DcMotor frontRightDrive;
    DcMotor backLeftDrive;
    DcMotor backRightDrive;
    DcMotor intakeMotor;
    boolean intakeOn = false;
    boolean lastA = false;

    @Override
    public void init(){
        telemetry.addData("Controller 1", "Press A + Start");

        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        intakeMotor = hardwareMap.get(DcMotor.class, "intake_motor");

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop(){
        // Mecanum drive is controlled with three axes: drive (front-and-back),
        // strafe (left-and-right), and twist (rotating the whole chassis).
        // boolean value checks if intake is on or off
        double drive = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = gamepad1.right_stick_x;


        // You may need to multiply some of these by -1 to invert direction of
        // the motor.  This is not an issue with the calculations themselves.
        double[] power = {
                (drive + strafe + turn),
                (drive - strafe - turn),
                (drive - strafe + turn),
                (drive + strafe - turn)
        };

        // Loop through all values in the speeds[] array and find the greatest
        // *magnitude*.  Not the greatest velocity.
        double max = Math.abs(power[0]);
        for (int i = 0; i < power.length; i++) {

            if ( max < Math.abs(power[i]) )
                max = Math.abs(power[i]);

        }

        // If and only if the maximum is outside the range we want it to be,
        // normalize all the other speeds based on the given speed value.
        if (max > 1) {

            for (int i = 0; i < power.length; i++)
                power[i] /= max;

        }

        // apply the calculated values to the motors.
        frontLeftDrive.setPower(power[0]);
        frontRightDrive.setPower(power[1]);
        backLeftDrive.setPower(power[2]);
        backRightDrive.setPower(power[3]);

        if (gamepad1.a && !lastA){
            intakeOn = !intakeOn;
        }
        lastA = gamepad1.a;

        if (intakeOn){
            intakeMotor.setPower(1.0);
        }
        else {
            intakeMotor.setPower(0);
            intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
}
