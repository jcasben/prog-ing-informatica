import tkinter as tk
import numpy as np

def cargar_obj(file_path):
    vertices = []
    caras = []

    with open(file_path, 'r') as f:
        for line in f:
            # Separar la línea en palabras
            parts = line.strip().split()
            if not parts:
                continue

            # Identificar el tipo de línea
            if parts[0] == 'v':  # Línea de vértice
                vertices.append([float(p) for p in parts[1:4]])  # Añadir el vértice
            elif parts[0] == 'f':  # Línea de cara
                caras.append([int(p.split('/')[0]) - 1 for p in parts[1:]])  # Añadir la cara (ajustar índice)

    return vertices, caras


def escalar_a_caja(vertices, box_size=2.0):
    vertices = np.array(vertices)
    # Obtener el mínimo y máximo de los vértices en X, Y y Z
    min_vals = np.min(vertices, axis=0)
    max_vals = np.max(vertices, axis=0)

    # Calcular el centro del modelo
    centro = (max_vals + min_vals) / 2

    # Mover el modelo al origen
    vertices -= centro

    # Calcular la escala para ajustarlo a la caja de 2x2
    escala = box_size / np.max(max_vals - min_vals)
    vertices *= escala

    return vertices


# Proyección ortográfica simple (ignorar Z) y centrar en la pantalla
def proyectar_vertices(vertices, width, height, scale=100):
    projected_vertices = []
    for v in vertices:
        # Proyección en el plano XY, ignorando Z
        x_2d = int(v[0] * scale + width / 2)
        y_2d = int(-v[1] * scale + height / 2)  # Invertir Y para que apunte hacia arriba
        projected_vertices.append((x_2d, y_2d))
    return projected_vertices


# Visualización con tkinter
def visualizar_obj(vertices, caras):
    # Crear ventana tkinter
    window = tk.Tk()
    window.title("Visualización de archivo OBJ")

    # Dimensiones del canvas
    width, height = 800, 600
    canvas = tk.Canvas(window, width=width, height=height, bg="white")
    canvas.pack()

    # Escalar los vértices para que se ajusten a la caja de 2x2
    vertices_escalados = escalar_a_caja(vertices)
    # vertices_escalados = vertices

    # Proyectar los vértices 3D en 2D
    projected_vertices = proyectar_vertices(vertices_escalados, width, height)

    # Dibujar las caras
    for cara in caras:
        puntos = [projected_vertices[i] for i in cara]
        canvas.create_polygon(puntos, outline='black', fill='', width=1)

    window.mainloop()


# Ejemplo de uso
vertices, caras = cargar_obj('bunny.obj')  # Cargar un archivo OBJ
visualizar_obj(vertices, caras)  # Mostrar la visualización con tkinter