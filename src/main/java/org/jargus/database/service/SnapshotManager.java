package org.jargus.database.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.jargus.database.dao.TsStorageClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author kotelnikovdan
 */
@Service
@AllArgsConstructor
public class SnapshotManager {

    private final String SNAPSHOT_FILE_NAME = "db_snapshot.json";
    private TsStorageClient tsStorage;
    ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedDelayString = "PT10S")
    public void createSnapshot() throws IOException {
        String snapshot = tsStorage.getSerializedSnapshot();
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(SNAPSHOT_FILE_NAME), "utf-8"))) {
            writer.write(snapshot);
        }
    }

    @PostConstruct
    public void loadSnapshot() {
        try {
            String snapshotString = Files.readString(Paths.get(SNAPSHOT_FILE_NAME), Charset.defaultCharset());
            tsStorage.loadSnapshot(snapshotString);
        } catch (Exception ex) {}
    }
}
