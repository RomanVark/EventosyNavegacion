package ni.edu.uam.eventosynavegacion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.edu.uam.eventosynavegacion.modelos.Artesania;

import java.io.File;

public class ArtesaniaController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ComboBox<String> cbCategoria;

    @FXML
    private ImageView imgProducto;

    @FXML
    private TableView<Artesania> tvProductos;

    @FXML
    private TableColumn<Artesania, String> colNombre;

    @FXML
    private TableColumn<Artesania, String> colCategoria;

    @FXML
    private TableColumn<Artesania, Double> colPrecio;

    @FXML
    private TableColumn<Artesania, String> colImagen;

    private String rutaImagen;

    @FXML
    public void initialize() {

        cbCategoria.getItems().addAll(
                "Cerámica",
                "Madera",
                "Textil",
                "Cuero",
                "Decoración",
                "Otros"
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        colImagen.setCellFactory(column -> new TableCell<>() {

            private final ImageView imagen = new ImageView();

            @Override
            protected void updateItem(String ruta, boolean empty) {
                super.updateItem(ruta, empty);

                if (empty || ruta == null || ruta.isBlank()) {
                    setGraphic(null);
                    return;
                }

                imagen.setImage(new Image(ruta));
                imagen.setFitWidth(60);
                imagen.setFitHeight(60);
                imagen.setPreserveRatio(true);

                setGraphic(imagen);
            }
        });

        colImagen.setCellValueFactory(
                new PropertyValueFactory<>("rutaImagen")
        );

        tvProductos.setRowFactory(tableView -> {
            TableRow<Artesania> fila = new TableRow<>();
            fila.setPrefHeight(70);
            return fila;
        });
    }

    @FXML
    private void nuevoProducto() {
        limpiarCampos();
    }

    @FXML
    private void guardarProducto() {

        if (txtNombre.getText().isBlank()
                || txtPrecio.getText().isBlank()
                || cbCategoria.getValue() == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos incompletos",
                    "Debe completar todos los campos."
            );

            return;
        }

        double precio;

        try {
            precio = Double.parseDouble(txtPrecio.getText());
        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Precio incorrecto",
                    "El precio debe ser un valor numérico."
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

        Artesania artesania = new Artesania(
                txtNombre.getText().trim(),
                cbCategoria.getValue(),
                precio,
                rutaImagen
        );

        tvProductos.getItems().add(artesania);

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Producto guardado",
                "La artesanía fue registrada correctamente."
        );

        limpiarCampos();
    }

    @FXML
    private void seleccionarImagen() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File archivo = fileChooser.showOpenDialog(
                txtNombre.getScene().getWindow()
        );

        if (archivo != null) {

            rutaImagen = archivo.toURI().toString();

            Image imagen = new Image(rutaImagen);

            imgProducto.setImage(imagen);
        }
    }

    @FXML
    private void buscarProducto() {

        TextInputDialog dialogo = new TextInputDialog();

        dialogo.setTitle("Buscar producto");
        dialogo.setHeaderText(null);
        dialogo.setContentText("Ingrese el nombre del producto:");

        dialogo.showAndWait().ifPresent(nombre -> {

            for (Artesania artesania : tvProductos.getItems()) {

                if (artesania.getNombre().equalsIgnoreCase(nombre.trim())) {

                    tvProductos.getSelectionModel().select(artesania);
                    tvProductos.scrollTo(artesania);

                    return;
                }
            }

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Producto no encontrado",
                    "No se encontró el producto."
            );
        });
    }

    @FXML
    private void nuevaVenta() {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Ventas",
                "Opción Nueva Venta seleccionada."
        );
    }

    @FXML
    private void verVentas() {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Ventas",
                "Opción Ver Ventas seleccionada."
        );
    }

    @FXML
    private void acercaDe() {

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Acerca de",
                "Sistema de tienda de artesanías nicaragüenses."
        );
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtPrecio.clear();
        cbCategoria.setValue(null);

        imgProducto.setImage(null);

        rutaImagen = null;

        txtNombre.requestFocus();
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