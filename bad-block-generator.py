import string
import random

STRING_LEN = 40
FILE_COUNT = 5


def createBadBlock(filename):
    file = open(filename, "w")
    hash = "".join(
        random.choice(
            string.ascii_uppercase + string.ascii_lowercase + string.digits
        )
        for _ in range(STRING_LEN)
    )
    file.write(hash)
    file.write(" ")
    file.write(str(random.randrange(0, 999)))
    file.write("\n")


if __name__ == "__main__":
    for i in range(FILE_COUNT):
        createBadBlock("block/files/badBlock")
