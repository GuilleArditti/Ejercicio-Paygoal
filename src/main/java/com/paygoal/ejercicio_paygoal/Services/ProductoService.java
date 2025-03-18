package com.paygoal.ejercicio_paygoal.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paygoal.ejercicio_paygoal.Entities.Producto;
import com.paygoal.ejercicio_paygoal.Repositories.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository=productoRepository;
    }

    public Producto crearProducto(Producto producto){
        productoRepository.save(producto);

    return producto;
    }

    public Producto buscarProductoPorNombre(String nombre){
        return productoRepository.findProductoByNombre(nombre);
    }

    public Producto buscarProductoPorId(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

    public List<Producto> listarProductosPorPrecioASC(){
        return productoRepository.listarProductosPorPrecioASC();
    }

    public List<Producto> listarProductosPorPrecioDESC(){
        return productoRepository.listarProductosPorPrecioDESC();
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
