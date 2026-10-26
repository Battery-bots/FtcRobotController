package org.firstinspires.ftc.teamcode.mechanisms;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
 * Made to get used to creating a class for motors
 * for harder motor configurations such as the launcher
 * This is not very efficient, just for practice
 */
public class Intake {

    private DcMotor intakeMotor;
    double max = 0.8;

    // initializes the hardware motor
    public void init(HardwareMap hwMap){

        intakeMotor = hwMap.get(DcMotor.class, "intake_motor");
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //ensures the motor brakes when stopped

    }

    // If intake is on, sets motor to full power
    public void intakeOn(){

        intakeMotor.setPower(max);

    }

    // If intake is off, sets immedietaly stops the motor
    public void intakeOff(){

        intakeMotor.setPower(0);

    }

    public void reverse(){
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setPower(.6);
    }
    public void forward(){
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setPower(max);
    }
}
