import os
import shutil

sourceDir = os.getcwd()
targetDir = "../LibGDX/extensions/box2d-editor"

if (os.path.exists(targetDir)):
    print("Deleting existing libgdx directory...", end="")
    shutil.rmtree(targetDir)
    print("done!")
    print()

print("Copying editor/src ...", end="")
shutil.copytree(sourceDir + "/editor/src", targetDir + "/editor/src")
print("done!")

print("Copying editor-test ...", end="")
shutil.copytree(sourceDir + "/editor-test", targetDir + "/editor-test")
print("done!")
    
print("Copying fixtureatlas/src ...", end="")
shutil.copytree(sourceDir + "/fixtureatlas/src", targetDir + "/fixtureatlas/src")
print("done!")
    
print("Copying fixtureatlas-test/src ...", end="")
shutil.copytree(sourceDir + "/fixtureatlas-test/src", targetDir + "/fixtureatlas-test/src")
print("done!")
    
print("Copying fixtureatlas-test/data ...", end="")
shutil.copytree(sourceDir + "/fixtureatlas-test/data", targetDir + "/fixtureatlas-test/data")
print("done!")

print()

print("Renaming editor/src/aurelienribon ...", end="")
os.renames(targetDir + "/editor/src/aurelienribon", targetDir + "/editor/src/com/badlogic/gdx")
print("done!")
print("Renaming fixtureatlas/src/aurelienribon ...", end="")
os.renames(targetDir + "/fixtureatlas/src/aurelienribon", targetDir + "/fixtureatlas/src/com/badlogic/gdx")
print("done!")
print("Renaming fixtureatlas-test/src/aurelienribon ...", end="")
os.renames(targetDir + "/fixtureatlas-test/src/aurelienribon", targetDir + "/fixtureatlas-test/src/com/badlogic/gdx")
print("done!")

print()

import fileinput
import sys

print("Replacing every occurence of 'aurelienribon' by 'com.badlogic.gdx' ...", end="")
for root, dirs, files in os.walk(targetDir):
    for file in files:
        if file.endswith(".java"):
            path = os.path.join(root,file)
            for line in fileinput.input(path, inplace=True):
                if "package aurelienribon" in line:
                    line = line.replace("package aurelienribon", "package com.badlogic.gdx")
                if "import aurelienribon" in line:
                    line = line.replace("import aurelienribon", "import com.badlogic.gdx")
                print(line, end="")
print("done!")
