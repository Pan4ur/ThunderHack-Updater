package thunder.updater;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.LanguageAdapter;
import net.fabricmc.loader.api.LanguageAdapterException;
import net.fabricmc.loader.api.ModContainer;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Paths;

public class PreLaunch implements LanguageAdapter {
    private static final String version = "thunderhack-1.6.jar";

    static {
        File file = Paths.get(FabricLoader.getInstance().getGameDir().toString(), "mods", version).toFile();
        boolean wasBefore = file.exists();
        try (BufferedInputStream bis = new BufferedInputStream(new URL("https://github.com/Pan4ur/ThunderHack-Recode/releases/download/latest/" + version).openStream()); FileOutputStream fos = new FileOutputStream(file)) {
            byte[] db = new byte[1024];
            int b;
            while ((b = bis.read(db, 0, 1024)) != -1)
                fos.write(db, 0, b);

            if(!wasBefore)
                JOptionPane.showMessageDialog(null, "Thunderhack has been installed, restart the game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> T create(ModContainer mod, String value, Class<T> type) throws LanguageAdapterException {
        return null;
    }
}