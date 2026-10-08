package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/*
 * Class for mecanum drive
 * experimenting with Field Relative Drive for ease of steering and aiming
 * Code taken from:https://youtu.be/sFCO4du5IZk?si=7h63JWqiSaWLh6x7
 */
public class MecanumDrive {

    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private IMU imu; //built in gyro sensor in ftc

    public void init(HardwareMap hwMap){
        // initializes all motors to their hardware map positions
        frontLeft = hwMap.get(DcMotor.class, "front_left_motor");
        backLeft = hwMap.get(DcMotor.class, "back_left_motor");
        frontRight = hwMap.get(DcMotor.class, "front_right_motor");
        backRight = hwMap.get(DcMotor.class, "back_right_motor");

        // reverses direction of left motors bc they're on backwards
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Runs motors using encoders
        // used to make movement more accurate (usually for auto, but idk if it would be useful in
        // teleop
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hwMap.get(IMU.class, "imu");

        // sets orientation of robot
        // Based on control hub, the logo of the hub is up and USB is facing forward
        // edit if orientated any other way
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));
    }

    public void drive(double forward, double strafe, double rotate){
        double frontLeftPower = forward + strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backRightPower = forward + strafe - rotate;
        double backLeftPower = forward - strafe + rotate;
        double maxPower = 1.0;

        // loops through power to find greatest magnitude of power
        maxPower = Math.max(maxPower, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));



        frontLeft.setPower(frontLeftPower/maxPower);
        backLeft.setPower(backLeftPower/maxPower);
        frontRight.setPower(frontRightPower/maxPower);
        backRight.setPower(backRightPower/maxPower);

    }

    // Field relative code
    public void driveFieldRelative(double forward, double strafe, double rotate){
        // obtains angle and distance you want the robot to move
        double theta = Math.atan2(forward, strafe);
        double r = Math.hypot(strafe, forward);

        // obtains robot current angle
        // and rotates based on where robot is currently facing
        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        // calculates new forward motion based on angle
        double newForward = r * Math.sin(theta);
        // calculates new strafe based on angle
        double newStrafe = r * Math.cos(theta);

        // calls drive function to move motors based on newly calculated forward, strafe variables
        this.drive(newForward, newStrafe, rotate);
    }
}
