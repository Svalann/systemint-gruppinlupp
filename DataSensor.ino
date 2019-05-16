#include <ESP8266WiFi.h>
#include <WiFiClientSecure.h>
#include <WiFiUdp.h>

#include <AzureIoTHub.h>
#include <AzureIoTProtocol_MQTT.h>
#include <AzureIoTUtility.h>

#include "config.h"

static IOTHUB_CLIENT_LL_HANDLE iotHubClientHandle;

void setup()
{
	initPins();
	initSerial();	
	initWiFi();
	initTime();
	initSensor();
	initServo();
	initIot();
	SetupComplete();
}

void loop()
{
	UpdateCurrentTime();
	MeasureTemperature();
	//if (ButtonPushed()) TriggerButton();

	if (SendMessageApproved())
	{
		char messagePayload[MESSAGE_MAX_LEN];
		prepareMessage(temperature, messagePayload);
		sendMessage(iotHubClientHandle, messagePayload);
		nextSendTime = currentTime + sendInterval;
	}

	IoTHubClient_LL_DoWork(iotHubClientHandle);
	blinkLeds_OFF(currentTime);
	delay(20);
}