package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
 * Made to get used to creating a class for motors
 * for harder motor configurations such as the launcher
 * This is not very efficient, just for practice
 */
public class Intake {

    private DcMotor intakeMotor;

    // initializes the hardware motor
    public void init(HardwareMap hwMap){

        intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    // If intake is on, sets motor to full power
    public void intakeOn(){

        double max = 1.0;
        intakeMotor.setPower(max);

    }

    // If intake is off, sets immedietaly stops the motor
    public void intakeOff(){

        intakeMotor.setPower(0);

    }
}
