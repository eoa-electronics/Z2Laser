# **Z2Laser**

---

### **Description**
Z2Laser is a simple Java-based tool to convert Z movements in your G-Code files to Laser on or off commands. <br>
This tool is useful when using Engraving Software which doesn't support Laser on its own, but you still want to use it. <br>
This was developed for FlatCAM, so you can Laser-Etch your PCB trace isolations, instead of having to mill it (more about that at our website [eoa-electronics.de](https://eoa-electronics)).

### **Usage**
1. Start the Tool and click the ``Open`` button to open up your G-Code file.
2. (If you want to edit anything manually first, do it in the integrated editor.)
3. Load your preset by clicking on the ``Load Preset`` button, or type in the settings:
   1. ``G-Code for Z-Up (Laser Off)``: The G-Code which currently moves your Z-Axis up. Will be converted to a Laser-Off command.
   2. ``G-Code for Z-Down (Laser On)``: The G-Code which currently moves your Z-Axis down. Will be converted to a Laser-On command.
   3. ``Laser-On Power (S-Value)``: The Power of the Laser when turned on. This is the ``S`` parameter of ``M3`` (``M3 S[thisValue]``)
   4. ``Delete all existing M3 / M5 commands before processing``: This deletes, if selected, all ``M3`` (Spindle/Laser on) and/or ``M5`` (Spindle/Laser off) commands before converting your file. Deleting all ``M3`` commands is really recommended, to ensure the Laser won't be on when not wanted.
   5. ``Add M5 at file beginning / end after processing``: This adds, if selected, an ``M5`` (Laser off) command at the beginning and/or end of the processed file. Adding ``M5`` at the end is really recommended, to ensure the Laser will be turned off, at the end of the job, so it doesn't burn stuff while not moving and maybe cause a fire.
4. (If you want to, you can now click the ``Save as Preset`` Button to save your settings for the next time.)
5. Click the ``Convert`` button and wait for the tool to do its job.
6. Check your file, especially the beginning and end, and if you want to make some manual changes.
7. Save the newly generated G-Code file by clicking on the ``Save`` Button.
8. Start engraving!

<br>

### **Installation**
1. If not already done: Install a Java 17 runtime ([Download JDK 17](https://www.oracle.com/java/technologies/downloads/#jdk17))
2. Download the executable from the releases section (Z2Laser-V1.0.jar)
3. Put the executable somewhere on your computer (It is not recommended, to keep it in your Downloads folder!).
4. (If you want to: Create a shortcut to your desktop and/or start menu)
5. (Especially Linux users:) Make sure the file has the rights tu be executed, and also to create a 'settings.json' file in its directory. It will use this file to store your presets.
6. Run the file.
7. Have fun using Z2Laser. (See section 'Usage' for how to use Z2Laser)

---

## **Disclaimer**
We do NOT take any responsibility for any damage of property and/or personal injury caused. We can NOT guarantee that this tool will always work flawlessly, and that it will always be compatible with your hardware and software.<br>
Laser are dangerous! Using this is COMPLETELY on YOUR OWN RISK!

---

I hope you enjoy my little tool. If you find any bugs, or want some functions added, please feel free to open an issue, and I will see what I can do. <br>
And remember: This tool is completely free and open, so you can do whatever you want with it.