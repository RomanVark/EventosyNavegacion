package ni.edu.uam.eventosynavegacion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ni.edu.uam.eventosynavegacion.modelos.Lote;

import java.awt.*;

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
    private TableColumn<Lote, String> colCodigo;

    @FXML
    private TableColumn<Lote, String> colProductor;

    @FXML
    private TableColumn<Lote, Double> colPeso;

    @FXML
    private TableColumn<Lote, String> colFecha;

    @FXML
    private TextArea txtDetalles;

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(
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
        opcionEditar.setOnAction

    }

}
