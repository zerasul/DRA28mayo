#include <SoftwareSerial.h>
#include <cocheDuino.h>
 SoftwareSerial sSerial(3,2);
 cocheDuino coche(11,10,9,6);
void setup()
{
 
   sSerial.begin(9600);
   Serial.begin(9600);
}

void loop(){
  
  if(sSerial.available())
  {
    char dato=sSerial.read();
      Serial.print(dato);
     switch(dato)
     {
      case '0':
        coche.parar();
        break;
      case '1':
        coche.avanzar(255,ADELANTE);
        break;
      case '2':
        coche.avanzar(255,ATRAS);
        break;
      case '3':
        coche.girar(255,DERECHA);
        break;
      case '4':
        coche.girar(255,IZQUIERDA);
        break;
   }
  }
}

