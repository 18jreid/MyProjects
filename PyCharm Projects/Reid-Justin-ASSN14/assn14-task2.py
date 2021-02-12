import random
intsList = list(range(1, 10))
counts = 0

for i in range(1001):
    randomNumber = random.choice(intsList)
    counts += randomNumber
print(counts)