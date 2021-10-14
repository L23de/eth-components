import os
import random


def getRandomAddr():
    files = os.listdir("./block/files")
    file = files[random.randint(0, len(files) - 1)]

    f = open("./block/files/" + file, "r")
    lines = f.readlines()

    line = lines[random.randint(0, len(lines) - 1)]
    print(line.split(" ")[0])


if __name__ == "__main__":
    getRandomAddr()
