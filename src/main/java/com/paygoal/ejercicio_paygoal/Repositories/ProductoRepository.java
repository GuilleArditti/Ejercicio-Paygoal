package com.paygoal.ejercicio_paygoal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paygoal.ejercicio_paygoal.Entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long>{

    public Producto findProductoByNombre(String nombre);
    
    @Query("SELECT p FROM Producto p ORDER BY p.precio ASC")
    List<Producto> listarProductosPorPrecioASC();

    @Query("SELECT p FROM Producto p ORDER BY p.precio DESC")
    List<Producto> listarProductosPorPrecioDESC();

}
