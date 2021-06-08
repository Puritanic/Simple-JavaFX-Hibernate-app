package zadatak4.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import zadatak4.DAO.FakultetDAOImpl;
import zadatak4.entities.Fakultet;

public class FakultetController {
    private final FakultetDAOImpl fakultetService = new FakultetDAOImpl();
    private ObservableList<Fakultet> listFakulteta = FXCollections.observableArrayList();

    public void addFakultet(Fakultet fakultet) {
        fakultetService.addFakultet(fakultet);
    }

    public ObservableList<Fakultet> getFakultetData() {
        if (!listFakulteta.isEmpty() && fakultetService.isValidCache()) {
            return listFakulteta;
        } else {
            listFakulteta.clear();
        }

        listFakulteta = FXCollections.observableList(fakultetService.listFakultetData());
        return listFakulteta;
    }

    public void removeFakultet(Integer id) {
        fakultetService.removeFakultet(id);
    }

    public void updateFakultet(Fakultet fakultet) {
        fakultetService.updateFakultet(fakultet);
    }
}
