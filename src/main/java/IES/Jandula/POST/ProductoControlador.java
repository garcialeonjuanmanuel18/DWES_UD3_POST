package ies.jandula.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductoControlador {

    private final List<Producto> productos = new ArrayList<>(); //Lista para guardar cada producto introducido en el form

    // 1. Mostrar la lista y el formulario
    //Ejecuta el método cuando alguien entre en localhost/productos
    @GetMapping("/productos")
    public String mostrarProductos(Model model) {//Model model es un objeto que permite pasar objetos de la lista a html

        model.addAttribute("productos", productos);
        model.addAttribute("nuevoProducto", new Producto());

        return "productos";
    }

    // 2. Procesar el envío del formulario (POST)
    @PostMapping("/productos/guardar")
    public String guardarProducto(
            @ModelAttribute("nuevoProducto") Producto producto,
            RedirectAttributes redirectAttributes) {

        // Añadimos el nuevo producto recibido a la lista
        productos.add(producto);

        // Añadimos un mensaje de confirmación
        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "¡Producto '" + producto.getNombre()
                        + "' agregado correctamente!");

        // Redirigimos a la vista GET para evitar duplicados con F5
        return "redirect:/productos";
    }
}