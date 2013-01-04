Arduino-Communicator-Client
===========================

Simple example demostrating how to use Arduino Uno Communicator app.

Please download the Arduino Uno Communicator app here: https://play.google.com/store/apps/details?id=com.primavera.arduino.listener

The source code of Arduino Uno Communicator app can be find here: https://github.com/jeppsson/Arduino-Communicator

This app requires the Arduino Uno Communicator app. Without the Arduino Uno Communicator app this app is useless.

This app broadcats "primavera.arduino.intent.action.SEND_DATA" intent with a predefined text. If you have the Arduino Uno Communicator app installed and your Arduino connected, the text should be sent to your Arduino.

This app also listens to "primavera.arduino.intent.action.DATA_RECEIVED" intents which the Arduino Uno Communicator app will broadcast when receiving data from the Arduino.
