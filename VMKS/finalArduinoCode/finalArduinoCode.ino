#define USE_ARDUINO_INTERRUPTS true    
#include <PulseSensorPlayground.h>   
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
//#include <ESP8266WiFi.h>
//#include <ThingerWifi.h>
Adafruit_SSD1306 display = Adafruit_SSD1306(128, 32, &Wire);

//  Variables pulse
const int PulseWire = 0;       
const int LED13 = 13;          
int Threshold = 550;

PulseSensorPlayground pulseSensor;

// Variables accelerometer
float xavg=0, yavg=0, zavg=0;
float xcur=0, ycur=0, zcur=0;
float xnxt=0, ynxt=0, znxt=0;
int steps = 0;


void setup() {
Serial.begin(9600);          
  // Configure the PulseSensor object, by assigning our variables to it.
  pulseSensor.analogInput(PulseWire);  
  pulseSensor.blinkOnPulse(LED13);       
  pulseSensor.setThreshold(Threshold);  
  // Double-check the "pulseSensor" object was created and "began" seeing a signal.
   if (pulseSensor.begin()) {
    Serial.println("We created a pulseSensor Object !");  //This prints one time at Arduino power-up,  or on Arduino reset. 
  }

  Serial.println("OLED intialized");
 	display.begin(SSD1306_SWITCHCAPVCC, 0x3C); // Address 0x3C for 128x32
 	display.display();
 	delay(1000);
 	// Clear the buffer.
 	display.clearDisplay();
 	display.display();
 	// text display tests
 	display.setTextSize(1);
 	display.setTextColor(WHITE);

  // accelerometer
  ssd1306_init();
  adxl345_init();
  read_av_acc();

}

void loop() {
int myBPM = pulseSensor.getBeatsPerMinute();   // "myBPM" hold this BPM value now.                      
 Serial.println(myBPM);     
  

  //
  for(int i = 0; i<50; i++){
    Wire.beginTransmission(0x53);
    Wire.write(0x32); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte x0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x33); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte x1 = Wire.read();
    x1 = x1 & 0x03;

    uint16_t x = (x1 << 8) + x0;
    int16_t xf = x;
    if(xf > 511){
    xf = xf - 1024;
    }
    float xa = xf * 0.004;
    xcur = xcur + xa;

    Wire.beginTransmission(0x53);
    Wire.write(0x34); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte y0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x35); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte y1 = Wire.read();
    y1 = y1 & 0x03;

    uint16_t y = (y1 << 8) + y0;
    int16_t yf = y;
    if(yf > 511){
    yf = yf - 1024;
    }
    float ya = yf * 0.004;
    ycur = ycur + ya;

    Wire.beginTransmission(0x53);
    Wire.write(0x36); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte z0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x37); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte z1 = Wire.read();
    z1 = z1 & 0x03;

    uint16_t z = (z1 << 8) + z0;
    int16_t zf = z;
    if(zf > 511){
    zf = zf - 1024;
    }
    float za = zf * 0.004;
    zcur = zcur + za;
  }
  xcur = xcur/50; 
  ycur = ycur/50;
  zcur = zcur/50;

  float acc = sqrt(((xcur - xavg) * (xcur - xavg)) + 
              ((ycur - yavg) * (ycur - yavg)) + 
              ((zcur - zavg) * (zcur - zavg)));
  delay(250);

    //Next Acceleration
    for(int i = 0; i<50; i++){
    Wire.beginTransmission(0x53);
    Wire.write(0x32); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte x0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x33); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte x1 = Wire.read();
    x1 = x1 & 0x03;

    uint16_t x = (x1 << 8) + x0;
    int16_t xf = x;
    if(xf > 511)
    {
    xf = xf - 1024;
    }
    float xa = xf * 0.004;
    xnxt = xnxt + xa;


    Wire.beginTransmission(0x53);
    Wire.write(0x34); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte y0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x35); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte y1 = Wire.read();
    y1 = y1 & 0x03;

    uint16_t y = (y1 << 8) + y0;
    int16_t yf = y;
    if(yf > 511)
    {
    yf = yf - 1024;
    }
    float ya = yf * 0.004;
    ynxt = ynxt + ya;


    Wire.beginTransmission(0x53);
    Wire.write(0x36); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte z0 = Wire.read();

    Wire.beginTransmission(0x53);
    Wire.write(0x37); 
    Wire.endTransmission();
    Wire.requestFrom(0x53, 1);
    byte z1 = Wire.read();
    z1 = z1 & 0x03;

  uint16_t z = (z1 << 8) + z0;
  int16_t zf = z;
  if(zf > 511){
    zf = zf - 1024;
  }
  float za = zf * 0.004;
  znxt = znxt + za;
  }
  xnxt = xnxt/50; 
  ynxt = ynxt/50;
  znxt = znxt/50;

  float acc2 = sqrt(((xnxt - xavg) * (xnxt - xavg)) + 
               ((ynxt - yavg) * (ynxt - yavg)) + 
               ((znxt - zavg) * (znxt - zavg)));

  if (acc2-acc > 0.05)
    {
      steps = steps + 1;
    }
  
  
  
  //display
  display.clearDisplay();
 	display.setCursor(0, 0);
  display.print("steps: ");
  display.println(steps);
 	display.print("myBPM: ");
 	display.println(myBPM);
 	display.display();
  delay(100);    
}

