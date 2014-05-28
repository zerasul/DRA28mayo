#include <Arduino.h>
#include "cocheDuino.h"
#define CERO 0

cocheDuino::cocheDuino(byte pinM1,byte pinM2,byte pinM3,byte pinM4)
{
  _pinM1=pinM1;
  _pinM2=pinM2;
  _pinM3=pinM3;
  _pinM4=pinM4;
  pinMode(_pinM1,OUTPUT);
  pinMode(_pinM2,OUTPUT);
  pinMode(_pinM3,OUTPUT);
  pinMode(_pinM4,OUTPUT);
  parar();
}
cocheDuino::~cocheDuino()
{

}
void cocheDuino::avanzar(byte velocidad,SENTIDO sentido)
{

  switch(sentido){
  case ADELANTE:
    analogWrite(_pinM3,CERO);
    analogWrite(_pinM4,velocidad);
    analogWrite(_pinM1,CERO);
    analogWrite(_pinM2,velocidad);
    break;
  case ATRAS:
    
    analogWrite(_pinM3,velocidad);
    analogWrite(_pinM4,CERO);
    analogWrite(_pinM1,velocidad);
    analogWrite(_pinM2,CERO);
    break;
  default:
    break;
  }
}

void cocheDuino::girar(byte velocidad,DIRECCION direccion)
{
  switch(direccion){
  case DERECHA:
   // analogWrite(_pinM4,velocidad);
    analogWrite(_pinM2,CERO);
    analogWrite(_pinM1,velocidad);
    analogWrite(_pinM3,CERO);
   // analogWrite(_pinM4,velocidad);
    break;
  case IZQUIERDA:
   //analogWrite(_pinM2,velocidad);
    analogWrite(_pinM4,CERO);
    analogWrite(_pinM3,velocidad);
    analogWrite(_pinM1,CERO);
  //  analogWrite(_pinM2,velocidad);
    break;
  default:
    break;
  }
}
void cocheDuino::parar(void)
{
  analogWrite(this->_pinM1,CERO);
  analogWrite(this->_pinM2,CERO);
  analogWrite(this->_pinM3,CERO);
  analogWrite(this->_pinM4,CERO);
}

