package com.example.backend.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "app.client.generation.enabled", havingValue = "true")
public class ClientGeneratorConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void generateClient() {
        try {
            // Get the absolute path to the script relative to this class
            Path classPath = Paths.get(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            Path scriptPath = classPath
                    .getParent() // target/classes
                    .getParent() // target
                    .getParent() // backend
                    .resolve("scripts")
                    .resolve("generate-clients.sh");

            // Check if file exists
            if (!Files.exists(scriptPath)) {
                System.err.println("Script not found at: " + scriptPath);
                return;
            }

            // Make sure the script is executable
            if (!Files.isExecutable(scriptPath)) {
                try {
                    Set<PosixFilePermission> permissions = new HashSet<>(Files.getPosixFilePermissions(scriptPath));
                    permissions.add(PosixFilePermission.OWNER_EXECUTE);
                    Files.setPosixFilePermissions(scriptPath, permissions);
                } catch (IOException e) {
                    System.err.println("Failed to set script permissions: " + e.getMessage());
                    return;
                }
            }

            // Run the script
            ProcessBuilder processBuilder = new ProcessBuilder(scriptPath.toString());
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Client generation failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException | URISyntaxException e) {
            System.err.println("Failed to generate client: " + e.getMessage());
        }
    }
}