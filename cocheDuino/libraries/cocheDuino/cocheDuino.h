#include <Arduino.h>
#ifndef COCHEDUINO_H_
#define COCHEDUINO_H_
enum SENTIDO{
  ADELANTE=0,
  ATRAS
};
enum DIRECCION{
  DERECHA=0,
  IZQUIERDA 
};
class cocheDuino{
  public:
     cocheDuino(byte pinM1,byte pinM2,byte pinM3,byte pinM4);
     ~cocheDuino();
     void avanzar(byte velocidad,SENTIDO sentido);
     void girar(byte velocidad,DIRECCION direccion);
     void parar(void);
  private:
   byte _pinM1,_pinM2,_pinM3,_pinM4;
   
};

#endif
