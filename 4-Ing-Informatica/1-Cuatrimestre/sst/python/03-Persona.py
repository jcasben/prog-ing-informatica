class Persona:
    def __init__(self, nombre, apellido, profesion):
        self.nombre = nombre
        self.apellido = apellido
        self.profesion = profesion
        self.sueldo = 0

    def nombre_completo(self):
        print(self.nombre + " " + self.apellido)

    def imprimir(self):
        print(f"[nombre: {self.nombre}], [apellido: {self.apellido}], [profesion: {self.profesion}], [sueldo: {self.sueldo}]")

    def asignar_sueldo(self, sueldo):
        self.sueldo = sueldo

    def imprimir_sueldo(self):
        print("El sueldo de " + self.nombre + " es de " +  str(self.sueldo))

def crear_persona(num_persona):
    print(f"### DATOS PERSONA {num_persona} ###")
    nombre = input("Introduce el nombre: ")
    apellido = input("Introduce el apellido: ")
    profesion = input("Introduce el profesion: ")
    persona = Persona(nombre, apellido, profesion)

    print()

    print(f"PERSONA {num_persona} CREADA: ")
    persona.imprimir()

    print()

    sueldo = input(f"Introduce ahora el sueldo de la persona {num_persona}: ")
    persona.asignar_sueldo(sueldo)
    persona.imprimir_sueldo()

    print()

crear_persona(1)
crear_persona(2)