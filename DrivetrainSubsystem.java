// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DrivetrainSubsystem extends SubsystemBase {
 
   SparkMax leftFrontMotor= new SparkMax(DriveConstants.kLeftFrontMotorCANID,MotorType.kBrushless);
   SparkMax leftRearMotor= new SparkMax (DriveConstants.kLeftRearMotorCANID,MotorType.kBrushless);
   SparkMax rightFrontMotor= new SparkMax (DriveConstants.kRightFrontMotorCANID,MotorType.kBrushless);
   SparkMax rightRearMotor = new SparkMax(DriveConstants.kRightRearMotorCANID, MotorType.kBrushless);
  
   RelativeEncoder leftEncoder = leftFrontMotor.getEncoder();
  RelativeEncoder righEncoder = rightFrontMotor.getEncoder();

  MotorControllerGroup leftControllerGroup = new MotorControllerGroup(leftFrontMotor, leftRearMotor);
  MotorControllerGroup rightControllerGroup = new MotorControllerGroup(rightFrontMotor, rightRearMotor);

  DifferentialDrive differentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

  /** Creates a new ExampleSubsystem. */
  public DrivetrainSubsystem() {
    leftFrontMotor.restoreFactoryDefaults();
    leftRearMotor.restoreFactoryDefaults();
    rightFrontMotor.restoreFactoryDefaults();
    rightRearMotor.restoreFactoryDefaults();

    leftEncoder.setPosition(0);
    righEncoder.setPosition(0);

    leftRearMotor.follow(leftFrontMotor);
    rightRearMotor.follow(rightFrontMotor);

    rightControllerGroup.setInverted(true);
    leftControllerGroup.setInverted(false);
  }

  public void arcadeDrive(double fwd, double rot) {
    differentialDrive.arcadeDrive(fwd, rot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}