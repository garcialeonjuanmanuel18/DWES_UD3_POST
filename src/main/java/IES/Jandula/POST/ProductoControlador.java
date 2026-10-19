package IES.Jandula.POST;

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
    @GetMapping("/productos")//Se llama así porque el html se llama "productos"
    public String mostrarProductos(Model model) {//Model model es un objeto que permite pasar objetos de la lista a html como si fuera una bandeja

        model.addAttribute("listaProductos", productos);//Añade la lista productos a ${listaproductos} mediante model
        model.addAttribute("nuevoProducto", new Producto());//Crea en el modelo un nuevo producto. Este se recoge en la parte del html con ${nuevoProducto} 

        return "productos";//SOLAMENTE ESTO, NOMBRE IGUAL QUE EL NOMBRE DEL ARCHIVO HTML
    }

    // 2. Procesar el envío del formulario (POST)
    @PostMapping("/productos/guardar")//dirección que recibe los datos del formulario
    public String guardarProducto(
            @ModelAttribute("nuevoProducto") Producto producto, 
            RedirectAttributes redirectAttributes) {

        // Añadimos el nuevo producto recibido a la lista
        productos.add(producto);

        // Añadimos un mensaje de confirmación
        redirectAttributes.addFlashAttribute(
                "mensajeExito", // añadimos en mensajeExito lo que hay justo debajo
                "¡Producto '" + producto.getNombre()
                        + "' agregado correctamente!");

        // Redirigimos a la vista GET para evitar duplicados con F5
        return "redirect:/productos";
    }
}