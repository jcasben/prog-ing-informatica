import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.io.File
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

class ObjViewer2D : JPanel() {

    private val vertices = mutableListOf<Pair<Double, Double>>()
    private val faces = mutableListOf<Int>()

    init {
        cargarObj("bunny.obj")
    }

    private fun cargarObj(filePath: String) {
        File(filePath).forEachLine { line ->
            val parts = line.trim().split("\\s+".toRegex())
            when (parts[0]) {
                "v" -> {
                    // Convertir x, y, z a pares (x, y) para la visualización 2D
                    if (parts.size >= 4) { // Asegúrate de que haya al menos 4 elementos
                        val x = parts[1].toDouble()
                        val y = parts[2].toDouble()
                        vertices.add(Pair(x, y)) // Solo se agrega (x, y)
                    }
                }
                "f" -> faces.addAll(parts.drop(1).map { it.split('/')[0].toInt() - 1 })
            }
        }
        println("Vertices: $vertices")
//        escalarACaja()
    }

//    private fun escalarACaja(boxSize: Double = 2.0) {
//        val scaledVertices = vertices.chunked(3) { it.toDoubleArray() }
//        val minX = scaledVertices.minOf { it[0] }
//        val maxX = scaledVertices.maxOf { it[0] }
//        val minY = scaledVertices.minOf { it[1] }
//        val maxY = scaledVertices.maxOf { it[1] }
//
//        val centerX = (minX + maxX) / 2
//        val centerY = (minY + maxY) / 2
//
//        val scale = boxSize / maxOf(maxX - minX, maxY - minY)
//
//        vertices.clear()
//        for (vertex in scaledVertices) {
//            val x2D = (vertex[0] - centerX) * scale + width / 2
//            val y2D = (vertex[1] - centerY) * scale + height / 2
//            vertices.add(Pair(x2D, -y2D)) // Invertir Y para que apunte hacia arriba
//        }
//    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.BLACK

        // Dibuja las líneas entre los vértices
        for (i in faces.indices step 3) {
            if (i + 2 < faces.size) {
                val v1 = vertices[faces[i]]
                val v2 = vertices[faces[i + 1]]
                val v3 = vertices[faces[i + 2]]

                // Dibujar las líneas del triángulo
                g.drawLine(
                    (v1.first * 100 + width / 2).toInt(),
                    (-v1.second * 100 + height / 2).toInt(),
                    (v2.first * 100 + width / 2).toInt(),
                    (-v2.second * 100 + height / 2).toInt()
                )
                g.drawLine(
                    (v2.first * 100 + width / 2).toInt(),
                    (-v2.second * 100 + height / 2).toInt(),
                    (v3.first * 100 + width / 2).toInt(),
                    (-v3.second * 100 + height / 2).toInt()
                )
                g.drawLine(
                    (v3.first * 100 + width / 2).toInt(),
                    (-v3.second * 100 + height / 2).toInt(),
                    (v1.first * 100 + width / 2).toInt(),
                    (-v1.second * 100 + height / 2).toInt()
                )
            }
        }
    }
}

fun main() {
    val frame = JFrame("Visualización 2D de archivo OBJ")
    val viewer = ObjViewer2D()

    viewer.isVisible = true

    frame.add(viewer)
    frame.size = Dimension(800, 600)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.isVisible = true

    frame.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(e: WindowEvent?) {
            exitProcess(0)
        }
    })
}