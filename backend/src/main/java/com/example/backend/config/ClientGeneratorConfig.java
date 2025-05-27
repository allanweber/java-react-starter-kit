package com.example.backend.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ClientGeneratorConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void generateClient() {
        try {
            // Get the absolute path to the script
            Path scriptPath = Paths.get(System.getProperty("user.dir"))
                    .getParent()
                    .resolve("scripts")
                    .resolve("generate-clients.sh");

            // Make sure the script is executable
            if (!Files.isExecutable(scriptPath)) {
                Set<PosixFilePermission> permissions = new HashSet<>(Files.getPosixFilePermissions(scriptPath));
                permissions.add(PosixFilePermission.OWNER_EXECUTE);
                Files.setPosixFilePermissions(scriptPath, permissions);
            }

            // Run the script
            ProcessBuilder processBuilder = new ProcessBuilder(scriptPath.toString());
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Client generation failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Failed to generate client: " + e.getMessage());
        }
    }
}