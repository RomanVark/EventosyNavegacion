package ni.edu.uam.eventosynavegacion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ni.edu.uam.eventosynavegacion.modelos.Lote;
import java.util.Optional;



public class LoteController {

    @FXML
    private TextField txtCode;
    @FXML
    private TextField txtProductor;
    @FXML
    private TextField txtWeight;
    @FXML
    private TextField txtEntrega;
    @FXML
    private Button btnRegistrar;
    @FXML
    private TableView<Lote> tvRegistros;

    @FXML
    private TableColumn<Lote, String> colCode;

    @FXML
    private TableColumn<Lote, String> colProductor;

    @FXML
    private TableColumn<Lote, Double> colPeso;

    @FXML
    private TableColumn<Lote, String> colFecha;

    @FXML
    private TextArea txtDetalles;

    // Nos permite saber si estamos editanto un lote
    private Lote loteEditando;

    @FXML
    public void initialize() {
        colCode.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colProductor.setCellValueFactory(
                new PropertyValueFactory<>("productor")
        );

        colPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fecha")
        );

        // MouseEvent
        tvRegistros.setOnMouseClicked(this::mostrarDetalles);


        // ContextMenu
        ContextMenu menuContextual = new ContextMenu();

        MenuItem opcionEditar = new MenuItem("Editar");
        MenuItem opcionEliminar = new MenuItem("Eliminar");

        menuContextual.getItems().addAll(opcionEditar, opcionEliminar);


        //Evento Editar
        opcionEditar.setOnAction(event -> editarLote());

        //Evento Eliminar
        opcionEliminar.setOnAction(event -> eliminarLote());

        //Colocar contextMenu en el table view
        tvRegistros.setContextMenu(menuContextual);
    }

        @FXML
                protected void registrarLote(ActionEvent event){
            if (txtCode.getText().isBlank() ||txtProductor.getText().isBlank() || txtWeight.getText().isBlank() ||
        txtEntrega.getText().isBlank()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe completar todos los campos");
                return;
        }
            double weight;
            try {
                weight = Double.parseDouble(txtWeight.getText());
            } catch(NumberFormatException e) {
                mostrarAlerta(Alert.AlertType.ERROR,"Peso incorrecto", "El peso debe ser un valor numerico.");
                return;
            }

            //Si no se esta editanto
            if(loteEditando == null) {
                Lote lote = new Lote(txtCode.getText(),
                        txtProductor.getText(),
                        weight,
                        txtEntrega.getText());

                tvRegistros.getItems().add(lote);
            } else {
                //Cuando si se esta editando
                loteEditando.setCodigo(txtCode.getText());
                loteEditando.setProductor(txtProductor.getText());
                loteEditando.setPeso(weight);
                loteEditando.setFecha(txtEntrega.getText());

                tvRegistros.refresh();

                loteEditando = null;

                btnRegistrar.setText("Registrar Lotes");

            }
            limpiarCampos();


        }

        private void mostrarDetalles(MouseEvent event) {
            Lote loteSeleccionado = tvRegistros.getSelectionModel().getSelectedItem();

            if(loteSeleccionado != null) {
                txtDetalles.setText("Codigo: " + loteSeleccionado.getCodigo() + "\nProductor: " + loteSeleccionado.getProductor()
                + "\nPeso: " + loteSeleccionado.getPeso() + " Kg" + "\nFecha de entrega: " + loteSeleccionado.getFecha());
            }

        }



    private void editarLote() {
        Lote loteSeleccionado = tvRegistros.getSelectionModel().getSelectedItem();

        if(loteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Sin seleccion", "Tienes que seleccionar un lote para editar.");
            return;
        }
        //Se guarda el lote que se esta editando
        loteEditando = loteSeleccionado;

        //Se envian los datos nuevamente al formulario
        txtCode.setText(loteSeleccionado.getCodigo());
        txtProductor.setText(loteSeleccionado.getProductor());
        txtWeight.setText(String.valueOf(loteSeleccionado.getPeso()));
        txtEntrega.setText(loteSeleccionado.getFecha());

        btnRegistrar.setText("Guardar Cambios");

    }
    private void eliminarLote(){
        Lote loteSeleccionado = tvRegistros.getSelectionModel().getSelectedItem();

        if (loteSeleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING,"Sin seleccion", "Tienes que seleccionar un lote para editar.");
            return;

        }
        //Alerta de confirmacion
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminacion");

        confirmacion.setHeaderText("Desea eliminar ese lote?");

        confirmacion.setContentText("Codigo: " + loteSeleccionado.getCodigo() + "\nProductor: " + loteSeleccionado.getProductor());

        Optional<ButtonType> respuesta = confirmacion.showAndWait();

        if(respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            tvRegistros.getItems().remove(loteSeleccionado);
            txtDetalles.clear();
            limpiarCampos();
            loteEditando = null;
            btnRegistrar.setText("Registrar Lotes");
        }

    }

    private void limpiarCampos() {
        txtCode.clear();
        txtProductor.clear();
        txtWeight.clear();
        txtEntrega.clear();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta= new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }

}
