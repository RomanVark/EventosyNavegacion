package ni.edu.uam.eventosynavegacion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.eventosynavegacion.dao.ProductoDao;
import ni.edu.uam.eventosynavegacion.modelos.Producto;

public class Pulperia {
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Label lblResultado;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnBuscar;

    private final ProductoDao productoDao = new ProductoDao();

    @FXML
    private void guardarProducto(ActionEvent event) {}



}
