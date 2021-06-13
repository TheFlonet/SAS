package businesslogic.menu;

import businesslogic.recipe.KitchenProcess;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuItem {
    private int id;
    private final String description;
    private final KitchenProcess itemProcess;

    public MenuItem(KitchenProcess rec) {
        this(rec, rec.getName());
    }

    public MenuItem(KitchenProcess rec, String desc) {
        id = 0;
        itemProcess = rec;
        description = desc;
    }

    public String toString() {
        return description;
    }

    public static void saveAllNewItems(int menuid, int sectionid, List<MenuItem> items) {
        String itemInsert = "INSERT INTO catering.MenuItems (menu_id, section_id, description, recipe_id, position) VALUES (?, ?, ?, ?, ?);";
        PersistenceManager.executeUpdate(itemInsert, items.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, menuid);
                ps.setInt(2, sectionid);
                ps.setString(3, items.get(batchCount).description);
                ps.setInt(4, items.get(batchCount).itemProcess.getId());
                ps.setInt(5, batchCount);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                items.get(count).id = rs.getInt(1);
            }
        });
    }

    public KitchenProcess getItemProcess() {
        return itemProcess;
    }
}
