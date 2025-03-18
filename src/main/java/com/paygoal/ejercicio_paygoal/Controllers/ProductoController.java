package com.paygoal.ejercicio_paygoal.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.paygoal.ejercicio_paygoal.Entities.Producto;
import com.paygoal.ejercicio_paygoal.Exceptions.ResourceNotFoundException;
import com.paygoal.ejercicio_paygoal.Services.ProductoService;

@RestController
@RequestMapping("paygoal/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService= productoService;
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){
        if(productoService.buscarProductoPorNombre(producto.getNombre())==null){
            productoService.crearProducto(producto);
        }
        else{
            throw new IllegalArgumentException("El producto " + producto.getNombre() + " ya se encuentra en la base de datos");
        }

    return producto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id){
        Producto producto= productoService.buscarProductoPorId(id);
        if(producto==null){
            throw new ResourceNotFoundException("No se encontro el producto " + id);
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    @GetMapping("/precio/asc")
    public List<Producto> listarProductosPorPrecioASC(){
        return productoService.listarProductosPorPrecioASC();
    }

    @GetMapping("/precio/desc")
    public List<Producto> listarProductosPorPrecioDESC(){
        return productoService.listarProductosPorPrecioDESC();
    }

    @GetMapping("/buscar")
    public Producto buscarProducto(@RequestParam String nombre){
        return productoService.buscarProductoPorNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable Long id){
        Producto producto= productoService.buscarProductoPorId(id);
        if(producto==null){
            throw new ResourceNotFoundException("El id " + id + " no existe");
        }
        productoService.eliminarProducto(id);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put(id + " eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoRecibido){
        Producto producto = productoService.buscarProductoPorId(id);
        if(producto==null){
            throw new ResourceNotFoundException("El id " + id + " no existe");

        }
        producto.setNombre(productoRecibido.getNombre());
        producto.setDescripcion((productoRecibido.getDescripcion()));
        producto.setCantidad(productoRecibido.getCantidad());

        productoService.crearProducto(producto);

        return ResponseEntity.ok(producto);
    }

}
