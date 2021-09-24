import string
import random

STRING_LEN = 40

def createSamples(file, lines: int):
    for _ in range(lines):
        hash = ''.join(random.choice(string.ascii_uppercase + string.ascii_lowercase + string.digits) for _ in range(STRING_LEN))
        file.write(hash)
        file.write(" ")
        file.write(str(random.randrange(0, 999)))
        file.write("\n")

file = open("ledger2.txt", "w")
createSamples(file, 20)

