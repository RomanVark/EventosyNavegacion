package ni.edu.uam.eventosynavegacion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ni.edu.uam.eventosynavegacion.dao.ProductoDao;
import ni.edu.uam.eventosynavegacion.modelos.Producto;

public class PulperiaController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Label lblResultado;

    private final ProductoDao productoDao = new ProductoDao();

    @FXML
    private void guardarProducto() {

        if (txtCodigo.getText().isBlank()
                || txtNombre.getText().isBlank()
                || txtPrecio.getText().isBlank()
                || txtCantidad.getText().isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debe completar todos los campos."
            );

            return;
        }

        double precio;
        int cantidad;

        try {
            precio = Double.parseDouble(txtPrecio.getText());
            cantidad = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Datos incorrectos",
                    "El precio y la cantidad deben ser valores numéricos."
            );

            return;
        }

        if (precio <= 0) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Precio incorrecto",
                    "El precio debe ser mayor que cero."
            );

            return;
        }

        if (cantidad < 0) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Cantidad incorrecta",
                    "La cantidad no puede ser negativa."
            );

            return;
        }

        Producto productoExistente =
                productoDao.buscarporcodigo(txtCodigo.getText().trim());

        if (productoExistente != null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Código existente",
                    "Ya existe un producto con ese código."
            );

            return;
        }

        Producto producto = new Producto(
                txtNombre.getText().trim(),
                precio,
                cantidad,
                txtCodigo.getText().trim()
        );

        productoDao.agregar(producto);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Producto registrado",
                "El producto fue guardado correctamente."
        );

        limpiarCampos();
    }

    @FXML
    private void buscarProducto(KeyEvent event) {

        if (event.getCode() != KeyCode.ENTER) {
            return;
        }

        String codigo = txtBuscar.getText().trim();

        if (codigo.isBlank()) {
            lblResultado.setText("Ingrese un código para buscar.");
            return;
        }

        Producto producto = productoDao.Buscar(codigo);

        if (producto == null) {

            lblResultado.setText(
                    "No se encontró ningún producto con el código " + codigo
            );

            return;
        }

        lblResultado.setText(
                "Código: " + producto.getCodigo()
                        + "\nNombre: " + producto.getNombre()
                        + "\nPrecio: C$ " + producto.getPrecio()
                        + "\nCantidad disponible: " + producto.getCantidad()
        );
    }

    @FXML
    private void buscarProductoBoton() {

        String codigo = txtBuscar.getText().trim();

        if (codigo.isBlank()) {
            lblResultado.setText("Ingrese un código para buscar.");
            return;
        }

        Producto producto = productoDao.buscarporcodigo(codigo);

        if (producto == null) {

            lblResultado.setText(
                    "No se encontró ningún producto con el código " + codigo
            );

            return;
        }

        lblResultado.setText(
                "Código: " + producto.getCodigo()
                        + "\nNombre: " + producto.getNombre()
                        + "\nPrecio: C$ " + producto.getPrecio()
                        + "\nCantidad disponible: " + producto.getCantidad()
        );
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();

        txtCodigo.requestFocus();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje
    ) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}