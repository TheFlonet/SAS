package businesslogic.menu;

import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Section {
    private int id;
    private final String name;
    private final List<MenuItem> sectionItems;

    public Section(String name) {
        id = 0;
        this.name = name;
        sectionItems = new ArrayList<>();
    }

    public String toString() {
        StringBuilder result = new StringBuilder(name + "\n");
        for (MenuItem mi : sectionItems) {
            result.append("\t").append(mi.toString()).append("\n");
        }
        return result.toString();
    }

    public static void saveAllNewSections(int menuid, List<Section> sections) {
        String secInsert = "INSERT INTO catering.MenuSections (menu_id, name, position) VALUES (?, ?, ?);";
        PersistenceManager.executeUpdate(secInsert, sections.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, menuid);
                ps.setString(2, sections.get(batchCount).name);
                ps.setInt(3, batchCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                sections.get(count).id = rs.getInt(1);
            }
        });

        // salva le voci delle sezioni
        for (Section s : sections) {
            if (s.sectionItems.size() > 0) {
                MenuItem.saveAllNewItems(menuid, s.id, s.sectionItems);
            }
        }
    }
}
