WibexVisuals (Fabric, Minecraft 1.21.11)

HOW TO GET THE .JAR (no programs needed, only a browser)
1. Register at github.com (free).
2. Click "+" (top right) -> New repository. Name: wibexvisuals. Public. Create.
3. Click "uploading an existing file". Open this unpacked folder, select EVERYTHING INSIDE it
   (build.gradle, gradle.properties, settings.gradle, src, .github) and drag it into the browser.
   Press "Commit changes".
   If the .github folder did not upload: Add file -> Create new file, type
   .github/workflows/build.yml  as the name and paste the contents of that file.
4. Open the "Actions" tab. The "Build WibexVisuals" run starts by itself (2-5 minutes).
   Wait for the green check mark.
5. Click the run, scroll down to "Artifacts", download "WibexVisuals" (a zip).
   Inside is wibexvisuals-1.0.0.jar.

INSTALL
Fabric Loader for 1.21.11 + Fabric API (modrinth.com/mod/fabric-api) + wibexvisuals-1.0.0.jar
all go into .minecraft/mods. In game: Right Shift = menu, hold C = zoom.
