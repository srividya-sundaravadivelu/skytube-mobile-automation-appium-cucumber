# Mobile Automation Framework (Appium + Cucumber + JUnit + Java)

This repository contains automated test scripts for the SkyTube Android application using Appium, Cucumber (BDD), and JUnit.

The framework is designed to support behavior-driven testing with feature files describing the scenarios in plain English (Gherkin), while the underlying implementation is in Java.

## Application Under Test

SkyTube is an open-source YouTube player for Android that lets you watch videos ad-free, without sign-in, tracking, or restrictions. It supports high-quality playback, background play, and offers extra features.

## Tech Stack

Java – Programming language

Appium – Mobile automation framework

JUnit – Test runner and assertion library

Cucumber – BDD framework with Gherkin syntax

Maven – Build & dependency management tool

## Prerequisites

Install Java 11+

Install Maven (3.6+)

Install Node.js & Appium

npm install -g appium

Install Android Studio & Emulator

Make sure the Android Emulator is running before executing tests

## Key Features

### Cross-Platform Testing :
Supports both Android & iOS mobile applications

### Remote execution :
Test execution in local emulators as well as cloud device farms like Sauce Labs.
 
### Page Object Model (POM) :
Page Objects use @AndroidFindBy and @iosXCUITFindBy to support cross-platform locators.

### BDD with Cucumber :
Readable Gherkin scenarios that bridge communication between QA, Dev, BA

### Configuration Managemnent:
Separate config files for Android, ios and Common Settings improves flexibility and maintainability.

### Reporting & Logging:
Reporting using Reporter Plugin which gives screenshorts for every step/click.
Logs captured using Log4j ensuring visibility of execution flow.

### Custom wait strategies for dynamic mobile elements:
Automatically manages all element waits with the use of Wait Plugin

### CI/CD Integration :
Plugged into Jenkins using a Jenkinsfile for continuous testing

## Framework Design and Execution Flow

<img width="1344" height="888" alt="image" src="https://github.com/user-attachments/assets/6921b653-931b-41b4-b3da-da7e06d11a1e" />

## Reports

<img width="1919" height="971" alt="image" src="https://github.com/user-attachments/assets/ed156a12-f8ab-4320-b288-a7b7a8fff124" />

<img width="1919" height="978" alt="image" src="https://github.com/user-attachments/assets/cfe4c7d0-599e-475b-b0d2-c763623288a9" />

## Reference / Credits

This framework was adapted from: 
**`appium-cucumber-junit5-mobile-automation-framework`** by *the-sdet*  
Source: [GitHub → the-sdet/appium-cucumber-junit5-mobile-automation-framework](https://github.com/the-sdet/appium-cucumber-junit5-mobile-automation-framework)