void adxl345_init(){
  Serial.begin(115200);
  Wire.begin();

  Wire.beginTransmission(0x53);
  Wire.write(0x2C); 
  Wire.write(0x08); 
  Wire.endTransmission();

  Wire.beginTransmission(0x53);
  Wire.write(0x31); 
  Wire.write(0x08); 
  Wire.endTransmission();

  Wire.beginTransmission(0x53);
  Wire.write(0x2D); 
  Wire.write(0x08); 
  Wire.endTransmission();
}

void ssd1306_init(){
  display.begin(SSD1306_SWITCHCAPVCC);
  display.display();
  delay(1000);
  display.clearDisplay();
  display.display();
  delay(1000);
  display.clearDisplay();
  display.setTextSize(2); 
  display.setTextColor(SSD1306_WHITE);
  display.setCursor(0, 0);
  display.println("STEPS");
  display.println("COUNTER");
  display.display();
  delay(1000);
}

  float read_av_acc(){
  for(int i = 0; i<50; i++){
  Wire.beginTransmission(0x53);
  Wire.write(0x32); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte x0 = Wire.read();

  Wire.beginTransmission(0x53);
  Wire.write(0x33); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte x1 = Wire.read();
  x1 = x1 & 0x03;

  uint16_t x = (x1 << 8) + x0;
  int16_t xf = x;
  if(xf > 511)
  {
  xf = xf - 1024;
  }
  float xa = xf * 0.004;
  xavg = xavg + xa;


  Wire.beginTransmission(0x53);
  Wire.write(0x34); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte y0 = Wire.read();

  Wire.beginTransmission(0x53);
  Wire.write(0x35); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte y1 = Wire.read();
  y1 = y1 & 0x03;

  uint16_t y = (y1 << 8) + y0;
  int16_t yf = y;
  if(yf > 511)
  {
  yf = yf - 1024;
  }
  float ya = yf * 0.004;
  yavg = yavg + ya;


  Wire.beginTransmission(0x53);
  Wire.write(0x36); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte z0 = Wire.read();

  Wire.beginTransmission(0x53);
  Wire.write(0x37); 
  Wire.endTransmission();
  Wire.requestFrom(0x53, 1);
  byte z1 = Wire.read();
  z1 = z1 & 0x03;

  uint16_t z = (z1 << 8) + z0;
  int16_t zf = z;
  if(zf > 511)
  {
  zf = zf - 1024;
  }
  float za = zf * 0.004;
  zavg = zavg + za;
  }
  xavg = xavg/50; 
  yavg = yavg/50;
  zavg = zavg/50;
}