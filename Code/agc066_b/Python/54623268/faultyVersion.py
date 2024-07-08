import random
n = 5 ** 50
for i in range(185):
    s = str(random.randint(1, 9999) * n)
    if i != 0: s.zfill(54)
    print(s, end = "")