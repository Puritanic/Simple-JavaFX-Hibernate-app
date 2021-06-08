package zadatak4.interfaces;

import zadatak4.entities.Fakultet;

import java.util.List;

public interface FakultetDAO {
    void addFakultet(Fakultet fakultet);

    List<Fakultet> listFakultetData();

    void removeFakultet(Integer id);

    void updateFakultet(Fakultet fakultet);
}
