H = int(input())
a = 0
i = 1

while True:
    a = a + 2 ** i
    i += 1
    if a > H:
        print(i)
        break